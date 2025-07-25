package net.thevaliantsquidward.rainbowreef.entity;


import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefFishEntity;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;

public class Hogfish extends ReefFishEntity<Hogfish.Variant> {
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

    public Hogfish(EntityType<? extends WaterAnimal> type, Level level) {
        super(type, level, 400);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.CUBAN;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.HOGFISH_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return new Variant[0];
    }

    protected void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isInWater(), this.tickCount);
        this.landAnimationState.animateWhen(!this.isInWater(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.HOGFISH_BUCKET);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 4D).add(Attributes.MOVEMENT_SPEED, 1D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FishDigGoal(this, 40, RRTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new RandomSwimmingGoal(this, 0.8D, 1));
    }

    public enum Variant implements ReefVariant {
        CUBAN(0, "cuban"),
        SPANISH(1, "spanish"),
        PEPPERMINT(2, "peppermint"),
        LYRETAIL(3, "lyretail"),
        CORAL(4, "coral");

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
            return "hogfish";
        }
    }
}