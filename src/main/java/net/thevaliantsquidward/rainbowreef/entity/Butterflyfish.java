package net.thevaliantsquidward.rainbowreef.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefSchoolingFishEntity;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;

public class Butterflyfish extends ReefSchoolingFishEntity<Butterflyfish.Variant> {
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

    public Butterflyfish(EntityType<? extends Butterflyfish> type, Level level) {
        super(type, level, 600);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 30, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 15);
    }

    @Override
    public int getMaxSchoolSize() {
        return 10;
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.BUTTERFISH_BUCKET);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 6D).add(Attributes.MOVEMENT_SPEED, 1D).build();
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.COPPERBANDED;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.BUTTERFISH_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    @Override
    public Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        if (levelAccessor.getBiome(this.blockPosition()).is(Biomes.MANGROVE_SWAMP)) {
            return random.nextBoolean() ? Variant.SIXSPINED : Variant.FOUREYE;
        }
        if (random.nextFloat() < 0.02) {
            return random.nextBoolean() ? Variant.EASTERISLAND : Variant.SADDLEBACK;
        }
        return getAllVariants()[random.nextInt(17)];
    }

    @Override
    protected void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isInWater(), this.tickCount);
        this.landAnimationState.animateWhen(!this.isInWater(), this.tickCount);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FishDigGoal(this, 15, RRTags.BUTTERFLY_DIET));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomizableRandomSwimGoal(this, 0.9, 1, 20, 20, 3, false));
    }

    public void travel(Vec3 vec3d) {
        if (this.isEyeInFluid(FluidTags.WATER) && this.isPathFinding()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.001, 0.0));
        }
        super.travel(vec3d);
    }

    public enum Variant implements ReefVariant {
        COPPERBANDED(0, "copperbanded"),
        MULLERS(1, "mullers"),
        THREADFIN(2, "threadfin"),
        BANNER(3, "banner"),
        BLUECHEEK(4, "bluecheek"),
        LONGNOSE(5, "longnose"),
        SPOTFIN(6, "spotfin"),
        HOODED(7, "hooded"),
        ARABIC(8, "arabic"),
        PYRAMID(9, "pyramid"),
        REDSEA(10, "redsea"),
        DARKLONGNOSE(11, "darklongnose"),
        WROUGHTIRON(12, "wroughtiron"),
        AFRICAN(13, "african"),
        ERITREAN(14, "eritrean"),
        MARGINATED(15, "marginated"),
        THOMPSON(16, "thompson"),
        EASTERISLAND(17, "easterisland"),
        SADDLEBACK(18, "saddleback"),
        SIXSPINED(19, "sixspined"),
        FOUREYE(20, "foureye");

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
            return "butterfish";
        }
    }
}