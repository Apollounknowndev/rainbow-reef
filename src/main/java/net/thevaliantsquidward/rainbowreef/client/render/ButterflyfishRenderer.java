package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.ButterflyfishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Butterflyfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;


public class ButterflyfishRenderer extends ReefRenderer<Butterflyfish, ReefRenderState> {
    public ButterflyfishRenderer(EntityRendererProvider.Context context) {
        super("butterflyfish", Butterflyfish.Variant.values(), context, new ButterflyfishModel(context.bakeLayer(ReefModelLayers.BUTTERFISH_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(Butterflyfish entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.landAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}