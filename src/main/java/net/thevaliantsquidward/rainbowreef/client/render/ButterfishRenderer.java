package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.ButterfishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Butterfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;


public class ButterfishRenderer extends ReefRenderer<Butterfish, ReefRenderState> {
    public ButterfishRenderer(EntityRendererProvider.Context context) {
        super("butterfish", Butterfish.Variant.values(), context, new ButterfishModel(context.bakeLayer(ReefModelLayers.BUTTERFISH_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(Butterfish entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.landAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}