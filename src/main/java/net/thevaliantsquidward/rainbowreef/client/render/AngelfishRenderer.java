package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.AngelfishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Angelfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class AngelfishRenderer extends ReefRenderer<Angelfish, ReefRenderState> {
    public AngelfishRenderer(EntityRendererProvider.Context context) {
        super("angelfish", Angelfish.Variant.values(), context, new AngelfishModel(context.bakeLayer(ReefModelLayers.ANGELFISH_LAYER)), 0.3F);
    }

    @Override
    public void extractRenderState(Angelfish entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.flopAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}