package com.blakebr0.mysticalagriculture;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.blakebr0.mysticalagriculture.items.ModItems;

public class MACreativeTab extends CreativeTabs {

    public MACreativeTab() {
        super(MysticalAgriculture.MOD_ID);
    }

    @Override
    public ItemStack createIcon() {
        return ModItems.itemCrafting.itemSupremiumEssence;
    }
}
