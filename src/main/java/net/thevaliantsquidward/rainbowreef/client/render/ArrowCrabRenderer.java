package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.ArrowCrabModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.CrabRenderState;
import net.thevaliantsquidward.rainbowreef.entity.ArrowCrab;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class ArrowCrabRenderer extends ReefRenderer<ArrowCrab, CrabRenderState> {
    public ArrowCrabRenderer(EntityRendererProvider.Context context) {
        super("arrow_crab", ArrowCrab.Variant.values(), context, new ArrowCrabModel(context.bakeLayer(ReefModelLayers.ARROWCRAB_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(ArrowCrab entity, CrabRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.idle.copyFrom(entity.idleAnimationState);
        state.walk.copyFrom(entity.walkAnimationState);
        state.dance.copyFrom(entity.danceAnimationState);
    }

    @Override
    public CrabRenderState createRenderState() {
        return new CrabRenderState();
    }
}