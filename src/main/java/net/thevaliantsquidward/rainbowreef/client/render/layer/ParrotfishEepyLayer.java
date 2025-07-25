package net.thevaliantsquidward.rainbowreef.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.model.entity.ParrotfishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ParrotfishRenderState;
import net.thevaliantsquidward.rainbowreef.entity.Parrotfish;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;

public class ParrotfishEepyLayer extends RenderLayer<ParrotfishRenderState, EntityModel<ParrotfishRenderState>> {
    public ParrotfishEepyLayer(RenderLayerParent<ParrotfishRenderState, EntityModel<ParrotfishRenderState>> parent) {
        super(parent);
    }

    public ResourceLocation eepyTextures(ReefVariant variant) {
        return RainbowReef.id("textures/entity/parroteepy/parrotfish_sleepy_" + variant.getSerializedName() + ".png");
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, ParrotfishRenderState state, float f, float g) {
        if (!state.isInvisible && state.night && state.isInWater) {
            ResourceLocation texture = eepyTextures(state.variant);
            coloredCutoutModelCopyLayerRender(this.getParentModel(), texture, poseStack, buffer, packedLight, state, -1);
        }
    }
}