package net.thevaliantsquidward.rainbowreef.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

public interface ReefJukeboxSongs {
    ResourceKey<JukeboxSong> CRAB = create("crab");

    private static ResourceKey<JukeboxSong> create(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, RainbowReef.id(name));
    }
}
