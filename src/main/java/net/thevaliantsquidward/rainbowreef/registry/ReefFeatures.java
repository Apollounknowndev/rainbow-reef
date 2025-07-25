package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.world.feature.AnemoneFeature;
import net.thevaliantsquidward.rainbowreef.world.feature.StarfishFeature;
import net.thevaliantsquidward.rainbowreef.world.feature.config.AnemoneConfig;
import net.thevaliantsquidward.rainbowreef.world.feature.config.StarfishConfig;

public interface ReefFeatures {
    Feature<AnemoneConfig> ANEMONE = register("anemone", new AnemoneFeature());
    Feature<StarfishConfig> STARFISH = register("starfish", new StarfishFeature());

    private static <T extends FeatureConfiguration> Feature<T> register(String name, Feature<T> feature) {
        return Registry.register(BuiltInRegistries.FEATURE, RainbowReef.id(name), feature);
    }

    static void init() {

    }
}