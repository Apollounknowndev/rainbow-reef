package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import com.mojang.datafixers.DataFixUtils;
import net.minecraft.world.entity.ai.goal.Goal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefSchoolingFishEntity;

import java.util.List;
import java.util.function.Predicate;

public class FollowVariantLeaderGoal extends Goal {
    private final ReefSchoolingFishEntity mob;
    private int timeToRecalcPath;
    private int nextStartTick;

    public FollowVariantLeaderGoal(ReefSchoolingFishEntity p_25249_) {
        this.mob = p_25249_;
        this.nextStartTick = this.nextStartTick(p_25249_);
    }

    protected int nextStartTick(ReefSchoolingFishEntity p_25252_) {
        return reducedTickDelay(200 + p_25252_.getRandom().nextInt(200) % 20);
    }

    public boolean canUse() {
        if (this.mob.hasFollowers()) {
            return false;
        } else if (this.mob.isFollower()) {
            return true;
        } else if (this.nextStartTick > 0) {
            --this.nextStartTick;
            return false;
        } else {
            this.nextStartTick = this.nextStartTick(this.mob);
            Predicate<ReefSchoolingFishEntity> predicate = (p_25258_) -> {
                return p_25258_.canBeFollowed() || !p_25258_.isFollower();
            };
            List<? extends ReefSchoolingFishEntity> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(10.0D, 10.0D, 10.0D), predicate);
            ReefSchoolingFishEntity schoolingWaterAnimal = DataFixUtils.orElse(list.stream().filter(ReefSchoolingFishEntity::canBeFollowed).findAny(), this.mob);
            schoolingWaterAnimal.addFollowers(list.stream().filter((p_25255_) -> {
                return !p_25255_.isFollower();
            }));
            return this.mob.isFollower();
        }
    }

    public boolean canContinueToUse() {
        return this.mob.isFollower() && this.mob.inRangeOfLeader();
    }

    public void start() {
        this.timeToRecalcPath = 0;
    }

    public void stop() {
        this.mob.stopFollowing();
    }

    public void tick() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            this.mob.pathToLeader();
        }
    }
}
