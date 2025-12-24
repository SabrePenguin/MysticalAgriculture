package com.blakebr0.mysticalagriculture.items.custom;

import net.minecraft.item.Item;

import com.blakebr0.mysticalagriculture.util.resources.CustomItemJsonReader;
import com.github.bsideup.jabel.Desugar;

@Desugar
public record CustomItem(String name, Item crop, Item seed, int tier, String input, int outputCount, String output,
                         CustomItemJsonReader.CustomItemHolder.RecipeType type) {

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomItem other) {
            return name.equals(other.name);
        }
        return false;
    }
}
