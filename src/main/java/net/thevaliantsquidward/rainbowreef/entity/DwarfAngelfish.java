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
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefFishEntity;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;

public class DwarfAngelfish extends ReefFishEntity<DwarfAngelfish.Variant> {
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

    public DwarfAngelfish(EntityType<? extends WaterAnimal> type, Level level) {
        super(type, level, 120);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 10, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.BICOLOR;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.DWARF_ANGELFISH_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    @Override
    public Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        if (random.nextFloat() <= 0.001) {
            return Variant.ORANGEPEEL;
        }
        if (random.nextFloat() <= 0.1) {
            return Variant.RARE_VARIANTS[random.nextInt(3)];
        }
        return getAllVariants()[random.nextInt(12)];
    }

    @Override
    protected void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.walkAnimation.isMoving() && this.isInWater(), this.tickCount);
        this.landAnimationState.animateWhen(this.isAlive() && !this.isInWater(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.DWARF_ANGELFISH_BUCKET);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 4D).add(Attributes.MOVEMENT_SPEED, 0.7D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FishDigGoal(this, 30, RRTags.ANGELFISH_DIET));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomizableRandomSwimGoal(this, 1, 1, 20, 20, 3, false));
    }

    public enum Variant implements ReefVariant {
        BICOLOR(0, "bicolor"),
        CORALBEAUTY(1, "coralbeauty"),
        FLAME(2, "flame"),
        SPOTTED(3, "spotted"),
        MASKED(4, "masked"),
        CHERUB(5, "cherub"),
        BLACKNOX(6, "blacknox"),
        LAMARCK(7, "lamarck"),
        LEMONPEEL(8, "lemonpeel"),
        YELLOW(9, "yellow"),
        PEARLSCALE(10, "pearlscale"),
        RESPLENDENT(11, "resplendent"),
        CANDYCANE(12, "candycane"),
        JAPANESE(13, "japanese"),
        YELLOWTAIL(14, "yellowtail"),
        ORANGEPEEL(15, "orangepeel");

        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
        public static final Variant[] RARE_VARIANTS = new Variant[]{CANDYCANE, JAPANESE, YELLOWTAIL};

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
            return "dwarf_angelfish";
        }
    }
}