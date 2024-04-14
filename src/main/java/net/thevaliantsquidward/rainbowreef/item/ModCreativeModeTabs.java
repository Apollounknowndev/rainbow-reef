package net.thevaliantsquidward.rainbowreef.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.block.ModBlocks;

public class ModCreativeModeTabs {
public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RainbowReef.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RAINBOW_REEF_TAB = CREATIVE_MODE_TABS.register("rainbow_reef_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAW_TANG.get()))
                    .title(Component.translatable("creativetab.rainbow_reef_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        //order:
                        //basslet
                        //boxfish
                        //butterfish
                        //clownfish
                        //dwarf angelfish
                        //goby
                        //hogfish
                        //parrotfish
                        //pipefish
                        //seahorse
                        //small shork
                        //tang

                        pOutput.accept(ModItems.RAW_BASSLET.get());
                        pOutput.accept(ModItems.RAW_BOXFISH.get());
                        pOutput.accept(ModItems.RAW_BUTTERFISH.get());
                        pOutput.accept(ModItems.RAW_CLOWNFISH.get());
                        pOutput.accept(ModItems.RAW_DWARF_ANGELFISH.get());
                        pOutput.accept(ModItems.RAW_GOBY.get());
                        pOutput.accept(ModItems.RAW_HOGFISH.get());
                        pOutput.accept(ModItems.RAW_PARROTFISH.get());
                        pOutput.accept(ModItems.RAW_PIPEFISH.get());
                        pOutput.accept(ModItems.RAW_SEAHORSE.get());
                        pOutput.accept(ModItems.RAW_TANG.get());


                        pOutput.accept(ModItems.BASSLET_BUCKET.get());
                        pOutput.accept(ModItems.BOXFISH_BUCKET.get());
                        pOutput.accept(ModItems.BUTTERFISH_BUCKET.get());
                        pOutput.accept(ModItems.CLOWNFISH_BUCKET.get());
                        pOutput.accept(ModItems.DWARF_ANGELFISH_BUCKET.get());
                        pOutput.accept(ModItems.GOBY_BUCKET.get());
                        pOutput.accept(ModItems.HOGFISH_BUCKET.get());
                        pOutput.accept(ModItems.PARROTFISH_BUCKET.get());
                        pOutput.accept(ModItems.PIPEFISH_BUCKET.get());
                        pOutput.accept(ModItems.SEAHORSE_BUCKET.get());
                        pOutput.accept(ModItems.TANG_BUCKET.get());
                        pOutput.accept(ModItems.SHARK_BUCKET.get());
                        pOutput.accept(ModItems.SPOTTED_EAGLE_RAY_BUCKET.get());


                        pOutput.accept(ModItems.BASSLET_SPAWN_EGG.get());
                        pOutput.accept(ModItems.BOXFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.BUTTERFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.CLOWNFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.DWARF_ANGEL_SPAWN_EGG.get());
                        pOutput.accept(ModItems.GOBY_SPAWN_EGG.get());
                        pOutput.accept(ModItems.HOGFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.PARROTFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.PIPEFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.RAY_SPAWN_EGG.get());
                        pOutput.accept(ModItems.SEAHORSE_SPAWN_EGG.get());
                        pOutput.accept(ModItems.TANG_SPAWN_EGG.get());
                        pOutput.accept(ModItems.SMALL_SHARK_SPAWN_EGG.get());

                        pOutput.accept(ModItems.BASSLET_COOKIE.get());
                        pOutput.accept(ModItems.BOXFISH_BREAD.get());
                        pOutput.accept(ModItems.BUTTERED_BUTTERFLYFISH_TOAST.get());
                        pOutput.accept(ModItems.CLOWNFISH_CUPCAKE.get());
                        pOutput.accept(ModItems.DWARF_ANGELFISH_TARTS.get());
                        pOutput.accept(ModItems.GOBY_GUMMY.get());
                        pOutput.accept(ModItems.HOGFISH_BACON.get());
                        pOutput.accept(ModItems.COOKED_HOGFISH_BACON.get());
                        pOutput.accept(ModItems.ULTRA_BACON_SANDWICH.get());
                        pOutput.accept(ModItems.PARROTFISH_PUNCH.get());
                        pOutput.accept(ModItems.PIPEFISH_SUSHI.get());
                        pOutput.accept(ModItems.DRIED_SEAHORSE.get());
                        pOutput.accept(ModItems.TANGY_SOUP.get());


                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> RAINBOW_REEF_BLOCKS = CREATIVE_MODE_TABS.register("rainbow_reef_block_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CORALSTONE.get()))
                    .title(Component.translatable("creativetab.rainbow_reef_block_tab"))
                    .displayItems((pParameters, pOutput) -> {


                        pOutput.accept(ModBlocks.CORALSTONE.get());
                        pOutput.accept(ModBlocks.CORALSTONE_BRICKS.get());
                        pOutput.accept(ModBlocks.POLISHED_CORALSTONE.get());
                        pOutput.accept(ModBlocks.CHISELED_CORALSTONE.get());
                        pOutput.accept(ModBlocks.BUBBLER.get());
                        pOutput.accept(ModBlocks.BLUE_PUFFER_LANTERN.get());
                        pOutput.accept(ModBlocks.GREEN_PUFFER_LANTERN.get());
                        pOutput.accept(ModBlocks.ORANGE_PUFFER_LANTERN.get());
                        pOutput.accept(ModBlocks.MUD_BURROW.get());
                        pOutput.accept(ModBlocks.SAND_BURROW.get());
                        pOutput.accept(ModBlocks.STONE_BURROW.get());
                        pOutput.accept(ModBlocks.SHELF_CORAL.get());
                        pOutput.accept(ModBlocks.DEAD_SHELF_CORAL.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
