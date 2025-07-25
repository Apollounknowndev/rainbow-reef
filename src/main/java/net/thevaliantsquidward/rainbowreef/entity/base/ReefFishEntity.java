package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariantHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ReefFishEntity<V extends ReefVariant> extends ReefBaseMob implements Bucketable, ReefVariantHolder<V> {
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(ReefFishEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ReefFishEntity.class, EntityDataSerializers.INT);

    protected ReefFishEntity(EntityType<? extends WaterAnimal> type, Level level, int feedCooldown) {
        super(type, level, feedCooldown);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, this.getDefaultVariant().index());
        builder.define(FROM_BUCKET, false);
    }

    // Variants and buckets

    public V getVariant() {
        return getAllVariants()[this.entityData.get(VARIANT)];
    }

    public void setVariant(V variant) {
        int index = variant.index();
        if (index >= 0 && index < getAllVariants().length) {
            this.entityData.set(VARIANT, index);
        }
    }

    @Nullable
    public <T> T get(DataComponentType<? extends T> type) {
        return type == getVariantComponent() ? castComponentValue(type, this.getVariant()) : super.get(type);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter getter) {
        this.applyImplicitComponentIfPresent(getter, getVariantComponent());
        super.applyImplicitComponents(getter);
    }

    @Override
    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        if (type == getVariantComponent()) {
            this.setVariant(castComponentValue(getVariantComponent(), value));
            return true;
        } else {
            return super.applyImplicitComponent(type, value);
        }
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData groupData) {
        this.setVariant(this.selectVariant(levelAccessor, levelAccessor.getRandom()));
        return super.finalizeSpawn(levelAccessor, difficulty, reason, groupData);
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

    @Override
    public void saveToBucketTag(@Nonnull ItemStack stack) {
        Bucketable.saveDefaultDataToBucketTag(this, stack);
        stack.copyFrom(getVariantComponent(), this);
    }

    @Override
    public void loadFromBucketTag(@Nonnull CompoundTag compound) {
        Bucketable.loadDefaultDataFromBucketTag(this, compound);
    }

    @Override
    @Nonnull
    protected InteractionResult mobInteract(@Nonnull Player player, @Nonnull InteractionHand hand) {
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
        this.entityData.set(FROM_BUCKET, fromBucket);
    }

    // Other

    protected abstract void setupAnimationStates();

    @Override
    public void tick() {
        this.setupAnimationStates();
        if (!this.isInWater() && this.onGround() && this.verticalCollision && this.flops()) {
            this.setDeltaMovement(0, 0, 0);
            this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
            this.setOnGround(false);
            this.hasImpulse = true;
            this.makeSound(this.getFlopSound());
        }
        super.tick();
    }

    public boolean flops() {
        return true;
    }

    @Override
    @Nonnull
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.TROPICAL_FISH_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.TROPICAL_FISH_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.TROPICAL_FISH_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public boolean isNoGravity() {
        return this.isInWater();
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WaterBoundPathNavigation(this, level);
    }

    @Override
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    @Override
    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }
}
