package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.custom.CustomItem;
import com.blakebr0.mysticalagriculture.items.custom.CustomItems;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = MysticalAgriculture.MOD_ID)
public class DefaultHandler {

    private static final ModelResourceLocation ESSENCE_MODEL = new ModelResourceLocation(
            new ResourceLocation(MysticalAgriculture.MOD_ID, "default_essence"),
            "inventory");
    private static final ModelResourceLocation SEED_MODEL = new ModelResourceLocation(
            new ResourceLocation(MysticalAgriculture.MOD_ID, "default_seeds"),
            "inventory");
    private static final ResourceLocation CROP = new ResourceLocation(
            MysticalAgriculture.MOD_ID, "default_crop");
    private static final ModelResourceLocation CROP_MODEL = new ModelResourceLocation(
            CROP, "inventory");

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
        if (!ModConfig.confEnableDefaultSeedTexture) {
            return;
        }
        ModelManager manager = event.getModelManager();
        IBakedModel defaultEssenceModel = manager.getModel(ESSENCE_MODEL);
        IBakedModel defaultSeedModel = manager.getModel(SEED_MODEL);
        IBakedModel defaultCropModel = manager.getModel(CROP_MODEL);
        IBakedModel missing = manager.getMissingModel();
        for (CustomItem item : CustomItems.getCustomItems()) {
            ModelResourceLocation essenceModelLoc = new ModelResourceLocation(
                    new ResourceLocation(MysticalAgriculture.MOD_ID, item.name() + "_essence"), "inventory");
            IBakedModel essenceModel = manager.getModel(essenceModelLoc);
            if (essenceModel == missing || isMissing(essenceModel)) {
                event.getModelRegistry().putObject(
                        essenceModelLoc,
                        defaultEssenceModel);
            }

            ModelResourceLocation seedModelLoc = new ModelResourceLocation(
                    new ResourceLocation(MysticalAgriculture.MOD_ID, item.name() + "_seeds"), "inventory");
            IBakedModel model = manager.getModel(seedModelLoc);
            if (model == missing || isMissing(model)) {
                event.getModelRegistry().putObject(
                        seedModelLoc,
                        defaultSeedModel);
            }

            ModelResourceLocation cropModelLoc = new ModelResourceLocation(
                    new ResourceLocation(MysticalAgriculture.MOD_ID, item.name() + "_crop"), "inventory");
            IBakedModel cropModel = manager.getModel(cropModelLoc);
            if (model == missing || isMissing(cropModel)) {
                event.getModelRegistry().putObject(
                        cropModelLoc,
                        defaultCropModel);
            }

            Block block = item.block();

            for (IBlockState state : block.getBlockState().getValidStates()) {
                ModelResourceLocation blockState = new ModelResourceLocation(
                        block.getRegistryName(), "age=" + state.getValue(BlockCrops.AGE));
                IBakedModel stateModel = manager.getModel(blockState);

                if (stateModel == missing || isMissing(stateModel)) {
                    IBakedModel replacement = manager
                            .getModel(new ModelResourceLocation(CROP, "age=" + state.getValue(BlockCrops.AGE)));
                    event.getModelRegistry().putObject(
                            blockState,
                            replacement);
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
