package net.thevaliantsquidward.rainbowreef;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.thevaliantsquidward.rainbowreef.block.ModBlocks;
import net.thevaliantsquidward.rainbowreef.entity.ModEntities;
import net.thevaliantsquidward.rainbowreef.entity.client.*;
import net.thevaliantsquidward.rainbowreef.item.ModCreativeModeTabs;
import net.thevaliantsquidward.rainbowreef.item.ModItems;
import net.thevaliantsquidward.rainbowreef.loot.ModLootModifiers;
import net.thevaliantsquidward.rainbowreef.sound.ModSounds;
import org.slf4j.Logger;

import java.util.Locale;


@Mod(RainbowReef.MOD_ID)
public class RainbowReef
{
    public static final String MOD_ID = "rainbowreef";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MOD_ID, name.toLowerCase(Locale.ROOT));
    }

    public RainbowReef()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModCreativeModeTabs.register(modEventBus);

        ModEntities.register(modEventBus);

        ModItems.register(modEventBus);

        ModSounds.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModBlocks.BLOCKS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register
                    (ModEntities.TANG.get(), TangRenderer:: new);
            EntityRenderers.register
                    (ModEntities.GOBY.get(), GobyRenderer:: new);
            EntityRenderers.register
                    (ModEntities.BOXFISH.get(), BoxfishRenderer:: new);
            EntityRenderers.register
                    (ModEntities.SMALL_SHARK.get(), SmallSharkRenderer:: new);
            EntityRenderers.register
                    (ModEntities.CLOWNFISH.get(), ClownfishRenderer:: new);
            EntityRenderers.register
                    (ModEntities.BUTTERFISH.get(), ButterfishRenderer:: new);
            EntityRenderers.register
                    (ModEntities.SEAHORSE.get(), SeahorseRenderer:: new);
            EntityRenderers.register
                    (ModEntities.DWARFANGEL.get(), DwarfAngelfishRenderer:: new);
            EntityRenderers.register
                    (ModEntities.PARROTFISH.get(), ParrotfishRenderer:: new);
            EntityRenderers.register
                    (ModEntities.HOGFISH.get(), HogfishRenderer:: new);
            EntityRenderers.register
                    (ModEntities.BASSLET.get(), BassletRenderer:: new);
            EntityRenderers.register
                    (ModEntities.PIPEFISH.get(), PipefishRenderer:: new);
            EntityRenderers.register
                    (ModEntities.CRAB.get(), CrabRenderer:: new);
            EntityRenderers.register
                    (ModEntities.RAY.get(), RayRenderer:: new);
            EntityRenderers.register
                    (ModEntities.MOORISH_IDOL.get(), MoorishIdolRenderer:: new);
            EntityRenderers.register
                    (ModEntities.ANGELFISH.get(), AngelfishRenderer:: new);
            EntityRenderers.register
                    (ModEntities.ARROW_CRAB.get(), ArrowCrabRenderer:: new);
            EntityRenderers.register
                    (ModEntities.JELLYFISH.get(), JellyfishRenderer:: new);
        }
    }
}
