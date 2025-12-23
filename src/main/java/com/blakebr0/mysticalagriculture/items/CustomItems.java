package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.util.resources.CustomItemJsonReader;

import java.util.Set;

public class CustomItems {

    public void init() {
        if (ModConfig.confEnableCustomSeeds) {
            Set<CustomItemJsonReader.CustomItemHolder> out = CustomItemJsonReader.loadResources();
        }
    }
}
