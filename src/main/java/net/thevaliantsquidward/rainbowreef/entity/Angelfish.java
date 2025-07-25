package net.thevaliantsquidward.rainbowreef.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefSchoolingFishEntity;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;

public class Angelfish extends ReefSchoolingFishEntity<Angelfish.Variant> {
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState flopAnimationState = new AnimationState();

    public Angelfish(EntityType<? extends Angelfish> type, Level level) {
        super(type, level, 300);

        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 5, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    @Override
    public int getMaxSchoolSize() {
        return 3;
    }

    protected void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isInWater(), this.tickCount);
        this.flopAnimationState.animateWhen(!this.isInWater(), this.tickCount);
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.QUEEN;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.ANGELFISH_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    @Override
    public Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        if (random.nextInt(1000) == 0) {
            return Variant.BLUEQUEEN;
        }
        return getAllVariants()[random.nextInt(15)];
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.ANGELFISH_BUCKET);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 4d).add(Attributes.MOVEMENT_SPEED, 0.7d).build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FishDigGoal(this, 20, RRTags.ANGELFISH_DIET));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomizableRandomSwimGoal(this, 0.8, 1, 20, 20, 3, false));
    }

    public enum Variant implements ReefVariant {
        QUEEN(0, "queen"),
        FRENCH(1, "french"),
        EMPEROR(2, "emperor"),
        YELLOWBAND(3, "yellowband"),
        BLUERING(4, "bluering"),
        ROCKBEAUTY(5, "rockbeauty"),
        MAJESTIC(6, "majestic"),
        KING(7, "king"),
        SEMICIRCLE(8, "semicircle"),
        BANDED(9, "banded"),
        GRAY(10, "gray"),
        OLDWOMAN(11, "oldwoman"),
        GUINEAN(12, "guinean"),
        QUEENSLANDYELLOWTAIL(13, "queenslandyellowtail"),
        CLARION(14, "clarion"),
        BLUEQUEEN(15, "bluequeen");

        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);

        private final int index;
        private final String name;

        Variant(int index, String name) {
            this.index = index;
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        @Override
        public int index() {
            return this.index;
        }

        @Override
        public String entity() {
            return "angelfish";
        }
    }
}