package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.JellyfishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefIdleableRenderState;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Goby;
import net.thevaliantsquidward.rainbowreef.entity.Jellyfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class JellyfishRenderer extends ReefRenderer<Jellyfish, ReefRenderState> {
    public JellyfishRenderer(EntityRendererProvider.Context context) {
        super("jellyfish", Jellyfish.Variant.values(), context, new JellyfishModel(context.bakeLayer(ReefModelLayers.JELLYFISH_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(Jellyfish entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.landAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}