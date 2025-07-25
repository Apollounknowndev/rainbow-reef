package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.GobyModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefIdleableRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Goby;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class GobyRenderer extends ReefRenderer<Goby, ReefIdleableRenderState> {
    public GobyRenderer(EntityRendererProvider.Context context) {
        super("goby", Goby.Variant.values(), context, new GobyModel(context.bakeLayer(ReefModelLayers.GOBY_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(Goby entity, ReefIdleableRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.landAnimationState);
        state.idle.copyFrom(entity.idleAnimationState);
    }

    @Override
    public ReefIdleableRenderState createRenderState() {
        return new ReefIdleableRenderState();
    }
}