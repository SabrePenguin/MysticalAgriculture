package com.blakebr0.mysticalagriculture.blocks;

import java.util.Random;

import net.minecraft.block.BlockTorch;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;

public class BlockMinersTorch extends BlockTorch implements IEnableable {

    public BlockMinersTorch() {
        this.setTranslationKey("ma." + "miners_torch");
        this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
        this.setLightLevel(1.0F);
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public boolean isEnabled() {
        return ModConfig.confGearModuleOverride;
    }
}
