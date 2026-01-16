package com.blakebr0.mysticalagriculture.items.custom;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.google.common.collect.Maps;

@Mod.EventBusSubscriber(modid = MysticalAgriculture.MOD_ID, value = Side.CLIENT)
public class InferiumCustomRegistration {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (Item item : CustomItems.stagedInferiumItems) {
            ModelLoader.setCustomModelResourceLocation(
                    item,
                    0,
                    new ModelResourceLocation(
                            new ResourceLocation(MysticalAgriculture.MOD_ID, "tier1_inferium_seeds"),
                            "inventory"));
        }
        for (Block block : CustomItems.stagedInferiumBlocks) {
            ModelLoader.setCustomModelResourceLocation(
                    Item.getItemFromBlock(block),
                    0,
                    new ModelResourceLocation(
                            new ResourceLocation(MysticalAgriculture.MOD_ID, "tier1_inferium_crop"),
                            "normal"));
            ModelLoader.setCustomStateMapper(block,
                    new StateMapperBase() {

                        @Override
                        protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                            Map<IProperty<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
                            return new ModelResourceLocation(
                                    new ResourceLocation(MysticalAgriculture.MOD_ID, "tier1_inferium_crop"),
                                    getPropertyString(map));
                        }
                    });
        }
    }
}
