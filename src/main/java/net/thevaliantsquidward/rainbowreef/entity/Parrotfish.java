package net.thevaliantsquidward.rainbowreef.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
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
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefSchoolingFishEntity;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.RandomSleepyLookaroundGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.RandomSleepySwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;

public class Parrotfish extends ReefSchoolingFishEntity<Parrotfish.Variant> {
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState flopAnimationState = new AnimationState();
    public final AnimationState eepyAnimationState = new AnimationState();

    public Parrotfish(EntityType<? extends Parrotfish> type, Level level) {
        super(type, level, 180);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.BLUE;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.PARROTFISH_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    @Override
    public Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        if (random.nextFloat() <= 0.1) {
            return getAllVariants()[random.nextInt(4) + 8];
        }
        return getAllVariants()[random.nextInt(8)];
    }

    @Override
    protected void setupAnimationStates() {
        long roundedTime = this.level().getDayTime() % 24000;
        boolean night = roundedTime >= 13000 && roundedTime <= 22000;

        this.swimAnimationState.animateWhen(this.isInWater(), this.tickCount);
        this.flopAnimationState.animateWhen(!this.isInWater(), this.tickCount);
        this.eepyAnimationState.animateWhen(this.isInWater() && night, this.tickCount);
        if (night) {
            this.swimAnimationState.stop();
        }
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.PARROTFISH_BUCKET);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 4D).add(Attributes.MOVEMENT_SPEED, 1.0D).build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FishDigGoal(this, 10, RRTags.PARROTFISH_DIET));
        this.goalSelector.addGoal(4, new RandomSleepyLookaroundGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new RandomSleepySwimGoal(this, 0.8, 1));
    }

    public enum Variant implements ReefVariant {
        BLUE(0, "blue"),
        HUMPHEAD(1, "humphead"),
        MIDNIGHT(2, "midnight"),
        STOPLIGHT(3, "stoplight"),
        MEDITERRANEAN(4, "mediterranean"),
        PRINCESS(5, "princess"),
        YELLOWTAIL(6, "yellowtail"),
        YELLOWBAND(7, "yellowband"),
        RAINBOW(8, "rainbow"),
        BLUEBUMPHEAD(9, "bluebumphead"),
        RED(10, "red"),
        OBISHIME(11, "obishime");

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
            return "parrotfish";
        }
    }
}