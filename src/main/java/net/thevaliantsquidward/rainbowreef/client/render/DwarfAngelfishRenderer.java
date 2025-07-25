package net.thevaliantsquidward.rainbowreef.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.thevaliantsquidward.rainbowreef.client.model.entity.DwarfAngelfishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;
import net.thevaliantsquidward.rainbowreef.entity.DwarfAngelfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class DwarfAngelfishRenderer extends ReefRenderer<DwarfAngelfish, ReefRenderState> {
    public DwarfAngelfishRenderer(EntityRendererProvider.Context context) {
        super("dwarfangelfish", DwarfAngelfish.Variant.values(), context, new DwarfAngelfishModel(context.bakeLayer(ReefModelLayers.DWARF_ANGELFISH_LAYER)), 0.4F);
    }

    @Override
    public void extractRenderState(DwarfAngelfish entity, ReefRenderState state, float delta) {
        super.extractRenderState(entity, state, delta);
        state.swim.copyFrom(entity.swimAnimationState);
        state.flop.copyFrom(entity.landAnimationState);
    }

    @Override
    public ReefRenderState createRenderState() {
        return new ReefRenderState();
    }
}