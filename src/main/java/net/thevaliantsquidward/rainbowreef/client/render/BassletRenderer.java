package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.BassletModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefIdleableRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Basslet;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class BassletRenderer extends ReefRenderer<Basslet, ReefIdleableRenderState> {
    public BassletRenderer(EntityRendererProvider.Context context) {
        super("basslet", Basslet.Variant.values(), context, new BassletModel(context.bakeLayer(ReefModelLayers.BASSLET_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(Basslet entity, ReefIdleableRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.idle.copyFrom(entity.idleAnimationState);
        state.flop.copyFrom(entity.landAnimationState);
    }

    @Override
    public ReefIdleableRenderState createRenderState() {
        return new ReefIdleableRenderState();
    }
}