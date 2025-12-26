package com.blakebr0.mysticalagriculture.items.custom;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockCruxMysticalCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ItemSeed;
import com.blakebr0.mysticalagriculture.util.resources.CustomItemJsonReader;
import com.github.bsideup.jabel.Desugar;

public class CustomItems {

    private static final Set<CustomItem> customItems = new HashSet<>();
    public static List<Pair<String, BlockCruxMysticalCrop>> staging = new ArrayList<>();

    public static Set<CustomItem> getCustomItems() {
        return customItems;
    }

    public static void init() {
        if (ModConfig.confEnableCustomSeeds) {
            if (ModConfig.confEnableDefaultSeedTexture) {
                final ModRegistry registry = MysticalAgriculture.REGISTRY;

                BlockCrops blockCrop = new BlockMysticalCrop("default_crop");
                registry.register(blockCrop, "default_crop");
                Item defaultEssence = new Item();
                registry.register(defaultEssence, "default_essence");
                Item defaultSeeds = new Item();
                registry.register(defaultSeeds, "default_seeds");
                customItems.add(
                        new CustomItem("default", defaultEssence, defaultSeeds, blockCrop, 1, null, 1,
                                null, CustomRecipeType.NONE));
            }

            Set<CustomItemJsonReader.CustomItemHolder> itemHolderSet = CustomItemJsonReader.loadResources();
            for (CustomItemJsonReader.CustomItemHolder item : itemHolderSet) {
                final ModRegistry registry = MysticalAgriculture.REGISTRY;
                BlockMysticalCrop blockCrop = setupCrop(item.name + "_crop", item.crux);
                registry.register(blockCrop, item.name + "_crop");

                ItemBase crop = new ItemBase("ma." + item.name + "_essence");
                registry.register(crop, item.name + "_essence");
                if (ModConfig.confGenericOreDictEssence) {
                    registry.addOre(crop, "essenceTier" + item.tier);
                }
                crop.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);

                ItemSeed seed = new ItemSeed(item.name + "_seed", blockCrop, item.tier);
                registry.register(seed, item.name + "_seeds");
                if (ModConfig.confGenericOreDictEssence) {
                    registry.addOre(seed, "seedsTier" + item.tier);
                }
                seed.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
                customItems.add(
                        new CustomItem(item.name, crop, seed, blockCrop, item.tier, item.input_item, item.output_count,
                                item.output_item, item.type));
                // Equivalent to set()
                blockCrop.setCrop(crop);
                blockCrop.setSeed(seed);
            }
        }
    }

    public static void registerCruxes() {
        final Block air = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:air"));
        final IBlockState airState = air.getDefaultState();
        for (Pair<String, BlockCruxMysticalCrop> pair : staging) {
            String[] metaName = pair.one.split("#", 2);
            Block existingBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(metaName[0]));
            if (existingBlock != air && existingBlock != null) {
                IBlockState state = metaName.length == 2 ?
                        existingBlock.getStateFromMeta(Integer.parseInt(metaName[1])) :
                        existingBlock.getDefaultState();
                pair.two.setRoot(state);
            } else {
                MysticalAgriculture.LOGGER.error("Invalid crux {}, using \"minecraft:air\"", pair.one);
                pair.two.setRoot(airState);
            }
        }
    }

    private static BlockMysticalCrop setupCrop(String name, String crux) {
        if (crux != null) {
            BlockCruxMysticalCrop cruxCrop = new BlockCruxMysticalCrop(name);
            String[] metaName = crux.split("#", 2);
            try {
                if (metaName.length == 2)
                    Integer.parseInt(metaName[1]);
            } catch (NumberFormatException e) {
                MysticalAgriculture.LOGGER.error("Unable to register meta {} for block {}, removing crux", metaName[1],
                        metaName[0]);
                return new BlockMysticalCrop(name);
            }
            staging.add(new Pair<>(crux, cruxCrop));
            return cruxCrop;
        }
        return new BlockMysticalCrop(name);
    }

    @Desugar
    public record Pair<T, E> (T one, E two) {}
}
