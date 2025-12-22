package com.blakebr0.mysticalagriculture.blocks.soulstone;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

public class BlockSoulstoneStairs extends BlockStairs {

    public BlockSoulstoneStairs(String name, IBlockState modelState) {
        super(modelState);
        this.setTranslationKey("ma." + name);
        this.setRegistryName(name);
        this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
    }
}
