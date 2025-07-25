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
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.nemhoster.LocateNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.nemhoster.MoveToNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.nemhoster.NemHoster;
import net.thevaliantsquidward.rainbowreef.entity.base.nemhoster.RestInNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;

public class Clownfish extends NemHoster<Clownfish.Variant> implements Bucketable {
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState flopAnimationState = new AnimationState();

    public Clownfish(EntityType<? extends NemHoster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, 200, 4, 600, 200);

        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 60, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    protected void setupAnimationStates() {
        if (this.isInWater()) {
            this.flopAnimationState.stop();
            this.swimAnimationState.startIfStopped(this.tickCount);
        } else {
            this.swimAnimationState.stop();
            this.flopAnimationState.startIfStopped(this.tickCount);
        }
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.CLOWNFISH_BUCKET);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 4D).add(Attributes.MOVEMENT_SPEED, 0.7D).build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FishDigGoal(this, 10, RRTags.CLOWNFISH_DIET));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 0.8D, 40));

        this.goalSelector.addGoal(0, new LocateNemGoal(this, 200));
        this.goalSelector.addGoal(0, new MoveToNemGoal(this, 1, 4));
        this.goalSelector.addGoal(5, new RestInNemGoal(this, 3, 600, 200));
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.OCELLARIS;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.CLOWNFISH_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    public Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        if (random.nextFloat() <= 0.001) {
            return Variant.BLUESTRAIN;
        }
        if (random.nextFloat() <= 0.05) {
            return getAllVariants()[random.nextInt(10) + 9];
        }
        return getAllVariants()[random.nextInt(9)];
    }

    public enum Variant implements ReefVariant {
        OCELLARIS(0, "ocellaris"),
        BLACKANDWHITE(1, "blackandwhite"),
        MAROON(2, "maroon"),
        PINKSKUNK(3, "pinkskunk"),
        CLARKII(4, "clarkii"),
        TOMATO(5, "tomato"),
        MADAGASCAR(6, "madagascar"),
        ALLARD(7, "allard"),
        REDSADDLEBACK(8, "redsaddleback"),

        BLIZZARD(9, "blizzard"),
        OMAN(10, "oman"),
        MOCHA(11, "mocha"),
        WHITESNOUT(12, "whitesnout"),
        GOLDNUGGET(13, "goldnugget"),
        SNOWSTORM(14, "snowstorm"),
        ORANGESKUNK(15, "orangeskunk"),
        DOMINO(16, "domino"),
        YELLOWCLARKII(17, "yellowclarkii"),
        NAKED(18, "naked"),

        BLUESTRAIN(19, "bluestrain");

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
            return "clownfish";
        }
    }
}