package com.blakebr0.mysticalagriculture.blocks;

import javax.annotation.Nonnull;

import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

public class BlockSoulGlassPane extends BlockPane {

    public BlockSoulGlassPane() {
        super(Material.GLASS, false);
        String name = "soul_glass_pane";
        this.setTranslationKey("ma." + name);
        this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
        this.setHardness(0.3F);
        this.setSoundType(SoundType.GLASS);
    }

    @Override
    @Nonnull
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
