# Mystical Agriculture Community Edition

Adds Resource Crops, Armor, Tools, and other cool things!

## What does this add?

This fork primarily adds the capability to generate plants and
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

| field        | required | default | values                             | description                                             |
|--------------|----------|---------|------------------------------------|---------------------------------------------------------|
| name         | true     |         | [a-z,0-9,.,-,_,/]                  | The produced item name. Follows path rules.             |
| tier         | false    | 1       | 1-5                                | The desired item tier.                                  |
| input_item   | false    |         | \<namespace>:\<item>[#\<metadata>] | The item to put in the four corners as a default recipe |
| output_item  | false    |         | \<namespace>:\<item>[#\<metadata>] | The item that the essence produces                      |
| output_count | false    | 1       | 1-64                               | The amount the output will create                       |
| type         | false    | NONE    | NONE, STAR, BOX                    | What pattern the output craft will be.                  |

A full item might look as follows:

```json5
{
  "name": "example",
  "tier": 3,
  "input_item": "mysticalagriculture:ingot_storage#2",
  "output_item": "mysticalagriculture:soulstone",
  "output_count": 64,
  "type": "STAR"
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

MACE follows standard minecraft resource rules, just in the `.minecraft/resources` folder instead of `assets`.
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
MACE only sets the translation key.

- `resources/mysticalagriculture/lang/<locale>.lang`

## What does this not add

Tier 6 crops.

## Original Project:
[![](http://cf.way2muchnoise.eu/full_246640_downloads.svg)](https://minecraft.curseforge.com/projects/mystical-agriculture) [![](http://cf.way2muchnoise.eu/versions/246640.svg)](https://minecraft.curseforge.com/projects/mystical-agriculture)