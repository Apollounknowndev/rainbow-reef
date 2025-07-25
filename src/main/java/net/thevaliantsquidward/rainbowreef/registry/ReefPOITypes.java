package net.thevaliantsquidward.rainbowreef.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

import java.util.Set;

public interface ReefPOITypes {
    TagKey<PoiType> ANEMONES = TagKey.create(Registries.POINT_OF_INTEREST_TYPE, RainbowReef.id("anemones"));

    PoiType GREEN_NEM = register("green_nem", new PoiType(getBlockStates(ReefBlocks.GREEN_SEA_ANEMONE), 32, 6));
    PoiType ORANGE_NEM = register("orange_nem", new PoiType(getBlockStates(ReefBlocks.ORANGE_SEA_ANEMONE), 32, 6));
    PoiType YELLOW_NEM = register("yellow_nem", new PoiType(getBlockStates(ReefBlocks.YELLOW_SEA_ANEMONE), 32, 6));

    private static Set<BlockState> getBlockStates(Block block) {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }

    private static PoiType register(String name, PoiType type) {
        return Registry.register(BuiltInRegistries.POINT_OF_INTEREST_TYPE, RainbowReef.id(name), type);
    }

    static void init() {

    }
}