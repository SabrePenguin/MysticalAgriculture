# Examples

In this folder, you'll find two primary folders.

- `config`: contains two subfolders, `mysticalagriculture` and `agricraft`
- `resources`: Contains resources for custom plants. See main README about the files a plant requires.

## `mysticalagriculture` config

This folder can contain any number of json files in any subfolder. This is where
your custom plants will be generated from. There are two example files provided.

- `placeholder_seed_multiple.json`: Shows that multiple plants can be declared in the same file.
- `placeholder_seed_single.json`: Shows that a single plant can be declared in a file.

## `agricraft` config

This folder contains a single important subfolder. `json/defaults/mod_mysticalagriculture`. Inside
is a json file which declares support for the custom placeholder seed in `mysticalagriculture`'s config folder.
There is a second file `placeholder_plant_crux.json` which demonstrates crux support.