package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.MoorishIdolModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefIdleableRenderState;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Goby;
import net.thevaliantsquidward.rainbowreef.entity.MoorishIdol;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class MoorishIdolRenderer extends ReefRenderer<MoorishIdol, ReefRenderState> {
    public MoorishIdolRenderer(EntityRendererProvider.Context context) {
        super("moorishidol", MoorishIdol.Variant.values(), context, new MoorishIdolModel(context.bakeLayer(ReefModelLayers.IDOL_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(MoorishIdol entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.landAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}