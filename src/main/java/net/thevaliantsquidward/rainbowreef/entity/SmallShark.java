package net.thevaliantsquidward.rainbowreef.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.GroundseekingRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefFishEntity;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.kinematics.IKSolver;
import net.thevaliantsquidward.rainbowreef.entity.pathing.AdvancedWaterboundPathNavigation;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;

public class SmallShark extends ReefFishEntity<SmallShark.Variant> {
    public IKSolver tailKinematics;

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();

    public SmallShark(EntityType<? extends WaterAnimal> type, Level level) {
        super(type, level, 400);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 3, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);

        this.tailKinematics = new IKSolver(this, 2, 0.3, 0.95, true, true);
    }

    public void tick() {
        super.tick();
        this.tailKinematics.TakePerTickAction(this);

        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.EPAULETTE;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.SMALL_SHARK_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    @Override
    public Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        if (random.nextFloat() <= 0.01) {
            return random.nextBoolean() ? Variant.ALBINO : Variant.PIEBALD;
        }
        return getAllVariants()[random.nextInt(6)];
    }

    protected void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.walkAnimation.isMoving() && this.isInWater(), this.tickCount);
        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    public void travel(Vec3 pTravelVector) {
        if (this.isEyeInFluid(FluidTags.WATER) && !this.isPathFinding()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.01, 0.0));
        }
        super.travel(pTravelVector);
    }

    @Override
    protected PathNavigation createNavigation(Level p_27480_) {
        return new AdvancedWaterboundPathNavigation(this, p_27480_, true, false);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.SHARK_BUCKET);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 7D).add(Attributes.MOVEMENT_SPEED, 0.8D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FishDigGoal(this, 40, RRTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new GroundseekingRandomSwimGoal(this, 1, 100, 20, 20, 0.01));
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.TROPICAL_FISH_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.TROPICAL_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_28281_) {
        return SoundEvents.TROPICAL_FISH_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.TROPICAL_FISH_FLOP;
    }

    public enum Variant implements ReefVariant {
        EPAULETTE(0, "epaulette"),
        PAJAMA(1, "pajama"),
        HORNED(2, "horned"),
        NURSE(3, "nurse"),
        ZEBRA(4, "zebra"),
        PORTJACKSON(5, "portjackson"),
        ALBINO(6, "albino"),
        PIEBALD(7, "piebald");

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
            return "small_shark";
        }
    }
}