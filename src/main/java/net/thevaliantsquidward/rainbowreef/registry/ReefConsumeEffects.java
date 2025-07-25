package net.thevaliantsquidward.rainbowreef.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.items.ClearNegativeEffectsConsumeEffect;
import net.thevaliantsquidward.rainbowreef.items.KillConsumeEffect;

public interface ReefConsumeEffects {
    ConsumeEffect.Type<ClearNegativeEffectsConsumeEffect> CLEAR_NEGATIVE_EFFECTS = register(
        "clear_negative_effects",
        ClearNegativeEffectsConsumeEffect.CODEC,
        ClearNegativeEffectsConsumeEffect.STREAM_CODEC
    );

    ConsumeEffect.Type<KillConsumeEffect> KILL = register(
        "kill",
        KillConsumeEffect.CODEC,
        KillConsumeEffect.STREAM_CODEC
    );

    private static <T extends ConsumeEffect> ConsumeEffect.Type<T> register(String name, MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        return Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, RainbowReef.id(name), new ConsumeEffect.Type<>(codec, streamCodec));
    }

    static void init() {

    }
}
