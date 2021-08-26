# Music Biomes
Music Biomes is a Minecraft mod that allows you to assign soundtracks to biomes.

## Configuration
### Resource pack
Your resource pack should be structured as following:
```
├── pack.mcmeta
└── assets
    ├── musicbiomes
    |   └── sounds.json
    |    
    └── <your_namespace>
        └── sounds
            ├── <your_beach_sound>.ogg
            ├── <your_forest_sound_1>.ogg
            └── <your_forest_sound_2>.ogg
```
Note that filenames must contain only ```[a-z 0-9 / . _ -]```

See https://minecraft.fandom.com/wiki/Resource_Pack for more information on resource packs.

#### sounds.json
All sound events are defined as ```music.<namespace>.<biome>```. Add the following to the ```sounds.json``` file in your resource pack:
```json
{
  "music.minecraft.beach": {
    "sounds": [
      {
        "name": "<namespace>:<your_beach_sound>",
        "stream": true
      }
    ]
  },
  "music.minecraft.forest": {
    "sounds": [
      {
        "name": "<namespace>:<your_forest_sound_1>",
        "stream": true
      },
      {
        "name": "<namespace>:<your_forest_sound_2>",
        "stream": true
      }
    ]
  },
}
```
See https://minecraft.fandom.com/wiki/Sounds.json for more information
