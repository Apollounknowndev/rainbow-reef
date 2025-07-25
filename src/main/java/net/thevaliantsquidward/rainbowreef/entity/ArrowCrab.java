package net.thevaliantsquidward.rainbowreef.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.*;
import net.thevaliantsquidward.rainbowreef.entity.base.AbstractCrab;
import net.thevaliantsquidward.rainbowreef.entity.base.DancingEntity;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

import javax.annotation.Nonnull;

public class ArrowCrab extends AbstractCrab<ArrowCrab.Variant> {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState danceAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();

    public ArrowCrab(EntityType<? extends DancingEntity> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.setPathfindingMalus(PathType.WATER_BORDER, 0.0F);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.ARROW_CRAB_BUCKET);
    }

    @Override
    @Nonnull
    public InteractionResult mobInteract(@Nonnull Player player, @Nonnull InteractionHand hand) {
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(0, new ConditionalStopGoal(this) {
            @Override
            public boolean canUse() {
                ArrowCrab crab = (ArrowCrab) getCreatura();
                return crab.isDancing();
            }

            @Override
            public boolean canContinueToUse() {
                ArrowCrab crab = (ArrowCrab) getCreatura();
                return crab.isDancing();
            }
        });
        this.goalSelector.addGoal(1, new CrabFindWater(this));
        this.goalSelector.addGoal(1, new CrabLeaveWater(this));
        this.goalSelector.addGoal(3, new CrabBottomWander(this, 1.0D, 10, 50));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
    }

    @Override
    protected void setupAnimationStates() {
        this.idleAnimationState.animateWhen(!this.walkAnimation.isMoving(), this.tickCount);
        this.walkAnimationState.animateWhen(this.isAlive() && this.walkAnimation.isMoving(), this.tickCount);
        this.danceAnimationState.animateWhen(this.isAlive() && (this.isDancing() && this.getJukeboxPos() != null), this.tickCount);
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.RED;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.ARROW_CRAB_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    @Override
    public Variant getVariant() {
        return getAllVariants()[this.entityData.get(VARIANT)];
    }

    @Override
    public void setVariant(Variant variant) {
        this.entityData.set(VARIANT, variant.index());
    }

    @Override
    public Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        return random.nextFloat() <= 0.1 ? Variant.YELLOWLINED : Variant.RED;
    }

    public enum Variant implements ReefVariant {
        RED(0, "red"),
        YELLOWLINED(1, "yellowlined");

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
            return "arrow_crab";
        }
    }
}
