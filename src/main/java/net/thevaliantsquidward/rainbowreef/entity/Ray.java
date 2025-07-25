package net.thevaliantsquidward.rainbowreef.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefSchoolingFishEntity;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.kinematics.IKSolver;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;

public class Ray extends ReefSchoolingFishEntity<Ray.Variant> {
    public IKSolver tailKinematics;

    public int animTime;
    public double animSpeed = 1;

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState flopAnimationState = new AnimationState();

    public Ray(EntityType<? extends ReefSchoolingFishEntity> type, Level level) {
        super(type, level, 1200);
        this.moveControl = new SmoothSwimmingMoveControl(this, 360, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);

        this.tailKinematics = new IKSolver(this, 5, 0.5, 0.75,false, false);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.SPOTTED_EAGLE_RAY_BUCKET);
    }

    public void tick() {
        super.tick();
        this.tailKinematics.TakePerTickAction(this);

        if (this.level().isClientSide()) {
            if (this.animTime == (int)(8 * 20 / (this.animSpeed))) {
                this.animTime = 0;
                this.animSpeed = 0.5 + (1 * Math.random());
                //animation speed ranges from 0.5 times, to 1.5 times

            } else {
                this.animTime++;
            }
        }
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.ORNATE;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.RAY_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    protected void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.walkAnimation.isMoving() && this.isInWater(), this.tickCount);
        this.idleAnimationState.animateWhen(this.isAlive() && this.isInWater(), this.tickCount);
        this.flopAnimationState.animateWhen(this.isAlive() && !this.isInWater(), this.tickCount);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 15D).add(Attributes.MOVEMENT_SPEED, 2D).build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FishDigGoal(this, 40, RRTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomizableRandomSwimGoal(this, 0.8, 1, 20, 20, 2, false));
    }

    public enum Variant implements ReefVariant {
        ORNATE(0, "ornate"),
        COWNOSE(1, "cownose"),
        SPOTTED(2, "spotted");  // default

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
            return "ray";
        }
    }
}