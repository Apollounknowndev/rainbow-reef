package net.thevaliantsquidward.rainbowreef;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.thevaliantsquidward.rainbowreef.client.model.entity.*;
import net.thevaliantsquidward.rainbowreef.client.render.*;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

import static net.thevaliantsquidward.rainbowreef.registry.ReefBlocks.*;

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
        EntityRendererRegistry.register(ReefEntities.BUTTERFLYFISH, ButterflyfishRenderer::new);
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
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.BUTTERFISH_LAYER, ButterflyfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.ARROWCRAB_LAYER, ArrowCrabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.DWARF_ANGELFISH_LAYER, DwarfAngelfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.BOXFISH_LAYER, BoxfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.BASSLET_LAYER, BassletModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.IDOL_LAYER, MoorishIdolModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.JELLYFISH_LAYER, JellyfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.HOGFISH_LAYER, HogfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ReefModelLayers.GOBY_LAYER, GobyModel::createBodyLayer);

        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT,
            CERULEAN_STARFISH,
            UMBER_STARFISH,
            TANGERINE_STARFISH,
            CARMINE_STARFISH,
            FUCHSIA_STARFISH,
            SAFFRON_STARFISH,
            CHARTREUSE_STARFISH,
            VIOLET_STARFISH,
            ORANGE_SEA_ANEMONE,
            YELLOW_SEA_ANEMONE,
            GREEN_SEA_ANEMONE,
            ORANGE_PUFFER_LANTERN,
            GREEN_PUFFER_LANTERN,
            BLUE_PUFFER_LANTERN
        );
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, BARREL_SET.translucentBlocks());
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, BUSH_SET.translucentBlocks());
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, CHIMNEY_SET.translucentBlocks());
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, FLOWER_SET.translucentBlocks());
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, HAND_SET.translucentBlocks());
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, RING_SET.translucentBlocks());
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, ROSE_SET.translucentBlocks());
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, SHELF_SET.translucentBlocks());
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, TOWER_SET.translucentBlocks());
        BlockRenderLayerMap.putBlock(JELLY_BLOCK, ChunkSectionLayer.TRANSLUCENT);
    }
}
