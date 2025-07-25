package net.thevaliantsquidward.rainbowreef.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record AnemoneConfig(HolderSet<Block> blocks) implements FeatureConfiguration {
    public static final Codec<AnemoneConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("blocks").forGetter(AnemoneConfig::blocks)
    ).apply(instance, AnemoneConfig::new));
}
