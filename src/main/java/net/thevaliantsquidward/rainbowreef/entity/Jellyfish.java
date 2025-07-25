package net.thevaliantsquidward.rainbowreef.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefBaseMob;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariantHolder;
import net.thevaliantsquidward.rainbowreef.registry.ReefDataComponents;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.ReefSoundEvents;


import javax.annotation.Nonnull;

public class Jellyfish extends ReefBaseMob implements Bucketable, ReefVariantHolder<Jellyfish.Variant> {
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Jellyfish.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Jellyfish.class, EntityDataSerializers.INT);

    public float xBodyRot;
    public float xBodyRotO;
    public float zBodyRot;
    public float zBodyRotO;
    public float tentacleMovement;
    public float oldTentacleMovement;
    public float tentacleAngle;
    public float oldTentacleAngle;
    private float speed;
    private float tentacleSpeed;
    private float rotateSpeed;
    private float tx;
    private float ty;
    private float tz;

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

    public Jellyfish(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, Integer.MAX_VALUE);
        this.random.setSeed(this.getId());
        this.tentacleSpeed = 4.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
        //        this number ^ deterimnes how often the jelly boosts
    }

    @Override
    public boolean isNoGravity() {
        return this.isInWater();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new JelRandomMovementGoal(this));
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 10D).add(Attributes.MOVEMENT_SPEED, 0.3D).build();
    }


    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isInWater(), this.tickCount);
        this.landAnimationState.animateWhen(!this.isInWater(), this.tickCount);
    }

    public void aiStep() {
        super.aiStep();
        this.xBodyRotO = this.xBodyRot;
        this.zBodyRotO = this.zBodyRot;
        this.oldTentacleMovement = this.tentacleMovement;
        this.oldTentacleAngle = this.tentacleAngle;
        this.tentacleMovement += this.tentacleSpeed;
        if ((double)this.tentacleMovement > (Math.PI * 2D)) {
            if (this.level().isClientSide) {
                this.tentacleMovement = ((float)Math.PI * 2F);
            } else {
                this.tentacleMovement -= ((float)Math.PI * 2F);
                if (this.random.nextInt(10) == 0) {
                    this.tentacleSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }

                this.level().broadcastEntityEvent(this, (byte)19);
            }
        }

        if (this.isInWater()) {
            if (this.tentacleMovement < (float)Math.PI) {
                float f = this.tentacleMovement / (float)Math.PI;
                this.tentacleAngle = Mth.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;
                if ((double)f > 0.75D) {
                    this.speed = 1.0F;
                    this.rotateSpeed = 1.0F;
                } else {
                    this.rotateSpeed *= 0.8F;
                }
            } else {
                this.tentacleAngle = 0.0F;
                this.speed *= 0.9F;
                this.rotateSpeed *= 0.99F;
            }

            if (!this.level().isClientSide) {
                this.setDeltaMovement((double)(this.tx * this.speed), (double)(this.ty * this.speed), (double)(this.tz * this.speed));
            }

            Vec3 vec3 = this.getDeltaMovement();
            double d0 = vec3.horizontalDistance();
            this.yBodyRot += (-((float)Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI) - this.yBodyRot) * 0.1F;
            this.setYRot(this.yBodyRot);
            this.zBodyRot += (float)Math.PI * this.rotateSpeed * 1.5F;
            this.xBodyRot += (-((float)Mth.atan2(d0, vec3.y)) * (180F / (float)Math.PI) - this.xBodyRot) * 0.1F;
        } else {
            this.tentacleAngle = Mth.abs(Mth.sin(this.tentacleMovement)) * (float)Math.PI * 0.25F;
            if (!this.level().isClientSide) {
                double d1 = this.getDeltaMovement().y;
                if (this.hasEffect(MobEffects.LEVITATION)) {
                    d1 = 0.05D * (double)(this.getEffect(MobEffects.LEVITATION).getAmplifier() + 1);
                } else if (!this.isNoGravity()) {
                    d1 -= 0.08D;
                }

                this.setDeltaMovement(0.0D, d1 * (double)0.98F, 0.0D);
            }

            this.xBodyRot += (-90.0F - this.xBodyRot) * 0.02F;
        }

    }
    private Vec3 rotateVector(Vec3 pVector) {
        Vec3 vec3 = pVector.xRot(this.xBodyRotO * ((float)Math.PI / 180F));
        return vec3.yRot(-this.yBodyRotO * ((float)Math.PI / 180F));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
        builder.define(FROM_BUCKET, false);
    }


    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.JELLYFISH_BUCKET);
    }


    public void saveToBucketTag(@Nonnull ItemStack bucket) {
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        bucket.copyFrom(getVariantComponent(), this);
    }


    public void loadFromBucketTag(@Nonnull CompoundTag compound) {
        Bucketable.loadDefaultDataFromBucketTag(this, compound);
    }

    @Override
    @Nonnull
    protected InteractionResult mobInteract(@Nonnull Player player, @Nonnull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level().isClientSide) {
            if(itemstack.getItem() == Items.GLASS_BOTTLE) {
                if(!player.isCreative()) {
                    itemstack.shrink(1);
                }
                this.spawnAtLocation((ServerLevel) this.level(), ReefItems.JELLYFISH_JELLY);
                this.playSound(SoundEvents.BOTTLE_FILL, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    public void playerTouch(Player player) {
        if (player instanceof ServerPlayer serverPlayer && serverPlayer.hurtServer(serverPlayer.level(), this.damageSources().mobAttack(this), 2)) {
            this.playSound(ReefSoundEvents.JELLYZAP, 1.0F, 1.0F);
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
        }

    }

    public Variant getVariant() {
        return getAllVariants()[this.entityData.get(VARIANT)];
    }

    public void setVariant(Variant variant) {
        int index = variant.index();
        if (index >= 0 && index < getAllVariants().length) {
            this.entityData.set(VARIANT, index);
        }
    }

    @Override
    public Variant getDefaultVariant() {
        return Variant.PINK;
    }

    @Override
    public Codec<Variant> getVariantCodec() {
        return Variant.CODEC;
    }

    @Override
    public DataComponentType<Variant> getVariantComponent() {
        return ReefDataComponents.JELLYFISH_VARIANT;
    }

    @Override
    public Variant[] getAllVariants() {
        return Variant.values();
    }

    public void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putString("variant", this.getVariant().getSerializedName());
        output.putBoolean("from_bucket", this.fromBucket());
    }

    public void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setVariant(input.read("variant", getVariantCodec()).orElse(getDefaultVariant()));
        this.setFromBucket(input.getBooleanOr("from_bucket", false));
    }

    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean fromBucket) {
        this.entityData.set(FROM_BUCKET, fromBucket);
    }

    public Variant selectVariant(ServerLevelAccessor levelAccessor, RandomSource random) {
        return getAllVariants()[random.nextInt(5) + random.nextFloat() <= 0.1 ? 5 : 0];
    }

    @Nonnull
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    public void travel(Vec3 pTravelVector) {
        this.move(MoverType.SELF, this.getDeltaMovement());
    }

    public void setMovementVector(float pTx, float pTy, float pTz) {
        this.tx = pTx;
        this.ty = pTy;
        this.tz = pTz;
    }

    public boolean hasMovementVector() {
        return this.tx != 0.0F || this.ty != 0.0F || this.tz != 0.0F;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return ReefSoundEvents.JELLYHIT;
    }

    static class JelRandomMovementGoal extends Goal {
        private final Jellyfish squid;

        public JelRandomMovementGoal(Jellyfish pSquid) {
            this.squid = pSquid;
        }

        public boolean canUse() {
            return true;
        }

        public void tick() {
            int pauseTime = this.squid.getNoActionTime();
            if (pauseTime > 100) {
                this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
            } else if (this.squid.getRandom().nextInt(reducedTickDelay(50)) == 0 || !this.squid.wasTouchingWater || !this.squid.hasMovementVector()) {
                float randomMotion = this.squid.getRandom().nextFloat() * 6.2831855F;
                float randX = Mth.cos(randomMotion) * 0.2F;
                float randY = -0.1F + this.squid.getRandom().nextFloat() * 0.2F;
                float randZ = Mth.sin(randomMotion) * 0.2F;
                this.squid.setMovementVector(randX, randY, randZ);
                //the goal modifies the angle of the squid, using setMovementVector to set the direction of the jelly by scaling each dimension's movement vector
            }

        }
    }

    public enum Variant implements ReefVariant {
        PINK(0, "pink"),
        ORANGE(1, "orange"),
        WHITE(2, "white"),
        YELLOW(3, "yellow"),
        RED(4, "red"),

        MUDDY(5, "muddy"),
        ABYSSAL(6, "abyssal"),
        CHERRY(7, "cherry"),
        MINTY(8, "minty"),
        AZURE(9, "azure");

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
            return "jellyfish";
        }
    }
}
