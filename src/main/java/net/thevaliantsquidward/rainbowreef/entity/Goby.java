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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.GroundseekingRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefFishEntity;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.Month;

public class Goby extends ReefFishEntity<Goby.Variant> {
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

    public Goby(EntityType<? extends WaterAnimal> type, Level level) {
        super(type, level, Integer.MAX_VALUE);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000000, 10, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public void tick() {
        if (!(!this.isInWater() && this.onGround() && this.verticalCollision && this.flops())) {
            this.setSprinting(true);
        }
        super.tick();
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.FIRE;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.GOBY_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    @Override
    public Goby.Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.getMonth() == Month.OCTOBER && currentDate.getDayOfMonth() == 31) {
            return Variant.DRACULA;
        }
        if (random.nextFloat() <= 0.001) {
            return Variant.NEONHYBRID;
        }
        return getAllVariants()[random.nextInt(14)];
    }

    protected void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.walkAnimation.isMoving() && this.isInWater(), this.tickCount);
        this.idleAnimationState.animateWhen(!this.walkAnimation.isMoving() && this.isInWater(), this.tickCount);
        this.landAnimationState.animateWhen(!this.isInWater(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.GOBY_BUCKET);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 4D).add(Attributes.MOVEMENT_SPEED, 100.0D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new GroundseekingRandomSwimGoal(this, 0.8D, 75, 5, 10, 0.01));
    }

    public enum Variant implements ReefVariant {
        FIRE(0, "fire"),
        PURPLEFIRE(1, "purplefire"),
        CANDYCANE(2, "candycane"),
        MANDARIN(3, "mandarin"),
        YELLOWWATCHMAN(4, "yellowwatchman"),
        CATALINA(5, "catalina"),
        BLACKRAY(6, "blackray"),
        HELFRICHI(7, "helfrichi"),
        BLUENEON(8, "blueneon"),
        YELLOWNEON(9, "yellowneon"),
        BLACKFIN(10, "blackfin"),
        BLUESTREAK(11, "bluestreak"),
        LEOPARDSPOTTED(12, "leopardspotted"),
        YELLOWCLOWN(13, "yellowclown"),
        DRACULA(14, "dracula"),
        NEONHYBRID(15, "neonhybrid");

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
            return "goby";
        }
    }
}