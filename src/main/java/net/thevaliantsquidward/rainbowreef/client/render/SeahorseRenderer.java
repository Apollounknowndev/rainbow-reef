package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.model.entity.SeahorseModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Pipefish;
import net.thevaliantsquidward.rainbowreef.entity.Seahorse;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class SeahorseRenderer extends ReefRenderer<Seahorse, ReefRenderState> {
    public SeahorseRenderer(EntityRendererProvider.Context context) {
        super("seahorse", Seahorse.Variant.values(), context, new SeahorseModel(context.bakeLayer(ReefModelLayers.SEAHORSE_LAYER)), 0.3F);
    }

    @Override
    public void extractRenderState(Seahorse entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.flopAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}