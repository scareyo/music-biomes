# Music Biomes
Music Biomes is a Minecraft mod that allows you to assign soundtracks to biomes.

## Configuration
All sound events are defined as ```music.<namespace>.<biome>```. Add the following to the ```sounds.json``` file in your resource pack:
```json
{
  "music.minecraft.beach": {
    "sounds": [
      {
        "name": "<namespace>:<your_beach_sound_file>",
        "stream": true
      }
    ]
  },
  "music.minecraft.forest": {
    "sounds": [
      {
        "name": "<namespace>:<your_forest_sound_file>",
        "stream": true
      }
    ]
  },
  "music.minecraft.ocean": {
    "sounds": [
      {
        "name": "<namespace>:<your_ocean_sound_file>",
        "stream": true
      }
    ]
  }
}
```

See https://minecraft.fandom.com/wiki/Sounds.json for more info.