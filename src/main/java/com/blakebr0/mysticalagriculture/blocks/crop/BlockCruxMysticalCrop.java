package com.blakebr0.mysticalagriculture.blocks.crop;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCruxMysticalCrop extends BlockMysticalCrop {

    private IBlockState root;

    public BlockCruxMysticalCrop(String name) {
        super(name);
    }

    public IBlockState getRoot() {
        return root;
    }

    public void setRoot(IBlockState root) {
        this.root = root;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        this.checkAndDropBlock(world, pos, state);
        if (world.getBlockState(pos.down(2)) != this.getRoot())
            return;

        int i = this.getAge(state);
        if (world.getLightFromNeighbors(pos.up()) >= 9) {
            if (world.getBlockState(pos.down(2)) == this.getRoot()) {
                if (i < this.getMaxAge()) {
                    float f = getGrowthChance(this, world, pos);
                    if (rand.nextInt((int) (35.0F / f) + 1) == 0) {
                        world.setBlockState(pos, this.withAge(i + 1), 2);
                    }
                }
            }
        }
    }
}
