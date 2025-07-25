package net.thevaliantsquidward.rainbowreef.entity.interfaces;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;

public interface ReefVariantHolder<V extends ReefVariant> {
    V getVariant();
    void setVariant(V variant);

    V getDefaultVariant();
    Codec<V> getVariantCodec();
    DataComponentType<V> getVariantComponent();
    V[] getAllVariants();

    default V selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        return getAllVariants()[random.nextInt(getAllVariants().length)];
    }
}
