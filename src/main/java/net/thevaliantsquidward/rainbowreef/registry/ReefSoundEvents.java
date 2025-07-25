package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

public interface ReefSoundEvents {
    SoundEvent JELLYZAP = register("jellyzap");
    SoundEvent JELLYHIT = register("jellyhit");
    SoundEvent JELLYBOUNCE = register("jellybounce");
    SoundEvent MUSIC_DISC_CLAW = register("music_disc.claw");

    private static SoundEvent register(String name) {
        var id = RainbowReef.id(name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    static void init() {

    }
}
