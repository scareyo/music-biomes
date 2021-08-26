package me.scarey.musicbiomes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class MusicBiomes implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		// Define a state wrapper than can be accessed from the lambda functions
		var wrapper = new Object() {
			Identifier biome = null;
			MusicSound music = null;
		};

		// Register all biomes upon joining a game session
		ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
			Registry<SoundEvent> soundRegistry = handler.getRegistryManager().get(Registry.SOUND_EVENT_KEY);
			for (Identifier biomeId : handler.getRegistryManager().get(Registry.BIOME_KEY).getIds()) {
				SoundEvent sound = new SoundEvent(biomeToSoundEvent(biomeId));

				// Register the sound if it doesn't exist
				if (soundRegistry.get(sound.getId()) == null)
					Registry.register(Registry.SOUND_EVENT, sound.getId(), sound);
			}
		});

		// Register client tick listener
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			if(client.player != null) {
				DynamicRegistryManager registryManager = client.world.getRegistryManager();
				BlockPos pos = client.player.getBlockPos();
				Identifier biome = registryManager.get(Registry.BIOME_KEY).getId(client.world.getBiome(pos));

				// Check if the player has switched biomes or if the song has stopped
				if (biome != wrapper.biome || !client.getMusicTracker().isPlayingType(wrapper.music)) {
					SoundEvent soundEvent = registryManager.get(Registry.SOUND_EVENT_KEY).get(biomeToSoundEvent(biome));

					MusicSound music = new MusicSound(soundEvent, 0, 0, true);
					client.getMusicTracker().stop();
					client.getMusicTracker().play(music);

					wrapper.biome = biome;
					wrapper.music = music;
				}
			}
		});
	}

	Identifier biomeToSoundEvent(Identifier biomeId) {
		// Music identifiers are registered using the following pattern:
		// 		musicbiomes:music.<namespace>.<biome>
		return new Identifier("musicbiomes:music." + biomeId.toString().replace(':', '.'));
	}
}
