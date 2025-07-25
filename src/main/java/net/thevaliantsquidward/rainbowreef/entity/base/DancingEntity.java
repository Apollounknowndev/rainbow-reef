package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class DancingEntity extends ReefBaseMob {
    private static final EntityDataAccessor<Boolean> DANCING = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.BOOLEAN);

    private BlockPos jukeboxPosition;
    private final DynamicGameEventListener<JukeboxListener> dynamicJukeboxListener;
    private final VibrationSystem.User vibrationUser;

    public DancingEntity(EntityType<? extends WaterAnimal> type, Level level) {
        super(type, level, 260);
        this.vibrationUser = new VibrationUser();
        this.dynamicJukeboxListener = new DynamicGameEventListener<>(new JukeboxListener(vibrationUser.getPositionSource(), GameEvent.JUKEBOX_PLAY.value().notificationRadius()));
    }

    public void updateDynamicGameEventListener(BiConsumer<DynamicGameEventListener<?>, ServerLevel> pListenerConsumer) {
        Level level = this.level();
        if (level instanceof ServerLevel serverlevel) {
            pListenerConsumer.accept(this.dynamicJukeboxListener, serverlevel);
        }

    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DANCING, false);
    }

    public void tick() {
        super.tick();

        if (this.getJukeboxPos() != null) {
            if (!this.getJukeboxPos().closerToCenterThan(this.position(), GameEvent.JUKEBOX_PLAY.value().notificationRadius())) {
                this.setDancing(false);
            } else {
                this.setDancing(true);
            }
        }

    }

    public void setJukeboxPos(BlockPos pos) {
        this.jukeboxPosition = pos;
    }

    public BlockPos getJukeboxPos() {
        return this.jukeboxPosition;
    }

    public boolean isDancing() {
        return this.entityData.get(DANCING);
    }

    public void setDancing(boolean dancing) {
        this.entityData.set(DANCING, dancing);
    }

    public void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("dancing", this.isDancing());
    }
    public void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setDancing(input.getBooleanOr("dancing", false));
    }

    public void setJukeboxPlaying(BlockPos jukeboxPos, boolean playing) {
        if (playing) {
            if (!this.isDancing()) {
                this.setJukeboxPos(jukeboxPos);
                this.setDancing(true);
            }

        } else {
            this.setJukeboxPos(null);
            this.setDancing(false);
        }

    }

    class VibrationUser implements VibrationSystem.User {
        private final PositionSource positionSource = new EntityPositionSource(DancingEntity.this, DancingEntity.this.getEyeHeight());

        public int getListenerRadius() {
            return 16;
        }

        public PositionSource getPositionSource() {
            return this.positionSource;
        }

        @Override
        public boolean canReceiveVibration(ServerLevel serverLevel, BlockPos blockPos, Holder<GameEvent> gameEvent, GameEvent.Context context) {
            return true;
        }

        @Override
        public void onReceiveVibration(ServerLevel serverLevel, BlockPos blockPos, Holder<GameEvent> gameEvent, @Nullable Entity entity, @Nullable Entity entity1, float v) {
        }

        public TagKey<GameEvent> getListenableEvents() {
            return GameEventTags.ALLAY_CAN_LISTEN;
        }
    }

    class JukeboxListener implements GameEventListener {
        private final PositionSource listenerSource;
        private final int listenerRadius;

        public JukeboxListener(PositionSource pListenerSource, int pListenerRadius) {
            this.listenerSource = pListenerSource;
            this.listenerRadius = pListenerRadius;
        }

        public PositionSource getListenerSource() {
            return this.listenerSource;
        }

        public int getListenerRadius() {
            return this.listenerRadius;
        }

        public boolean handleGameEvent(ServerLevel level, Holder<GameEvent> event, GameEvent.Context context, Vec3 pos) {
            if (event == GameEvent.JUKEBOX_PLAY) {
                DancingEntity.this.setJukeboxPlaying(BlockPos.containing(pos), true);
                return true;

            } else if (event == GameEvent.JUKEBOX_STOP_PLAY) {
                DancingEntity.this.setJukeboxPlaying(BlockPos.containing(pos), false);
                return true;

            } else {
                return false;

            }
        }
    }

}
