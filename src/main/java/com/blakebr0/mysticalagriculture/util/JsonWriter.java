package com.blakebr0.mysticalagriculture.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

import net.minecraftforge.fml.common.Loader;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonWriter {

    public static final Gson GSON;
    static {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        GSON = builder.create();
    }

    public static void writeNewAgricraftJson(String cropName, String cruxName) {
        writeNewAgricraftJson(cropName, cropName + "_essence", cruxName);
    }

    public static void writeNewAgricraftJson(String cropName, String essenceName, String cruxName) {
        File resource_dir = new File(Loader.instance().getConfigDir(),
                "agricraft/json/defaults/mod_mysticalagriculture/plants");
        if (!resource_dir.exists()) {
            try {
                resource_dir.mkdirs();
            } catch (SecurityException exception) {
                MysticalAgriculture.LOGGER.error("Unable to create directory {}", resource_dir);
                return;
            }
        }
        if (!resource_dir.isDirectory()) {
            MysticalAgriculture.LOGGER.error("{} is not a directory", resource_dir);
            return;
        }
        if (new File(resource_dir, cropName + "_plant.json").exists())
            return;
        try (Writer writer = new FileWriter(new File(resource_dir, cropName + "_plant.json"))) {
            GSON.toJson(createJson(cropName, essenceName, cruxName), writer);
        } catch (IOException exception) {
            MysticalAgriculture.LOGGER.error("Unable to write to file {}/{}", resource_dir, "");
        }
    }

    private static JsonObject createJson(String name, String essenceName, String cruxName) {
        String capitalizedName = capitalize(name);
        JsonObject object = new JsonObject();
        object.addProperty("path", "mod_mysticalagriculture/plants/" + name + "_plant.json");
        object.addProperty("enabled", true);
        object.addProperty("id", "mysticalagriculture:" + name + "_seeds");
        object.addProperty("plant_name", capitalizedName + " Crop");
        object.addProperty("seed_name", capitalizedName + " Seeds");
        {
            JsonObject seedItems = new JsonObject();
            seedItems.addProperty("item", "mysticalagriculture:" + name + "_seeds");
            seedItems.addProperty("meta", 0);
            seedItems.addProperty("tags", ""); // intentionally empty
            seedItems.addProperty("ignoreMeta", false);
            {
                JsonArray tags = new JsonArray();
                tags.add("*");
                seedItems.add("ignoreTags", tags);
            }
            seedItems.addProperty("useOreDict", false);

            JsonArray seedArray = new JsonArray();
            seedArray.add(seedItems);
            object.add("seed_items", seedArray);
        }
        {
            JsonObject description = new JsonObject();
            description.add("translations", new JsonObject());
            description.addProperty("default", capitalizedName + " Seeds.");
            object.add("description", description);
        }

        object.addProperty("growth_chance", 0.85);
        object.addProperty("growth_bonus", 0.02);
        object.addProperty("bonemeal", false);
        object.addProperty("tier", 1);
        object.addProperty("weedable", false);
        object.addProperty("aggressive", false);
        object.addProperty("spread_chance", 0.1);
        object.addProperty("spawn_chance", 0.0);
        object.addProperty("grass_drop_chance", 0.0);
        object.addProperty("seed_drop_chance", 1.0);
        object.addProperty("seed_drop_bonus", 0.0);
        {
            JsonObject products = new JsonObject();
            JsonArray productArray = new JsonArray();
            {
                JsonObject product = new JsonObject();
                product.addProperty("min", 1);
                product.addProperty("max", 5);
                product.addProperty("chance", 0.9);
                product.addProperty("required", true);
                product.addProperty("item", "mysticalagriculture:" + essenceName);
                product.addProperty("meta", 0);
                product.addProperty("tags", ""); // Deliberately empty
                product.addProperty("ignoreMeta", false);
                product.add("ignoreTags", new JsonArray());
                product.addProperty("useOreDict", false);

                productArray.add(product);
            }
            products.add("products", productArray);
            object.add("products", products);
        }
        {
            JsonObject requirement = new JsonObject();
            requirement.addProperty("min_light", 10);
            requirement.addProperty("max_light", 16);
            JsonArray soils = new JsonArray();
            soils.add("farmland_soil");
            requirement.add("soils", soils);
            JsonArray conditions = new JsonArray();
            if (cruxName != null && !cruxName.isEmpty()) {
                String[] metaName = cruxName.split("#", 2);
                boolean ignoreMeta = true;
                int meta = 0;
                try {
                    if (metaName.length == 2) {
                        meta = Integer.parseInt(metaName[1]);
                        ignoreMeta = false;
                    }
                } catch (NumberFormatException ignored) {}
                JsonObject condition = new JsonObject();
                condition.addProperty("amount", 1);
                condition.addProperty("min_x", 0);
                condition.addProperty("min_y", -2);
                condition.addProperty("min_z", 0);
                condition.addProperty("max_x", 0);
                condition.addProperty("max_y", -2);
                condition.addProperty("max_z", 0);
                condition.addProperty("item", metaName[0]);
                condition.addProperty("meta", meta);
                condition.addProperty("tags", "");
                condition.addProperty("ignoreMeta", ignoreMeta);
                condition.add("ignoreTags", new JsonArray());
                condition.addProperty("useOreDict", false);
                conditions.add(condition);
            }
            requirement.add("conditions", conditions);
            object.add("requirement", requirement);
        }
        {
            JsonObject texture = new JsonObject();
            texture.addProperty("render_type", "cross");
            texture.addProperty("seed_texture", "mysticalagriculture:items/" + name + "_seeds");
            JsonArray plant_textures = new JsonArray();
            plant_textures.add("mysticalagriculture:blocks/crop0");
            plant_textures.add("mysticalagriculture:blocks/crop1");
            plant_textures.add("mysticalagriculture:blocks/crop2");
            plant_textures.add("mysticalagriculture:blocks/crop2");
            plant_textures.add("mysticalagriculture:blocks/crop3");
            plant_textures.add("mysticalagriculture:blocks/crop4");
            plant_textures.add("mysticalagriculture:blocks/crop4");
            plant_textures.add("mysticalagriculture:blocks/" + name + "_crop");
            texture.add("plant_textures", plant_textures);
            object.add("texture", texture);
        }
        return object;
    }

    private static String capitalize(String name) {
        if (name == null || name.isEmpty())
            return "";
        String[] n = name.split("_");
        StringBuilder builder = new StringBuilder();
        for (String split : n) {
            builder.append(split.substring(0, 1).toUpperCase(Locale.ROOT)).append(split.substring(1)).append(' ');
        }
        return builder.toString().trim();
    }
}
