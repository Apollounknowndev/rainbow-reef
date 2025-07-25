package net.thevaliantsquidward.rainbowreef.items;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import net.thevaliantsquidward.rainbowreef.registry.ReefConsumeEffects;

public record KillConsumeEffect(ResourceKey<DamageType> damageType, float chance) implements ConsumeEffect {
    public static final MapCodec<KillConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        ResourceKey.codec(Registries.DAMAGE_TYPE).fieldOf("damage_type").forGetter(KillConsumeEffect::damageType),
        Codec.floatRange(0, 1).fieldOf("chance").forGetter(KillConsumeEffect::chance)
    ).apply(instance, KillConsumeEffect::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, KillConsumeEffect> STREAM_CODEC = StreamCodec.composite(
        ResourceKey.streamCodec(Registries.DAMAGE_TYPE),
        KillConsumeEffect::damageType,
        ByteBufCodecs.FLOAT,
        KillConsumeEffect::chance,
        KillConsumeEffect::new
    );

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return ReefConsumeEffects.KILL;
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity entity) {
        if (level instanceof ServerLevel serverLevel && level.getRandom().nextFloat() < this.chance) {
            entity.hurtServer(serverLevel, new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(damageType)), Integer.MAX_VALUE);
            return true;
        }
        return false;
    }
}
