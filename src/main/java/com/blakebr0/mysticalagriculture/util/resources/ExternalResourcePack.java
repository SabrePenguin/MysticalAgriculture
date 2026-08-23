package com.blakebr0.mysticalagriculture.util.resources;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import com.blakebr0.mysticalagriculture.util.ModChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

public class ExternalResourcePack {

    private static final File RESOURCE_DIR = new File(Minecraft.getMinecraft().gameDir, "resources");
    private static final File PACK_META = new File(RESOURCE_DIR, "pack.mcmeta");

    @SuppressWarnings("ReadWriteStringCanBeUsed")
    public static boolean ensurePackMcmetaExists() {
        if (!RESOURCE_DIR.exists()) {
            if (!RESOURCE_DIR.mkdir()) {
                MysticalAgriculture.LOGGER.error("Unable to create directory: {}", RESOURCE_DIR);
                return false;
            }
        }
        if (!RESOURCE_DIR.isDirectory()) {
            MysticalAgriculture.LOGGER.error("{} is not a directory", RESOURCE_DIR);
            return false;
        }
        if (!PACK_META.exists()) {
            try {
                String json = "{ \"pack\": { \"pack_format\": 3, \"description\": \"Mystical Agriculture Custom Resources\" } }";
                Files.write(PACK_META.toPath(), json.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                MysticalAgriculture.LOGGER.error("Unable to generate mcmeta: {}. ", e.getLocalizedMessage());
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    public static void injectExternalResources() {
        if (ModChecker.RESOURCE_LOADER || Loader.isModLoaded("citnbt")) {
            MysticalAgriculture.LOGGER.info("ResourceLoader detected");
            return;
        }
        File resourceDir = new File(Minecraft.getMinecraft().gameDir, "resources");
        IResourcePack flatPack = new FlattenedResourcePack(resourceDir);

        Minecraft mc = Minecraft.getMinecraft();
        try {
            List<IResourcePack> defaultPacks = ObfuscationReflectionHelper.getPrivateValue(
                    Minecraft.class, mc, "defaultResourcePacks", "field_110449_ao");
            defaultPacks.remove(flatPack);

            defaultPacks.add(flatPack);
            mc.refreshResources();
        } catch (Exception e) {
            MysticalAgriculture.LOGGER.error("Unable to load resources: {}", e.getLocalizedMessage());
        }
    }
}
