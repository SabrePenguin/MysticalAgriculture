package com.blakebr0.mysticalagriculture.items;

import java.util.*;

import net.minecraft.item.Item;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.util.resources.CustomItemJsonReader;
import com.github.bsideup.jabel.Desugar;

public class CustomItems {

    private static final Set<CustomItem> customItems = new HashSet<>();

    public static Set<CustomItem> getCustomItems() {
        return customItems;
    }

    public static void init() {
        if (ModConfig.confEnableCustomSeeds) {
            Set<CustomItemJsonReader.CustomItemHolder> itemHolderSet = CustomItemJsonReader.loadResources();
            for (CustomItemJsonReader.CustomItemHolder item : itemHolderSet) {
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
                customItems.add(new CustomItem(item.name, crop, seed, item.tier, item.input_item, item.output_count,
                        item.output_item, item.type));
                // Equivalent to set()
                blockCrop.setCrop(crop);
                blockCrop.setSeed(seed);
            }
        }
    }

    @Desugar
    public record CustomItem(String name, Item crop, Item seed, int tier, String input, int outputCount, String output,
                             CustomItemJsonReader.CustomItemHolder.RecipeType type) {

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof CustomItem other) {
                return name.equals(other.name);
            }
            return false;
        }
    }
}
