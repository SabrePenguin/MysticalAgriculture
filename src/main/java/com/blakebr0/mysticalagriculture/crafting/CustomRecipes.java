package com.blakebr0.mysticalagriculture.crafting;

import static com.blakebr0.mysticalagriculture.crafting.ModRecipes.getCraftingSeed;
import static com.blakebr0.mysticalagriculture.crafting.ModRecipes.getEssence;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.custom.CustomItem;
import com.blakebr0.mysticalagriculture.items.custom.CustomItems;
import com.blakebr0.mysticalagriculture.items.custom.CustomRecipeType;

public class CustomRecipes {

    public static void addSeedRecipe(CustomItem type, Object input) {
        RecipeHelper.addShapedRecipe(new ItemStack(type.seed(), 1, 0),
                "MEM",
                "ESE",
                "MEM",
                'E', getEssence(type.tier()),
                'S', getCraftingSeed(type.tier()),
                'M', input);
    }

    public static void init() {
        for (CustomItem item : CustomItems.getCustomItems()) {
            if (item.input() != null) {
                String[] itemStack = item.input().split("#", 2);
                Item i = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemStack[0]));
                if (i != null) {
                    try {
                        ItemStack stack = itemStack.length == 1 ? new ItemStack(i) :
                                new ItemStack(i, 1, Integer.parseInt(itemStack[1]));
                        addSeedRecipe(item, stack);
                    } catch (NumberFormatException error) {
                        MysticalAgriculture.LOGGER.error("Unable to use metadata \"{}\" on item \"{}\"", itemStack[1],
                                itemStack[0]);
                    }
                }
            }
            if (item.type() == CustomRecipeType.BOX) {
                String[] itemStack = item.output().split("#", 2);
                Item o = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemStack[0]));
                if (o != null) {
                    try {
                        ItemStack stack = itemStack.length == 1 ? new ItemStack(o, item.outputCount()) :
                                new ItemStack(o, item.outputCount(), Integer.parseInt(itemStack[1]));
                        EssenceRecipes.addEssenceRecipe(stack,
                                "EEE", "E E", "EEE", 'E', new ItemStack(item.crop(), 1));
                    } catch (NumberFormatException error) {
                        MysticalAgriculture.LOGGER.error("Unable to use metadata \"{}\" on item \"{}\"", itemStack[1],
                                itemStack[0]);
                    }
                }
            }
            if (item.type() == CustomRecipeType.CROSS) {
                String[] itemStack = item.output().split("#", 2);
                Item o = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemStack[0]));
                if (o != null) {
                    try {
                        ItemStack stack = itemStack.length == 1 ? new ItemStack(o, item.outputCount()) :
                                new ItemStack(o, item.outputCount(), Integer.parseInt(itemStack[1]));
                        EssenceRecipes.addEssenceRecipe(stack,
                                " E ", "EEE", " E ", 'E', new ItemStack(item.crop(), 1));
                    } catch (NumberFormatException error) {
                        MysticalAgriculture.LOGGER.error("Unable to use metadata \"{}\" on item \"{}\"", itemStack[1],
                                itemStack[0]);
                    }
                }
            }
        }
    }
}
