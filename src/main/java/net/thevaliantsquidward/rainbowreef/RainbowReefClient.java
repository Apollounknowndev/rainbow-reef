package net.thevaliantsquidward.rainbowreef;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.thevaliantsquidward.rainbowreef.client.model.entity.*;
import net.thevaliantsquidward.rainbowreef.client.render.*;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class RainbowReefClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ReefEntities.ANGELFISH, AngelfishRenderer::new);
        EntityRendererRegistry.register(ReefEntities.CRAB, CrabRenderer::new);
        EntityRendererRegistry.register(ReefEntities.CLOWNFISH, ClownfishRenderer::new);
        EntityRendererRegistry.register(ReefEntities.PARROTFISH, ParrotfishRenderer::new);
        EntityRendererRegistry.register(ReefEntities.PIPEFISH, PipefishRenderer::new);
        EntityRendererRegistry.register(ReefEntities.RAY, RayRenderer::new);
        EntityRendererRegistry.register(ReefEntities.SEAHORSE, SeahorseRenderer::new);
        EntityRendererRegistry.register(ReefEntities.SMALL_SHARK, SmallSharkRenderer::new);
        EntityRendererRegistry.register(ReefEntities.TANG, TangRenderer::new);
        EntityRendererRegistry.register(ReefEntities.BUTTERFISH, ButterfishRenderer::new);
        EntityRendererRegistry.register(ReefEntities.ARROW_CRAB, ArrowCrabRenderer::new);
        EntityRendererRegistry.register(ReefEntities.DWARF_ANGELFISH, DwarfAngelfishRenderer::new);
        EntityRendererRegistry.register(ReefEntities.BOXFISH, BoxfishRenderer::new);
        EntityRendererRegistry.register(ReefEntities.BASSLET, BassletRenderer::new);
        EntityRendererRegistry.register(ReefEntities.MOORISH_IDOL, MoorishIdolRenderer::new);
        EntityRendererRegistry.register(ReefEntities.JELLYFISH, JellyfishRenderer::new);
        EntityRendererRegistry.register(ReefEntities.HOGFISH, HogfishRenderer::new);
        EntityRendererRegistry.register(ReefEntities.GOBY, GobyRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.ANGELFISH_LAYER, AngelfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.CRAB_LAYER, CrabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.CLOWNFISH_LAYER, ClownfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.PARROTFISH_LAYER, ParrotfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.PIPEFISH_LAYER, PipefishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.RAY_LAYER, RayModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.SEAHORSE_LAYER, SeahorseModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.SMALL_SHARK_LAYER, SmallSharkModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.TANG_LAYER, TangModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.BUTTERFISH_LAYER, ButterfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.ARROWCRAB_LAYER, ArrowCrabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.DWARF_ANGELFISH_LAYER, DwarfAngelfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.BOXFISH_LAYER, BoxfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.BASSLET_LAYER, BassletModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.IDOL_LAYER, MoorishIdolModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.JELLYFISH_LAYER, JellyfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.HOGFISH_LAYER, HogfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.GOBY_LAYER, GobyModel::createBodyLayer);
    }
}
