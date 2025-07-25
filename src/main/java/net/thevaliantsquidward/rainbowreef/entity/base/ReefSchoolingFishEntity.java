package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FollowVariantLeaderGoal;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public abstract class ReefSchoolingFishEntity<V extends ReefVariant> extends ReefFishEntity<V> implements Bucketable {
    @Nullable
    private ReefSchoolingFishEntity<?> leader;
    private int schoolSize = 1;

    public ReefSchoolingFishEntity(EntityType<? extends WaterAnimal> type, Level level, int feedCooldown) {
        super(type, level, feedCooldown);
    }

    // Other stuff

    @Override
    public void tick() {
        if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
            List<? extends WaterAnimal> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
            if (list.size() <= 1) {
                this.schoolSize = 1;
            }
        }
        super.tick();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FollowVariantLeaderGoal(this));
    }

    public int getMaxSpawnClusterSize() {
        return this.getMaxSchoolSize();
    }

    public int getMaxSchoolSize() {
        return 4;
    }

    protected boolean canRandomSwim() {
        return !this.isFollower();
    }

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public ReefSchoolingFishEntity<?> startFollowing(ReefSchoolingFishEntity<?> leader) {
        this.leader = leader;
        leader.addFollower();
        return leader;
    }

    public void stopFollowing() {
        this.leader.removeFollower();
        this.leader = null;
    }

    private void addFollower() {
        ++this.schoolSize;
    }

    private void removeFollower() {
        --this.schoolSize;
    }

    public boolean canBeFollowed() {
        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
    }

    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 121.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(this.leader, 1.0D);
        }
    }

    public void addFollowers(Stream<? extends ReefSchoolingFishEntity> fishes) {
        fishes.limit(this.getMaxSchoolSize() - this.schoolSize).filter((fish) -> fish != this).forEach((fish) -> {
            if (this.getVariant().equals(fish.getVariant())) {
                fish.startFollowing(this);
            }
        });
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData groupData) {
        super.finalizeSpawn(levelAccessor, difficulty, reason, groupData);
        if (groupData instanceof SchoolSpawnGroupData<?> schoolingGroup) {
            this.startFollowing(schoolingGroup.leader());
            // This makes the subsequent V cast safe
            if (schoolingGroup.leader().getType().equals(this.getType())) {
                this.setVariant((V) schoolingGroup.variant());
            }
        } else {
            groupData = new SchoolSpawnGroupData<>(this, this.getVariant());
        }
        return groupData;
    }

    public record SchoolSpawnGroupData<T extends ReefVariant>(ReefSchoolingFishEntity<T> leader, T variant) implements SpawnGroupData {
    }
}
