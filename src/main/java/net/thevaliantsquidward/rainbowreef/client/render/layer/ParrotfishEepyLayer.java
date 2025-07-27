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

import java.util.HashMap;
import java.util.Map;

public class ParrotfishEepyLayer extends RenderLayer<ParrotfishRenderState, EntityModel<ParrotfishRenderState>> {
    private final Map<ReefVariant, ResourceLocation> variantTextures;

    public ParrotfishEepyLayer(RenderLayerParent<ParrotfishRenderState, EntityModel<ParrotfishRenderState>> parent) {
        super(parent);
        this.variantTextures = new HashMap<>();
        for (ReefVariant variant : Parrotfish.Variant.values()) {
            this.variantTextures.put(variant, RainbowReef.id("textures/entity/parrotfish/sleepy/"+variant.getSerializedName()+".png"));
        }
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, ParrotfishRenderState state, float f, float g) {
        if (!state.isInvisible && state.night && state.isInWater) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), variantTextures.get(state.variant), poseStack, buffer, packedLight, state, -1);
        }
    }
}