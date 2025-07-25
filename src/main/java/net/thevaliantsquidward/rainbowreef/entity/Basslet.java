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
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefFishEntity;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.entity.pathing.AdvancedWaterboundPathNavigation;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.Month;

public class Basslet extends ReefFishEntity<Basslet.Variant> {
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();

    public Basslet(EntityType<? extends WaterAnimal> type, Level level) {
        super(type, level, Integer.MAX_VALUE);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.FAIRY;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.BASSLET_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    @Override
    public Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.getMonth() == Month.OCTOBER && currentDate.getDayOfMonth() == 31) {
            return Variant.CANDY;
        }
        if (random.nextFloat() <= 0.001) {
            return Variant.GILDED;
        }
        if (random.nextFloat() <= 0.01) {
            return Variant.GOLD;
        }
        return getAllVariants()[random.nextInt(7)];
    }

    @Override
    protected void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isAlive() && this.walkAnimation.isMoving() && this.isInWater(), this.tickCount);
        this.idleAnimationState.animateWhen(this.isAlive() && !this.walkAnimation.isMoving() && this.isInWater(), this.tickCount);
        this.landAnimationState.animateWhen(this.isAlive() && !this.isInWater(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.BASSLET_BUCKET);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new AdvancedWaterboundPathNavigation(this, level, true, false);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 4D).add(Attributes.MOVEMENT_SPEED, 0.6D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomizableRandomSwimGoal(this, 1, 1, 20, 20, 3, true));
    }

    public enum Variant implements ReefVariant {
        FAIRY(0, "fairy"),
        BRAZILIAN(1, "brazilian"),
        ACCESSOR(2, "accessor"),
        BLACKCAP(3, "blackcap"),
        SWISSGUARD(4, "swissguard"),
        YELLOWSCISSORTAIL(5, "yellowscissortail"),
        MIDNIGHT(6, "midnight"),
        CANDY(7, "candy"),
        GOLD(8, "gold"),
        GILDED(9, "gilded");

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
            return "basslet";
        }
    }
}