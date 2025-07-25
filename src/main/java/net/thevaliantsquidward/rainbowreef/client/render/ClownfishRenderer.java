package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.ClownfishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Clownfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class ClownfishRenderer extends ReefRenderer<Clownfish, ReefRenderState> {
    public ClownfishRenderer(EntityRendererProvider.Context context) {
        super("clownfish", Clownfish.Variant.values(), context, new ClownfishModel(context.bakeLayer(ReefModelLayers.CLOWNFISH_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(Clownfish entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.flopAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}