package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.model.entity.CrabModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.CrabRenderState;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Angelfish;
import net.thevaliantsquidward.rainbowreef.entity.ArrowCrab;
import net.thevaliantsquidward.rainbowreef.entity.Crab;
import net.thevaliantsquidward.rainbowreef.entity.base.AbstractCrab;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;
import org.checkerframework.checker.units.qual.C;

public class CrabRenderer extends ReefRenderer<Crab, CrabRenderState> {
    public CrabRenderer(EntityRendererProvider.Context context) {
        super("crab", Crab.Variant.values(), context, new CrabModel(context.bakeLayer(ReefModelLayers.CRAB_LAYER)), 0.3F);
    }

    @Override
    public void extractRenderState(Crab entity, CrabRenderState state, float delta) {
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