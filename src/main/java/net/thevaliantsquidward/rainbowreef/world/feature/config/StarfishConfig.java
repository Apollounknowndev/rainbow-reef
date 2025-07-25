package net.thevaliantsquidward.rainbowreef.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record StarfishConfig(HolderSet<Block> blocks, HolderSet<Block> canPlaceOn) implements FeatureConfiguration {
    public static final Codec<StarfishConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("blocks").forGetter(StarfishConfig::blocks),
        RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_place_on").forGetter(StarfishConfig::canPlaceOn)
    ).apply(instance, StarfishConfig::new));
}
