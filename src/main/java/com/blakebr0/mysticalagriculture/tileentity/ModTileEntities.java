package com.blakebr0.mysticalagriculture.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

import com.blakebr0.cucumber.helper.ResourceHelper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileInferiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileIntermediumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TilePrudentiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileSuperiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileSupremiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileUltimateFurnace;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileInferiumReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileIntermediumReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TilePrudentiumReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileSuperiumReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileSupremiumReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileUltimateReprocessor;

public class ModTileEntities {

    public static void init() {
        if (ModConfig.confSeedReprocessor) {
            GameRegistry.registerTileEntity(TileEntitySeedReprocessor.class,
                    ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "ma_seed_reprocessor"));
            GameRegistry.registerTileEntity(TileInferiumReprocessor.class,
                    ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "inferium_reprocessor"));
            GameRegistry.registerTileEntity(TilePrudentiumReprocessor.class,
                    ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "prudentium_reprocessor"));
            GameRegistry.registerTileEntity(TileIntermediumReprocessor.class,
                    ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "intermedium_reprocessor"));
            GameRegistry.registerTileEntity(TileSuperiumReprocessor.class,
                    ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "superium_reprocessor"));
            GameRegistry.registerTileEntity(TileSupremiumReprocessor.class,
                    ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "supremium_reprocessor"));

            if (ModConfig.confUltimateReprocessor) {
                GameRegistry.registerTileEntity(TileUltimateReprocessor.class,
                        ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "ultimate_reprocessor"));
            }
        }

        if (ModConfig.confEssenceFurnaces) {
            GameRegistry.registerTileEntity(TileInferiumFurnace.class,
                    ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "ma_inferium_furnace"));
            GameRegistry.registerTileEntity(TilePrudentiumFurnace.class,
                    ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "ma_prudentium_furnace"));
            GameRegistry.registerTileEntity(TileIntermediumFurnace.class,
                    ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "ma_intermedium_furnace"));
            GameRegistry.registerTileEntity(TileSuperiumFurnace.class,
                    ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "ma_superium_furnace"));
            GameRegistry.registerTileEntity(TileSupremiumFurnace.class,
                    ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "ma_supremium_furnace"));

            if (ModConfig.confUltimateFurnace) {
                GameRegistry.registerTileEntity(TileUltimateFurnace.class,
                        ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "ma_ultimate_furnace"));
            }
        }
    }
}
