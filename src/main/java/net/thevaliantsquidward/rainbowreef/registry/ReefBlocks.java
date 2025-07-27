package net.thevaliantsquidward.rainbowreef.registry;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.blocks.*;
import net.thevaliantsquidward.rainbowreef.blocks.set.CoralBlockSet;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

public interface ReefBlocks {
    Block CORALSTONE = register("coralstone", properties -> new Block(properties.strength(3f, 3f).requiresCorrectToolForDrops()), Blocks.DEAD_BUBBLE_CORAL_BLOCK);
    Block CORALSTONE_BRICKS = register("coralstone_bricks", properties -> new Block(properties.strength(3f, 3f).requiresCorrectToolForDrops()), Blocks.DEAD_BUBBLE_CORAL_BLOCK);
    Block POLISHED_CORALSTONE = register("polished_coralstone", properties -> new Block(properties.strength(3f, 3f).requiresCorrectToolForDrops()), Blocks.DEAD_BUBBLE_CORAL_BLOCK);
    Block CHISELED_CORALSTONE = register("chiseled_coralstone", properties -> new Block(properties.strength(3f, 3f).requiresCorrectToolForDrops()), Blocks.DEAD_BUBBLE_CORAL_BLOCK);
    Block JELLY_BLOCK = register("jelly_block", properties -> new JellyBlock(properties.friction(0.5F).noOcclusion()), Blocks.SLIME_BLOCK);

    Block BUBBLER = register("bubbler", properties -> new BubblerBlock(properties.instrument(NoteBlockInstrument.SNARE).strength(0.5F)), Blocks.SAND);
    Block RED_SAND_BUBBLER = register("red_sand_bubbler", properties -> new RedSandBubblerBlock(properties.instrument(NoteBlockInstrument.SNARE).strength(0.5F)), Blocks.RED_SAND);
    Block FAKE_BUBBLES = register("fake_bubbles", FakeBubbleBlock::new, Blocks.BUBBLE_COLUMN);
    Block FAKE_BUBBLES_RED_SAND = register("fake_bubbles_red_sand", FakeBubbleBlockRedSand::new, Blocks.BUBBLE_COLUMN);

    CoralBlockSet BARREL_SET = CoralBlockSet.create("barrel", MapColor.WARPED_NYLIUM);
    CoralBlockSet BUSH_SET = CoralBlockSet.create("bush", MapColor.COLOR_PURPLE);
    CoralBlockSet CHIMNEY_SET = CoralBlockSet.create("chimney", MapColor.TERRACOTTA_BLACK);
    CoralBlockSet FLOWER_SET = CoralBlockSet.create("flower", MapColor.TERRACOTTA_YELLOW);
    CoralBlockSet HAND_SET = CoralBlockSet.create("hand", MapColor.COLOR_ORANGE);
    CoralBlockSet RING_SET = CoralBlockSet.create("ring", MapColor.LAPIS);
    CoralBlockSet ROSE_SET = CoralBlockSet.create("rose", MapColor.CRIMSON_NYLIUM);
    CoralBlockSet SHELF_SET = CoralBlockSet.create("shelf", MapColor.COLOR_LIGHT_GREEN);
    CoralBlockSet TOWER_SET = CoralBlockSet.create("tower", MapColor.COLOR_MAGENTA);

    Block ANGELFISH_CAKE = register("angelfish_cake", AngelfishCakeBlock::new, Blocks.CAKE);

    Block BLUE_PUFFER_LANTERN = register("blue_puffer_lantern", properties -> new BasePufferLanternBlock(properties.strength(0.5F, 0.0F)), Blocks.LANTERN);
    Block GREEN_PUFFER_LANTERN = register("green_puffer_lantern", properties -> new BasePufferLanternBlock(properties.strength(0.5F, 0.0F)), Blocks.LANTERN);
    Block ORANGE_PUFFER_LANTERN = register("orange_puffer_lantern", properties -> new BasePufferLanternBlock(properties.strength(0.5F, 0.0F)), Blocks.LANTERN);

    BlockBehaviour.Properties SEA_ANEMONE_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.5F).sound(SoundType.FROGLIGHT).randomTicks().noOcclusion();
    Block YELLOW_SEA_ANEMONE = register("yellow_sea_anemone", AnemoneBlock::new, SEA_ANEMONE_PROPERTIES);
    Block ORANGE_SEA_ANEMONE = register("orange_sea_anemone", AnemoneBlock::new, SEA_ANEMONE_PROPERTIES);
    Block GREEN_SEA_ANEMONE = register("green_sea_anemone", AnemoneBlock::new, SEA_ANEMONE_PROPERTIES);

    BlockBehaviour.Properties STARFISH_PROPERTIES = BlockBehaviour.Properties.of().instabreak().noCollission().sound(SoundType.CORAL_BLOCK);
    Block CERULEAN_STARFISH = register("cerulean_starfish", StarfishBlock::new, STARFISH_PROPERTIES);
    Block UMBER_STARFISH = register("umber_starfish", StarfishBlock::new, STARFISH_PROPERTIES);
    Block TANGERINE_STARFISH = register("tangerine_starfish", StarfishBlock::new, STARFISH_PROPERTIES);
    Block CARMINE_STARFISH = register("carmine_starfish", StarfishBlock::new, STARFISH_PROPERTIES);
    Block FUCHSIA_STARFISH = register("fuchsia_starfish", StarfishBlock::new, STARFISH_PROPERTIES);
    Block SAFFRON_STARFISH = register("saffron_starfish", StarfishBlock::new, STARFISH_PROPERTIES);
    Block CHARTREUSE_STARFISH = register("chartreuse_starfish", StarfishBlock::new, STARFISH_PROPERTIES);
    Block VIOLET_STARFISH = register("violet_starfish", StarfishBlock::new, STARFISH_PROPERTIES);

    static Block register(String name, Function<BlockBehaviour.Properties, Block> creator, Block copyOf) {
        return register(name, creator, BlockBehaviour.Properties.ofFullCopy(copyOf));
    }

    static Block registerStandalone(String name, Function<BlockBehaviour.Properties, Block> creator, Block copyOf) {
        return register(name, creator, BlockBehaviour.Properties.ofFullCopy(copyOf), false);
    }

    static Block register(String name, Function<BlockBehaviour.Properties, Block> creator, BlockBehaviour.Properties properties) {
        return register(name, creator, properties, true);
    }

    static Block register(String name, Function<BlockBehaviour.Properties, Block> creator, BlockBehaviour.Properties properties, boolean item) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, RainbowReef.id(name));
        BlockBehaviour.Properties behaviour = properties.setId(key);
        Block block = Registry.register(BuiltInRegistries.BLOCK, key, creator.apply(behaviour));
        if (item) registerItem(name, block);
        return block;
    }

    private static void registerItem(String name, Block block) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, RainbowReef.id(name));
        Registry.register(BuiltInRegistries.ITEM, key, new BlockItem(block, new Item.Properties().setId(key).useBlockDescriptionPrefix()));
    }

    static void init() {
    }
}
