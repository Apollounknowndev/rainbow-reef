package net.thevaliantsquidward.rainbowreef.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.*;
import net.thevaliantsquidward.rainbowreef.entity.base.AbstractCrab;

public interface ReefEntities {
    EntityType<Angelfish> ANGELFISH = register("angelfish", builder(Angelfish::new, MobCategory.WATER_AMBIENT, 0.6f, 0.6f));
    EntityType<ArrowCrab> ARROW_CRAB = register("arrow_crab", builder(ArrowCrab::new, MobCategory.CREATURE, 0.5f, 0.5f));
    EntityType<Basslet> BASSLET = register("basslet", builder(Basslet::new, MobCategory.WATER_AMBIENT, 0.5f, 0.5f));
    EntityType<Boxfish> BOXFISH = register("boxfish", builder(Boxfish::new, MobCategory.WATER_AMBIENT, 0.5f, 0.5f));
    EntityType<Butterfish> BUTTERFISH = register("butterflyfish", builder(Butterfish::new, MobCategory.WATER_AMBIENT, 0.5f, 0.5f));
    EntityType<Clownfish> CLOWNFISH = register("clownfish", builder(Clownfish::new, MobCategory.WATER_AMBIENT, 0.3f, 0.3f));
    EntityType<Crab> CRAB = register("crab", builder(Crab::new, MobCategory.CREATURE, 0.5f, 0.5f));
    EntityType<DwarfAngelfish> DWARF_ANGELFISH = register("dwarf_angelfish", builder(DwarfAngelfish::new, MobCategory.WATER_AMBIENT, 0.5f, 0.5f));
    EntityType<Goby> GOBY = register("goby", builder(Goby::new, MobCategory.WATER_AMBIENT, 0.5f, 0.5f));
    EntityType<Hogfish> HOGFISH = register("hogfish", builder(Hogfish::new, MobCategory.WATER_AMBIENT, 0.5f, 0.5f));
    EntityType<Jellyfish> JELLYFISH = register("jellyfish", builder(Jellyfish::new, MobCategory.WATER_AMBIENT, 0.9f, 0.9f));
    EntityType<MoorishIdol> MOORISH_IDOL = register("moorish_idol", builder(MoorishIdol::new, MobCategory.WATER_AMBIENT, 0.5f, 0.5f));
    EntityType<Parrotfish> PARROTFISH = register("parrotfish", builder(Parrotfish::new, MobCategory.WATER_AMBIENT, 0.6f, 0.6f));
    EntityType<Pipefish> PIPEFISH = register("pipefish", builder(Pipefish::new, MobCategory.WATER_AMBIENT, 0.5f, 0.5f));
    EntityType<Ray> RAY = register("ray", builder(Ray::new, MobCategory.WATER_AMBIENT, 1f, 0.6f));
    EntityType<Seahorse> SEAHORSE = register("seahorse", builder(Seahorse::new, MobCategory.WATER_AMBIENT, 0.5f, 0.5f));
    EntityType<SmallShark> SMALL_SHARK = register("small_shark", builder(SmallShark::new, MobCategory.WATER_AMBIENT, 0.7f, 0.7f));
    EntityType<Tang> TANG = register("tang", builder(Tang::new, MobCategory.WATER_AMBIENT, 0.5f, 0.5f));

    //EntityType<EelEntity> EEL = register("eel", builder(EelEntity::new, MobCategory.WATER_AMBIENT, 0.6f, 0.4f));
    
    private static <T extends Entity> EntityType.Builder<T> builder(EntityType.EntityFactory<T> factory, MobCategory category, float width, float height) {
        return EntityType.Builder.of(factory, category).sized(width, height).clientTrackingRange(10);
    }
    
    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        var key = ResourceKey.create(Registries.ENTITY_TYPE, RainbowReef.id(name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    static void init() {
        SpawnPlacements.register(ANGELFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(ARROW_CRAB, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractCrab::canSpawn);
        SpawnPlacements.register(BASSLET, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(BOXFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(BUTTERFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CLOWNFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CRAB, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractCrab::canSpawn);
        SpawnPlacements.register(DWARF_ANGELFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(GOBY, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(HOGFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(JELLYFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(MOORISH_IDOL, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(PARROTFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(PIPEFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(RAY, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(SEAHORSE, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(SMALL_SHARK, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(TANG, SpawnPlacementTypes.IN_WATER, Heightmap.Types.WORLD_SURFACE, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);

        FabricDefaultAttributeRegistry.register(ANGELFISH, Angelfish.setAttributes());
        FabricDefaultAttributeRegistry.register(ARROW_CRAB, AbstractCrab.setAttributes());
        FabricDefaultAttributeRegistry.register(BASSLET, Basslet.setAttributes());
        FabricDefaultAttributeRegistry.register(BOXFISH, Boxfish.setAttributes());
        FabricDefaultAttributeRegistry.register(BUTTERFISH, Butterfish.setAttributes());
        FabricDefaultAttributeRegistry.register(CLOWNFISH, Clownfish.setAttributes());
        FabricDefaultAttributeRegistry.register(CRAB, AbstractCrab.setAttributes());
        FabricDefaultAttributeRegistry.register(DWARF_ANGELFISH, DwarfAngelfish.setAttributes());
        FabricDefaultAttributeRegistry.register(GOBY, Goby.setAttributes());
        FabricDefaultAttributeRegistry.register(HOGFISH, Goby.setAttributes());
        FabricDefaultAttributeRegistry.register(JELLYFISH, Jellyfish.setAttributes());
        FabricDefaultAttributeRegistry.register(MOORISH_IDOL, MoorishIdol.setAttributes());
        FabricDefaultAttributeRegistry.register(PARROTFISH, Parrotfish.setAttributes());
        FabricDefaultAttributeRegistry.register(PIPEFISH, Pipefish.setAttributes());
        FabricDefaultAttributeRegistry.register(RAY, Ray.setAttributes());
        FabricDefaultAttributeRegistry.register(SEAHORSE, Seahorse.setAttributes());
        FabricDefaultAttributeRegistry.register(SMALL_SHARK, SmallShark.setAttributes());
        FabricDefaultAttributeRegistry.register(TANG, Tang.setAttributes());
    }
}
