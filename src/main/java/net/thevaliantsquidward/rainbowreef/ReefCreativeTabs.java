package net.thevaliantsquidward.rainbowreef;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

import static net.minecraft.world.item.CreativeModeTab.Row.TOP;

public class ReefCreativeTabs {
    public static final CreativeModeTab RAINBOW_REEF_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, RainbowReef.id("rainbow_reef"),
       CreativeModeTab.builder(TOP, 0)
           .icon(() -> new ItemStack(ReefItems.RAW_TANG))
           .title(Component.translatable("creativetab.rainbow_reef_tab"))
           .displayItems((params, output) -> {
                output.accept(ReefItems.ANGELFISH_SPAWN_EGG);
                output.accept(ReefItems.ARROW_CRAB_SPAWN_EGG);
                output.accept(ReefItems.BASSLET_SPAWN_EGG);
                output.accept(ReefItems.BOXFISH_SPAWN_EGG);
                output.accept(ReefItems.BUTTERFISH_SPAWN_EGG);
                output.accept(ReefItems.CLOWNFISH_SPAWN_EGG);
                output.accept(ReefItems.CRAB_SPAWN_EGG);
                output.accept(ReefItems.DWARF_ANGEL_SPAWN_EGG);
                output.accept(ReefItems.GOBY_SPAWN_EGG);
                output.accept(ReefItems.HOGFISH_SPAWN_EGG);
                output.accept(ReefItems.JELLYFISH_SPAWN_EGG);
                output.accept(ReefItems.MOORISH_IDOL_SPAWN_EGG);
                output.accept(ReefItems.PARROTFISH_SPAWN_EGG);
                output.accept(ReefItems.PIPEFISH_SPAWN_EGG);
                output.accept(ReefItems.RAY_SPAWN_EGG);
                output.accept(ReefItems.SEAHORSE_SPAWN_EGG);
                output.accept(ReefItems.SMALL_SHARK_SPAWN_EGG);
                output.accept(ReefItems.TANG_SPAWN_EGG);

                output.accept(ReefItems.RAW_ANGELFISH);
                output.accept(ReefItems.RAW_ARROW_CRAB);
                output.accept(ReefItems.RAW_BASSLET);
                output.accept(ReefItems.RAW_BOXFISH);
                output.accept(ReefItems.RAW_BUTTERFISH);
                output.accept(ReefItems.RAW_CLOWNFISH);
                output.accept(ReefItems.RAW_CRAB_MEAT);
                output.accept(ReefItems.RAW_DWARF_ANGELFISH);
                output.accept(ReefItems.RAW_GOBY);
                output.accept(ReefItems.RAW_HOGFISH);
                output.accept(ReefItems.GLOB_OF_JELLY);
                output.accept(ReefItems.RAW_MOORISH_IDOL);
                output.accept(ReefItems.RAW_PARROTFISH);
                output.accept(ReefItems.RAW_PIPEFISH);
                output.accept(ReefItems.RAW_RAY);
                output.accept(ReefItems.RAW_SEAHORSE);
                output.accept(ReefItems.RAW_SMALL_SHARK);
                output.accept(ReefItems.RAW_TANG);

                output.accept(ReefItems.ANGELFISH_BUCKET);
                output.accept(ReefItems.ARROW_CRAB_BUCKET);
                output.accept(ReefItems.BASSLET_BUCKET);
                output.accept(ReefItems.BOXFISH_BUCKET);
                output.accept(ReefItems.BUTTERFISH_BUCKET);
                output.accept(ReefItems.CLOWNFISH_BUCKET);
                output.accept(ReefItems.CRAB_BUCKET);
                output.accept(ReefItems.DWARF_ANGELFISH_BUCKET);
                output.accept(ReefItems.GOBY_BUCKET);
                output.accept(ReefItems.HOGFISH_BUCKET);
                output.accept(ReefItems.JELLYFISH_BUCKET);
                output.accept(ReefItems.MOORISH_IDOL_BUCKET);
                output.accept(ReefItems.PARROTFISH_BUCKET);
                output.accept(ReefItems.PIPEFISH_BUCKET);
                output.accept(ReefItems.SEAHORSE_BUCKET);
                output.accept(ReefItems.TANG_BUCKET);
                output.accept(ReefItems.SHARK_BUCKET);
                output.accept(ReefItems.SPOTTED_EAGLE_RAY_BUCKET);

                output.accept(ReefBlocks.ANGELFISH_CAKE);
                output.accept(ReefItems.BASSLET_COOKIE);
                output.accept(ReefItems.BOXFISH_BREAD);
                output.accept(ReefItems.BUTTERED_BUTTERFLYFISH_TOAST);
                output.accept(ReefItems.CLOWNFISH_CUPCAKE);
                output.accept(ReefItems.ROASTED_CRAB_MEAT);
                output.accept(ReefItems.CRAB_CAKE);
                output.accept(ReefItems.DWARF_ANGELFISH_TARTS);
                output.accept(ReefItems.GOBY_GUMMY);
                output.accept(ReefItems.HOGFISH_BACON);
                output.accept(ReefItems.COOKED_HOGFISH_BACON);
                output.accept(ReefItems.BACON_SANDWICH);
                output.accept(ReefItems.JELLYFISH_JELLY);
                output.accept(ReefItems.JELLY_SANDWICH);
                output.accept(ReefItems.JELLY_TART);
                output.accept(ReefItems.IDOL_COOKIE);
                output.accept(ReefItems.PARROTFISH_PUNCH);
                output.accept(ReefItems.PIPEFISH_SUSHI);
                output.accept(ReefItems.CHOCO_RAY_MUFFIN);
                output.accept(ReefItems.DRIED_SEAHORSE);
                output.accept(ReefItems.TANGY_SOUP);
                output.accept(ReefItems.SHARKBITE_SALAD);

                output.accept(ReefItems.SEASUGAR_SORBET);
                output.accept(ReefItems.ROCKFISH_CANDY);
                output.accept(ReefItems.FORBIDDEN_SOUP);
                output.accept(ReefItems.SWEET_TOOTH_SEABURGER);
                output.accept(ReefItems.HAWAIIAN_BARBEQUE);
                output.accept(ReefItems.TROPICAL_FISHSTICKS);
                output.accept(ReefItems.SURF_N_TURF);

                output.accept(ReefItems.MUSIC_DISC_CLAW);

                output.accept(ReefBlocks.ORANGE_PUFFER_LANTERN);
                output.accept(ReefBlocks.GREEN_PUFFER_LANTERN);
                output.accept(ReefBlocks.BLUE_PUFFER_LANTERN);
                output.accept(ReefBlocks.ORANGE_SEA_ANEMONE);
                output.accept(ReefBlocks.YELLOW_SEA_ANEMONE);
                output.accept(ReefBlocks.GREEN_SEA_ANEMONE);

                output.accept(ReefBlocks.CARMINE_STARFISH);
                output.accept(ReefBlocks.CERULEAN_STARFISH);
                output.accept(ReefBlocks.CHARTREUSE_STARFISH);
                output.accept(ReefBlocks.FUCHSIA_STARFISH);
                output.accept(ReefBlocks.SAFFRON_STARFISH);
                output.accept(ReefBlocks.TANGERINE_STARFISH);
                output.accept(ReefBlocks.UMBER_STARFISH);
                output.accept(ReefBlocks.VIOLET_STARFISH);

                output.accept(ReefBlocks.BUBBLER);
                output.accept(ReefBlocks.RED_SAND_BUBBLER);
                output.accept(ReefBlocks.JELLY_BLOCK);

                output.accept(ReefBlocks.CORALSTONE);
                output.accept(ReefBlocks.POLISHED_CORALSTONE);
                output.accept(ReefBlocks.CORALSTONE_BRICKS);
                output.accept(ReefBlocks.CHISELED_CORALSTONE);

                output.accept(ReefBlocks.BARREL_SET.block());
                output.accept(ReefBlocks.BARREL_SET.plant());
                output.accept(ReefItems.BARREL_CORAL_FAN);
                output.accept(ReefBlocks.BUSH_SET.block());
                output.accept(ReefBlocks.BUSH_SET.plant());
                output.accept(ReefItems.BUSH_CORAL_FAN);
                output.accept(ReefBlocks.CHIMNEY_SET.block());
                output.accept(ReefBlocks.CHIMNEY_SET.plant());
                output.accept(ReefItems.CHIMNEY_CORAL_FAN);
                output.accept(ReefBlocks.FLOWER_SET.block());
                output.accept(ReefBlocks.FLOWER_SET.plant());
                output.accept(ReefItems.FLOWER_CORAL_FAN);
                output.accept(ReefBlocks.HAND_SET.block());
                output.accept(ReefBlocks.HAND_SET.plant());
                output.accept(ReefItems.HAND_CORAL_FAN);
                output.accept(ReefBlocks.RING_SET.block());
                output.accept(ReefBlocks.RING_SET.plant());
                output.accept(ReefItems.RING_CORAL_FAN);
                output.accept(ReefBlocks.ROSE_SET.block());
                output.accept(ReefBlocks.ROSE_SET.plant());
                output.accept(ReefItems.ROSE_CORAL_FAN);
                output.accept(ReefBlocks.SHELF_SET.block());
                output.accept(ReefBlocks.SHELF_SET.plant());
                output.accept(ReefItems.SHELF_CORAL_FAN);
                output.accept(ReefBlocks.TOWER_SET.block());
                output.accept(ReefBlocks.TOWER_SET.plant());
                output.accept(ReefItems.TOWER_CORAL_FAN);
                output.accept(ReefBlocks.BARREL_SET.deadBlock());
                output.accept(ReefBlocks.BARREL_SET.deadPlant());
                output.accept(ReefItems.DEAD_BARREL_CORAL_FAN);
                output.accept(ReefBlocks.BUSH_SET.deadBlock());
                output.accept(ReefBlocks.BUSH_SET.deadPlant());
                output.accept(ReefItems.DEAD_BUSH_CORAL_FAN);
                output.accept(ReefBlocks.CHIMNEY_SET.deadBlock());
                output.accept(ReefBlocks.CHIMNEY_SET.deadPlant());
                output.accept(ReefItems.DEAD_CHIMNEY_CORAL_FAN);
                output.accept(ReefBlocks.FLOWER_SET.deadBlock());
                output.accept(ReefBlocks.FLOWER_SET.deadPlant());
                output.accept(ReefItems.DEAD_FLOWER_CORAL_FAN);
                output.accept(ReefBlocks.HAND_SET.deadBlock());
                output.accept(ReefBlocks.HAND_SET.deadPlant());
                output.accept(ReefItems.DEAD_HAND_CORAL_FAN);
                output.accept(ReefBlocks.RING_SET.deadBlock());
                output.accept(ReefBlocks.RING_SET.deadPlant());
                output.accept(ReefItems.DEAD_RING_CORAL_FAN);
                output.accept(ReefBlocks.ROSE_SET.deadBlock());
                output.accept(ReefBlocks.ROSE_SET.deadPlant());
                output.accept(ReefItems.DEAD_ROSE_CORAL_FAN);
                output.accept(ReefBlocks.SHELF_SET.deadBlock());
                output.accept(ReefBlocks.SHELF_SET.deadPlant());
                output.accept(ReefItems.DEAD_SHELF_CORAL_FAN);
                output.accept(ReefBlocks.TOWER_SET.deadBlock());
                output.accept(ReefBlocks.TOWER_SET.deadPlant());
                output.accept(ReefItems.DEAD_TOWER_CORAL_FAN);
           }).build()
    );

    public static void init() {
    }
}
