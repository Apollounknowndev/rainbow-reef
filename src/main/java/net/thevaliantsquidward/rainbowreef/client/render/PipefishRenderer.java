package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.model.entity.PipefishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Pipefish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class PipefishRenderer extends ReefRenderer<Pipefish, ReefRenderState> {
    public PipefishRenderer(EntityRendererProvider.Context context) {
        super("pipefish", Pipefish.Variant.values(), context, new PipefishModel(context.bakeLayer(ReefModelLayers.PIPEFISH_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(Pipefish entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.flopAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}