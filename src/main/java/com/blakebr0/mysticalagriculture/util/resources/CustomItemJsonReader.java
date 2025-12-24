package com.blakebr0.mysticalagriculture.util.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import net.minecraftforge.fml.common.Loader;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.custom.CustomRecipeType;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class CustomItemJsonReader {

    public static final Gson GSON = new Gson();
    public static final JsonParser parser = new JsonParser();

    public static Set<CustomItemHolder> loadResources() {
        File resource_dir = new File(Loader.instance().getConfigDir(), "mysticalagriculture");
        Set<CustomItemHolder> items = new HashSet<>();
        try (Stream<Path> path = Files.walk(resource_dir.toPath())) {
            path.filter(Files::isRegularFile)
                    .filter(file -> file.getFileName().toString().endsWith(".json"))
                    .forEach(file -> items.addAll(CustomItemJsonReader.loadJson(file.toFile())));
        } catch (IOException e) {
            MysticalAgriculture.LOGGER.error("Unable to read file: {}", e.getLocalizedMessage());
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
                        for (JsonElement element : root.getAsJsonArray()) {
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

        public String name;
        public int tier;
        public String input_item;
        public String output_item;
        public int output_count;
        public CustomRecipeType type;

        public CustomItemHolder() {
            this.type = CustomRecipeType.NONE;
        }

        public static CustomItemHolder validate(JsonObject object) {
            CustomItemHolder item = GSON.fromJson(object, CustomItemHolder.class);
            // This is necessary. Without this, there's no registry name
            if (item.name == null || item.name.isEmpty()) {
                return null;
            }
            // The user may not want to create a recipe for the seeds
            if (item.input_item == null || item.input_item.isEmpty()) {
                item.input_item = null;
            }
            // The user has not specified an item output, so we don't actually
            // want to auto-generate a recipe for them
            if (item.output_item == null && item.type != CustomRecipeType.NONE) {
                item.type = CustomRecipeType.NONE;
            }
            if (item.output_count <= 0) {
                item.output_count = 1;
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
