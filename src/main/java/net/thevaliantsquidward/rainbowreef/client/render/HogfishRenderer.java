package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.HogfishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefIdleableRenderState;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Goby;
import net.thevaliantsquidward.rainbowreef.entity.Hogfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;


public class HogfishRenderer extends ReefRenderer<Hogfish, ReefRenderState> {
    public HogfishRenderer(EntityRendererProvider.Context context) {
        super("hogfish", Hogfish.Variant.values(), context, new HogfishModel(context.bakeLayer(ReefModelLayers.HOGFISH_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(Hogfish entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.landAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}