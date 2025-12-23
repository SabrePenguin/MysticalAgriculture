package com.blakebr0.mysticalagriculture.util.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.*;
import net.minecraft.client.Minecraft;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.google.gson.stream.JsonReader;

public class CustomItemJsonReader {

    private static final File RESOURCE_DIR = new File(Minecraft.getMinecraft().gameDir,
            "resources/mysticalagriculture");
    public static final Gson GSON = new Gson();
    public static final JsonParser parser = new JsonParser();

    public static Set<CustomItemHolder> loadResources() {
        Set<CustomItemHolder> items = new HashSet<>();
        File[] files = RESOURCE_DIR.listFiles();
        if (files != null) {
            for (File file : files) {
                items.addAll(loadJson(file));
            }
        }
        return items;
    }

    private static Set<CustomItemHolder> loadJson(File file) {
        Set<CustomItemHolder> items = new HashSet<>();
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            reader.setLenient(true);
            {
                JsonElement root = parser.parse(reader);
                try {
                    if (root.isJsonObject()) {
                        CustomItemHolder newItem = CustomItemHolder.validate(root.getAsJsonObject());
                        if (newItem != null) {
                            items.add(newItem);
                        }
                    } else if (root.isJsonArray()) {
                        for(JsonElement element: root.getAsJsonArray()) {
                            CustomItemHolder newItem = CustomItemHolder.validate(element.getAsJsonObject());
                            if (newItem != null) {
                                items.add(newItem);
                            }
                        }
                    }
                } catch (JsonSyntaxException exception) {
                    MysticalAgriculture.LOGGER.error("Unable to deserialize: {}", exception.getLocalizedMessage());
                }
            }
        } catch (FileNotFoundException error) {
            MysticalAgriculture.LOGGER.error("Failed to find the file {}", file);
        }
        return items;
    }

    public static class CustomItemHolder {

        public enum RecipeType {
            BOX,
            CROSS,
            NONE
        }

        public String name;
        public int tier;
        public RecipeType type;

        public CustomItemHolder() {
            this.type = RecipeType.NONE;
        }

        public static CustomItemHolder validate(JsonObject object) {
            CustomItemHolder item = GSON.fromJson(object, CustomItemHolder.class);
            if (item.name == null || item.name.isEmpty()) {
                return null;
            }
            return item;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof CustomItemHolder other) {
                return this.name.equals(other.name);
            }
            return false;
        }
    }
}
