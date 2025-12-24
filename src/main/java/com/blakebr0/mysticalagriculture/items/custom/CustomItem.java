package com.blakebr0.mysticalagriculture.items.custom;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.github.bsideup.jabel.Desugar;

@Desugar
public record CustomItem(String name, Item crop, Item seed, Block block, int tier, String input, int outputCount, String output,
                         CustomRecipeType type) {

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomItem other) {
            return name.equals(other.name);
        }
        return false;
    }
}
