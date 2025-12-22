package com.blakebr0.mysticalagriculture.blocks.reprocessor;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileEssenceReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileUltimateReprocessor;

public class BlockUltimateReprocessor extends BlockEssenceReprocessor {

    private TileUltimateReprocessor tileForInfo = new TileUltimateReprocessor();

    public BlockUltimateReprocessor() {
        super("ultimate_reprocessor");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileUltimateReprocessor();
    }

    @Override
    public String getTooltipColor() {
        return Colors.DARK_GRAY;
    }

    @Override
    public TileEssenceReprocessor getTileForInfo() {
        return this.tileForInfo;
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && ModConfig.confUltimateReprocessor;
    }
}
