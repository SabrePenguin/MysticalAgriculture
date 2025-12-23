package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.util.resources.CustomItemJsonReader;

import java.util.Set;

public class CustomItems {


    public static void init() {
        if (ModConfig.confEnableCustomSeeds) {
            Set<CustomItemJsonReader.CustomItemHolder> out = CustomItemJsonReader.loadResources();
            for(CustomItemJsonReader.CustomItemHolder item: out) {
                final ModRegistry registry = MysticalAgriculture.REGISTRY;

                BlockMysticalCrop blockCrop = new BlockMysticalCrop(item.name + "_crop");

                ItemBase crop = new ItemBase("ma." + item.name + "_essence");
                registry.register(crop, item.name + "_essence");
                if (ModConfig.confGenericOreDictEssence) {
                    registry.addOre(crop, "essenceTier" + item.tier);
                }
                crop.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
                registry.register(blockCrop, item.name + "_crop");

                ItemSeed seed = new ItemSeed(item.name + "_seed", blockCrop, item.tier);
                registry.register(seed, item.name + "_seeds");
                if (ModConfig.confGenericOreDictEssence) {
                    registry.addOre(seed, "seedsTier" + item.tier);
                }
                seed.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);

                // Equivalent to set()
                blockCrop.setCrop(crop);
                blockCrop.setSeed(seed);
            }
        }
    }
}
