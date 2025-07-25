package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.model.entity.ParrotfishModel;
import net.thevaliantsquidward.rainbowreef.client.render.layer.ParrotfishEepyLayer;
import net.thevaliantsquidward.rainbowreef.client.render.state.ParrotfishRenderState;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefIdleableRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Goby;
import net.thevaliantsquidward.rainbowreef.entity.Parrotfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class ParrotfishRenderer extends ReefRenderer<Parrotfish, ParrotfishRenderState> {
    public ParrotfishRenderer(EntityRendererProvider.Context context) {
        super("parrotfish", Parrotfish.Variant.values(), context, new ParrotfishModel(context.bakeLayer(ReefModelLayers.PARROTFISH_LAYER)), 0.4F);
        this.addLayer(new ParrotfishEepyLayer(this));
    }

    @Override
    public void extractRenderState(Parrotfish entity, ParrotfishRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        long roundedTime = entity.level().getDayTime() % 24000;
        state.night = roundedTime >= 13000 && roundedTime <= 22000;
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.flopAnimationState);
        state.eepy.copyFrom(entity.eepyAnimationState);
    }

    @Override
    public ParrotfishRenderState createRenderState() {
        return new ParrotfishRenderState();
    }
}