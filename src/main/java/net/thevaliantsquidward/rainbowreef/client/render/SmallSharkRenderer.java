package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.SmallSharkModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.TailBearerRenderState;
import net.thevaliantsquidward.rainbowreef.entity.SmallShark;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class SmallSharkRenderer extends ReefRenderer<SmallShark, TailBearerRenderState> {
    public SmallSharkRenderer(EntityRendererProvider.Context context) {
        super("small_shark", SmallShark.Variant.values(), context, new SmallSharkModel(context.bakeLayer(ReefModelLayers.SMALL_SHARK_LAYER)), 0.4F);
    }

    @Override
    public TailBearerRenderState createRenderState() {
        return new TailBearerRenderState();
    }

    @Override
    public void extractRenderState(SmallShark shark, TailBearerRenderState state, float delta) {
        super.extractRenderState(shark, state, delta);
        state.idle.copyFrom(shark.idleAnimationState);
        state.swim.copyFrom(shark.swimAnimationState);

        state.lastTailYaws = shark.tailKinematics.getTailYaws();
        state.lastTailPitches = shark.tailKinematics.getTailPitches();

        state.currentTailYaws = shark.tailKinematics.getCurrentTailYaws();
        state.currentTailPitches = shark.tailKinematics.getCurrentTailPitches();
    }
}