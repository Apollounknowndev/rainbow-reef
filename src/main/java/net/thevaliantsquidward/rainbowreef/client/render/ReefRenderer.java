package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefBaseMob;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariantHolder;

import java.util.HashMap;
import java.util.Map;

public abstract class ReefRenderer<E extends ReefBaseMob & ReefVariantHolder<?>, S extends ReefRenderState> extends MobRenderer<E, S, EntityModel<S>> {
    private final Map<ReefVariant, ResourceLocation> variantTextures;

    public ReefRenderer(String name, ReefVariant[] variants, EntityRendererProvider.Context context, EntityModel<S> model, float shadowRadius) {
        super(context, model, shadowRadius);
        this.variantTextures = new HashMap<>();
        for (ReefVariant variant : variants) {
            this.variantTextures.put(variant, RainbowReef.id("textures/entity/"+name+"/"+variant.getSerializedName()+".png"));
        }
    }

    @Override
    public void extractRenderState(E entity, S state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.variant = entity.getVariant();
    }

    @Override
    public ResourceLocation getTextureLocation(ReefRenderState state) {
        return this.variantTextures.get(state.variant);
    }
}
