package com.blakebr0.mysticalagriculture.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.entity.ModEntities;
import com.blakebr0.mysticalagriculture.util.resources.ExternalResourcePack;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        if (ModConfig.confEnableCustomSeeds) {
            if (ExternalResourcePack.ensurePackMcmetaExists()) {
                ExternalResourcePack.injectExternalResources();
            }
        }
        ModEntities.initModels();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}
