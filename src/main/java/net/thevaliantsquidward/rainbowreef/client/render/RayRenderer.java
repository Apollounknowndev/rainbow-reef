package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.RayModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.TailBearerRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Ray;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class RayRenderer extends ReefRenderer<Ray, TailBearerRenderState> {
    public RayRenderer(EntityRendererProvider.Context context) {
        super("ray", Ray.Variant.values(), context, new RayModel(context.bakeLayer(ReefModelLayers.RAY_LAYER)), 0.6F);
    }

    @Override
    public TailBearerRenderState createRenderState() {
        return new TailBearerRenderState();
    }

    @Override
    public void extractRenderState(Ray ray, TailBearerRenderState state, float delta) {
        super.extractRenderState(ray, state, delta);
        state.idle.copyFrom(ray.idleAnimationState);
        state.swim.copyFrom(ray.swimAnimationState);
        state.flop.copyFrom(ray.flopAnimationState);
        state.lastTailYaws = ray.tailKinematics.getTailYaws();
        state.lastTailPitches = ray.tailKinematics.getTailPitches();

        state.currentTailYaws = ray.tailKinematics.setCurrentTailYaws();
        state.currentTailPitches = ray.tailKinematics.setCurrentTailPitches();
    }
}