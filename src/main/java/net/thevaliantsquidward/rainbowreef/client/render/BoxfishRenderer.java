package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.BoxfishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Boxfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;


public class BoxfishRenderer extends ReefRenderer<Boxfish, ReefRenderState> {
    public BoxfishRenderer(EntityRendererProvider.Context context) {
        super("boxfish", Boxfish.Variant.values(), context, new BoxfishModel(context.bakeLayer(ReefModelLayers.BOXFISH_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(Boxfish entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.landAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}