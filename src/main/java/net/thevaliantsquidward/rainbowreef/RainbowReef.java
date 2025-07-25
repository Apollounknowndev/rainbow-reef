package net.thevaliantsquidward.rainbowreef;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.registry.*;
import net.thevaliantsquidward.rainbowreef.registry.ReefPOITypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RainbowReef implements ModInitializer {
    public static final String MOD_ID = "rainbowreef";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ReefBlocks.init();
        ReefItems.init();
        ReefEntities.init();
        ReefDataComponents.init();
        ReefConsumeEffects.init();
        ReefCreativeTabs.init();
        ReefSoundEvents.init();
        ReefFeatures.init();
        ReefPOITypes.init();
        ReefTrades.init();
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
