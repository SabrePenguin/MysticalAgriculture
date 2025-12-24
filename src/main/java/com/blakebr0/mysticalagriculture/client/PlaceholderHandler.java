package com.blakebr0.mysticalagriculture.client;

import java.io.IOException;

import com.blakebr0.mysticalagriculture.items.custom.CustomItem;
import com.blakebr0.mysticalagriculture.items.custom.CustomItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = MysticalAgriculture.MOD_ID)
public class PlaceholderHandler {

    private static final ResourceLocation ESSENCE_TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID,
            "items/placeholder_essence");
    private static final ModelResourceLocation ESSENCE_MODEL = new ModelResourceLocation(
            new ResourceLocation(MysticalAgriculture.MOD_ID, "placeholder_essence"),
            "inventory");
    private static final ModelResourceLocation SEED_MODEL = new ModelResourceLocation(
            new ResourceLocation(MysticalAgriculture.MOD_ID, "placeholder_seeds"),
            "inventory");
    private static final ModelResourceLocation CROP_MODEL = new ModelResourceLocation(
            new ResourceLocation(MysticalAgriculture.MOD_ID, "placeholder_crop"),
            "normal");

    private static Class<?> fancyMissingClass;

    static {
        try {
            fancyMissingClass = Class.forName("net.minecraftforge.client.model.FancyMissingModel$BakedModel");
        } catch (ClassNotFoundException e) {
            fancyMissingClass = null;
        }
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        ModelManager manager = event.getModelManager();
        IBakedModel placeholderEssenceModel = manager.getModel(ESSENCE_MODEL);
        IBakedModel placeholderSeedModel = manager.getModel(SEED_MODEL);
        IBakedModel missing = manager.getMissingModel();
        for (CustomItem item : CustomItems.getCustomItems()) {
            ModelResourceLocation essenceModelLoc = new ModelResourceLocation(
                    new ResourceLocation(MysticalAgriculture.MOD_ID, item.name() + "_essence"), "inventory");
            IBakedModel essenceModel = manager.getModel(essenceModelLoc);
            if (essenceModel == missing || isMissing(essenceModel)) {
                event.getModelRegistry().putObject(
                        essenceModelLoc,
                        placeholderEssenceModel
                );
            }

            ModelResourceLocation seedModelLoc = new ModelResourceLocation(
                    new ResourceLocation(MysticalAgriculture.MOD_ID, item.name() + "_seeds"), "inventory");
            IBakedModel model = manager.getModel(seedModelLoc);
            if (model == missing || isMissing(model)) {
                event.getModelRegistry().putObject(
                        seedModelLoc,
                        placeholderSeedModel
                );
            }


            Block block = item.block();
            BlockModelShapes shapes = manager.getBlockModelShapes();

            for (IBlockState state: block.getBlockState().getValidStates()) {
                IBakedModel stateModel = shapes.getModelForState(state);
                if (stateModel == missing) {
                    ModelLoader.setCustomStateMapper(
                            block,
                            new StateMapperBase() {
                                @Override
                                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                                    return CROP_MODEL;
                                }
                            }
                    );
                }
            }
        }
    }


    private static boolean isMissing(IBakedModel model) {
        if (model == null) return true;
        if (fancyMissingClass == null) return false;
        return fancyMissingClass.isInstance(model);
    }
}
