package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.model.entity.TangModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Pipefish;
import net.thevaliantsquidward.rainbowreef.entity.Tang;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class TangRenderer extends ReefRenderer<Tang, ReefRenderState> {
    public TangRenderer(EntityRendererProvider.Context context) {
        super("tang", Tang.Variant.values(), context, new TangModel(context.bakeLayer(ReefModelLayers.TANG_LAYER)), 0.3F);
    }

    @Override
    public void extractRenderState(Tang entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.flopAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}
