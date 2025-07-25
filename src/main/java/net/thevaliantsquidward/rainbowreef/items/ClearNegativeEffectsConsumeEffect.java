package net.thevaliantsquidward.rainbowreef.items;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import net.thevaliantsquidward.rainbowreef.registry.ReefConsumeEffects;

public record ClearNegativeEffectsConsumeEffect() implements ConsumeEffect {
    public static final ClearNegativeEffectsConsumeEffect INSTANCE = new ClearNegativeEffectsConsumeEffect();
    public static final MapCodec<ClearNegativeEffectsConsumeEffect> CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, ClearNegativeEffectsConsumeEffect> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return ReefConsumeEffects.CLEAR_NEGATIVE_EFFECTS;
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity entity) {
        boolean applied = false;
        for (MobEffectInstance effect : entity.getActiveEffects()) {
            if (!effect.isAmbient() && !effect.getEffect().value().isInstantenous() && !effect.getEffect().value().isBeneficial()) {
                entity.removeEffect(effect.getEffect());
                applied = true;
            }
        }
        return applied;
    }
}
