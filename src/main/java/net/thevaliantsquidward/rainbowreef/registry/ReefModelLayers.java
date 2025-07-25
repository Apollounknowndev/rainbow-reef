package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

public interface ReefModelLayers {
    ModelLayerLocation ANGELFISH_LAYER = main("angelfish");
    ModelLayerLocation CRAB_LAYER = main("crab");
    ModelLayerLocation CLOWNFISH_LAYER = main("clownfish");
    ModelLayerLocation PARROTFISH_LAYER = main("parrotfish");
    ModelLayerLocation PIPEFISH_LAYER = main("pipefish");
    ModelLayerLocation RAY_LAYER = main("ray");
    ModelLayerLocation SEAHORSE_LAYER = main("seahorse");
    ModelLayerLocation SMALL_SHARK_LAYER = main("small_shark");
    ModelLayerLocation TANG_LAYER = main("tang");
    ModelLayerLocation BUTTERFISH_LAYER = main("butterfish");
    ModelLayerLocation ARROWCRAB_LAYER = main("arrowcrab");
    ModelLayerLocation DWARF_ANGELFISH_LAYER = main("dwarf_angelfish");
    ModelLayerLocation BOXFISH_LAYER = main("boxfish");
    ModelLayerLocation BASSLET_LAYER = main("basslet");
    ModelLayerLocation IDOL_LAYER = main("moorish_idol");
    ModelLayerLocation JELLYFISH_LAYER = main("jellyfish");
    ModelLayerLocation HOGFISH_LAYER = main("hogfish");
    ModelLayerLocation GOBY_LAYER = main("goby");

    private static ModelLayerLocation register(String id, String name) {
        return new ModelLayerLocation(RainbowReef.id(id), name);
    }

    private static ModelLayerLocation main(String id) {
        return register(id, "main");
    }
}
