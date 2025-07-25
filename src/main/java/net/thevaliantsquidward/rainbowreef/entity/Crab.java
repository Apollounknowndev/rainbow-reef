package net.thevaliantsquidward.rainbowreef.entity;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.pathfinder.PathType;
import net.thevaliantsquidward.rainbowreef.entity.base.AbstractCrab;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;

import java.time.LocalDate;
import java.time.Month;

public class Crab extends AbstractCrab<Crab.Variant> {
    public Crab(EntityType<? extends Crab> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.setPathfindingMalus(PathType.WATER_BORDER, 0.0F);
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.SALLY;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.CRAB_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    @Override
    public Variant getVariant() {
        return getAllVariants()[this.entityData.get(VARIANT)];
    }

    @Override
    public void setVariant(Variant variant) {
        this.entityData.set(VARIANT, variant.index());
    }

    @Override
    public Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        Holder<Biome> biome = levelAccessor.getBiome(this.blockPosition());
        LocalDate currentDate = LocalDate.now();
        if (currentDate.getMonth() == Month.OCTOBER && currentDate.getDayOfMonth() == 31) {
            return getAllVariants()[random.nextInt(3) + 7];
        }
        if (biome.is(Biomes.MANGROVE_SWAMP)) {
            return Variant.HALLOWEEN;
        }
        if (biome.is(ConventionalBiomeTags.IS_BEACH)) {
            return (random.nextBoolean() ? Variant.REDGHOST : Variant.GHOST);
        }
        if (biome.is(Biomes.STONY_SHORE)) {
            return Variant.SALLY;
        }
        if (biome.is(Biomes.WARM_OCEAN)) {
            return Variant.CANDY;
        }
        if (biome.is(Biomes.LUKEWARM_OCEAN) || biome.is(Biomes.DEEP_LUKEWARM_OCEAN)) {
            return Variant.EMERALD;
        }
        if (biome.is(Biomes.OCEAN) || biome.is(Biomes.DEEP_OCEAN)) {
            return Variant.BLUE;
        }
        if (biome.is(Biomes.COLD_OCEAN) || biome.is(Biomes.DEEP_COLD_OCEAN)) {
            return Variant.PURPLE;
        }
        return Variant.VAMPIRE;
    }

    public enum Variant implements ReefVariant {
        SALLY(0, "sally"),
        EMERALD(1, "emerald"),
        BLUE(2, "blue"),
        PURPLE(3, "purple"),
        CANDY(4, "candy"),
        REDGHOST(5, "redghost"),
        HALLOWEEN(6, "halloween"),
        GHOST(7, "ghost"),
        VAMPIRE(8, "vampire");

        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);

        private final int index;
        private final String name;

        Variant(int index, String name) {
            this.index = index;
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        @Override
        public int index() {
            return this.index;
        }

        @Override
        public String entity() {
            return "crab";
        }
    }
}
