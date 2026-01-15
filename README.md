# Mystical Agriculture Community Edition

Adds Resource Crops, Armor, Tools, and other cool things!

## What does this add?

Mace adds the ability to generate plants and
resources dynamically.

### Creating custom plants:

Set `enable_custom_seeds` to true

#### Adding the item:

Create a json file in config/mysticalagriculture. Multiple json files can be created for organization. 
Subfolders can be used as well. Fill it out as one of the two following:

```json5
[
  {
    "name": "placeholder",
    "tier": 3,
    "type": "BOX"
  },
  {
    "name": "other_plant"
  }
]
```

or

```json5
{
  "name": "placeholder",
  "tier": 3
}
```

Object type follows the format

| field        | required | default | values                              | description                                                     |
|--------------|----------|---------|-------------------------------------|-----------------------------------------------------------------|
| name         | true     |         | [a-z,0-9,.,-,_,/]                   | The produced item name. Follows path rules.                     |
| tier         | false    | 1       | 1-MAXINT                            | The desired item tier. 1-5 support auto-generated seed recipes. |
| input_item   | false    |         | \<namespace>:\<item>[#\<metadata>]  | The item to put in the four corners as a default recipe         |
| output_item  | false    |         | \<namespace>:\<item>[#\<metadata>]  | The item that the essence produces                              |
| output_count | false    | 1       | 1-64                                | The amount the output will create                               |
| type         | false    | NONE    | NONE, STAR, BOX                     | What pattern the output craft will be.                          |
| crux         | false    |         | \<namespace>:\<block>[#\<metadata>] | The crux block needed for the crop to grow.                     |

A full item might look as follows, which generates `example_seeds`, `example_essence`, and the crop. This
would require a crux of soulstone underneath.

```json5
{
  "name": "example",
  "tier": 3,
  "input_item": "mysticalagriculture:ingot_storage#2",
  "output_item": "mysticalagriculture:soulstone",
  "output_count": 64,
  "type": "STAR",
  "crux": "mysticalagriculture:soulstone"
}
```

#### Giving it texture

If you added the json, you may have noticed a lack of texture. This section helps address
that.

##### Enabling default textures

Set `enable_default_texture` to true. This adds a "default" texture by creating an additional item
and referencing the texture of it. All custom seeds and essence that do not have textures will now use
these textures.

##### Setting textures

Mace follows standard minecraft resource rules, just in the `.minecraft/resources` folder instead of `assets`.
Simply put, a custom seed needs the following files:

- `resources/mysticalagriculture/blockstates/<name>_crop.json`
- `resources/mysticalagriculture/models/block/<name>_crop.json`
- `resources/mysticalagriculture/models/item/<name>_crop.json`
- `resources/mysticalagriculture/models/item/<name>_essence.json`
- `resources/mysticalagriculture/models/item/<name>_seeds.json`
- `resources/mysticalagriculture/textures/blocks/<name>_crop.png`
- `resources/mysticalagriculture/textures/items/<name>_essence.png`
- `resources/mysticalagriculture/textures/items/<name>_seeds.png`

*The textures files can theoretically be anywhere under the `textures` folder, so long as the json indicates as such.

Optionally a lang file can also be added with the entry. It is HIGHLY recommended to add this, as
MA only sets the translation key.

- `resources/mysticalagriculture/lang/<locale>.lang`

#### Adding Agricraft support

Adding the support of agricraft is incredibly easy.

##### Automatic

Agricraft support is now even easier. Simply ensure `generate_agricraft_generation` is true. This will generate
a new json for all seeds, filling it out with cruxes and items.

##### Manual

1. Add `<name>_plant.json` to `config/agricraft/json/defaults/mod_mysticalagriculture`. I recommend copying one that
already exists.
2. Modify `<name>_plant.json` to use the name you set in the config.
3. Point towards a texture that exists. If `enable_default_texture` is true,
`mysticalagriculture:default_<seeds|essence|crop>` can be used instead.

## What does this not add

Anything specific to Mystical Agradditions.

## Credit:

Original project
[![](http://cf.way2muchnoise.eu/full_246640_downloads.svg)](https://minecraft.curseforge.com/projects/mystical-agriculture) [![](http://cf.way2muchnoise.eu/versions/246640.svg)](https://minecraft.curseforge.com/projects/mystical-agriculture)