package com.blakebr0.mysticalagriculture.crafting;

import static com.blakebr0.mysticalagriculture.crafting.ModRecipes.getCraftingSeed;
import static com.blakebr0.mysticalagriculture.crafting.ModRecipes.getEssence;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import com.blakebr0.cucumber.helper.RecipeHelper;
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
            Item i = ForgeRegistries.ITEMS.getValue(new ResourceLocation(item.input()));
            if (i != null) {
                ItemStack stack = new ItemStack(i);

                addSeedRecipe(item, stack);
                if (item.type() == CustomRecipeType.BOX) {
                    Item o = ForgeRegistries.ITEMS.getValue(new ResourceLocation(item.output()));
                    EssenceRecipes.addEssenceRecipe(new ItemStack(o, item.outputCount()),
                            "EEE", "E E", "EEE", 'E', new ItemStack(item.crop(), 1));
                }
                if (item.type() == CustomRecipeType.CROSS) {
                    Item o = ForgeRegistries.ITEMS.getValue(new ResourceLocation(item.output()));
                    EssenceRecipes.addEssenceRecipe(new ItemStack(o, item.outputCount()),
                            " E ", "EEE", " E ", 'E', new ItemStack(item.crop(), 1));
                }
            }
        }
    }
}
