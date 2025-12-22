package com.blakebr0.mysticalagriculture.blocks.reprocessor;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileEssenceReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileSuperiumReprocessor;

public class BlockSuperiumReprocessor extends BlockEssenceReprocessor {

    private TileSuperiumReprocessor tileForInfo = new TileSuperiumReprocessor();

    public BlockSuperiumReprocessor() {
        super("superium_reprocessor");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileSuperiumReprocessor();
    }

    @Override
    public String getTooltipColor() {
        return Colors.AQUA;
    }

    @Override
    public TileEssenceReprocessor getTileForInfo() {
        return this.tileForInfo;
    }
}
