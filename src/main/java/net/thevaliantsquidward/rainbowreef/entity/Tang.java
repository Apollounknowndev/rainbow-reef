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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefSchoolingFishEntity;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;

public class Tang extends ReefSchoolingFishEntity<Tang.Variant> {
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState flopAnimationState = new AnimationState();

    public Tang(EntityType<? extends ReefSchoolingFishEntity> type, Level level) {
        super(type, level, 180);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 5, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    @Override
    public int getMaxSchoolSize() {
        return 20;
    }

    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(pTravelVector);
        }
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.BLUEHIPPO;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.TANG_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    @Override
    public Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        if (random.nextFloat() <= 0.001) {
            return getAllVariants()[random.nextInt(10) + 22];
        }
        if (random.nextFloat() <= 0.15) {
            return getAllVariants()[random.nextInt(7) + 15];
        }
        return getAllVariants()[random.nextInt(15)];
    }

    protected void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isInWater(), this.tickCount);
        this.flopAnimationState.animateWhen(!this.isInWater(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.TANG_BUCKET);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 6D).add(Attributes.MOVEMENT_SPEED, 1D).build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FishDigGoal(this, 10, RRTags.TANG_DIET));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomizableRandomSwimGoal(this, 0.8, 1, 20, 20, 3, false));
    }

    public enum Variant implements ReefVariant {
        BLUEHIPPO(0, "bluehippo"),
        POWDERBLUE(1, "powderblue"),
        YELLOW(2, "yellow"),
        UNICORN(3, "unicorn"),
        CONVICT(4, "convict"),
        CLOWN(5, "clown"),
        ACHILLES(6, "achilles"),
        CHOCOLATE(7, "chocolate"),
        SAILFIN(8, "sailfin"),
        ATLANTICBLUE(9, "atlanticblue"),
        EYESTRIPE(10, "eyestripe"),
        SCOPAS(11, "scopas"),
        BLACKSURGEON(12, "blacksurgeon"),
        ORANGEBAND(13, "orangeband"),
        BLONDELIPSTICK(14, "blondelipstick"),

        PURPLE(15, "purple"),
        BLACK(16, "black"),
        REGALBLUE(17, "regalblue"),
        GEM(18, "gem"),
        WHITECHEEK(19, "whitecheek"),
        WHITETAILBRISTLETOOTH(20, "whitetailbristletooth"),
        ZEBRA(21, "zebra"),

        PENGUIN(22, "penguin"),
        GREENSPOT(23, "greenspot"),
        RUSTY(24, "rusty"),
        PEARLY(25, "pearly"),
        YELLOWBELLYBLUE(26, "yellowbellyblue"),
        MUDDY(27, "muddy"),
        GOTH(28, "goth"),
        POWDERHYBRID(29, "powderhybrid"),
        PASTELBLUE(30, "pastelblue"),
        YELLOWSTRIKE(31, "yellowstrike");

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
            return "tang";
        }
    }
}
