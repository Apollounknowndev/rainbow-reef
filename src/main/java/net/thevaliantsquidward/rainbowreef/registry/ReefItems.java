package net.thevaliantsquidward.rainbowreef.registry;


import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.material.Fluids;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.blocks.set.CoralBlockSet;
import net.thevaliantsquidward.rainbowreef.items.*;
import net.thevaliantsquidward.rainbowreef.util.ReefDamageTypes;
import net.thevaliantsquidward.rainbowreef.util.ReefJukeboxSongs;

import java.util.List;
import java.util.function.Function;

public interface ReefItems {
    Consumable FAST_EATING = Consumable.builder().consumeSeconds(0.8f).build();

    Item GOBY_SPAWN_EGG = spawnEgg("goby", ReefEntities.GOBY);
    Item RAY_SPAWN_EGG = spawnEgg("ray", ReefEntities.RAY);
    Item PIPEFISH_SPAWN_EGG = spawnEgg("pipefish", ReefEntities.PIPEFISH);
    Item TANG_SPAWN_EGG = spawnEgg("tang", ReefEntities.TANG);
    Item SEAHORSE_SPAWN_EGG = spawnEgg("seahorse", ReefEntities.SEAHORSE);
    Item BOXFISH_SPAWN_EGG = spawnEgg("boxfish", ReefEntities.BOXFISH);
    Item PARROTFISH_SPAWN_EGG = spawnEgg("parrotfish", ReefEntities.PARROTFISH);
    Item DWARF_ANGEL_SPAWN_EGG = spawnEgg("dwarf_angelfish", ReefEntities.DWARF_ANGELFISH);
    Item SMALL_SHARK_SPAWN_EGG = spawnEgg("small_shark", ReefEntities.SMALL_SHARK);
    Item CLOWNFISH_SPAWN_EGG = spawnEgg("clownfish", ReefEntities.CLOWNFISH);
    Item BASSLET_SPAWN_EGG = spawnEgg("basslet", ReefEntities.BASSLET);
    Item BUTTERFISH_SPAWN_EGG = spawnEgg("butterflyfish", ReefEntities.BUTTERFLYFISH);
    Item HOGFISH_SPAWN_EGG = spawnEgg("hogfish", ReefEntities.HOGFISH);
    Item ANGELFISH_SPAWN_EGG = spawnEgg("angelfish", ReefEntities.ANGELFISH);
    Item CRAB_SPAWN_EGG = spawnEgg("crab", ReefEntities.CRAB);
    Item ARROW_CRAB_SPAWN_EGG = spawnEgg("arrow_crab", ReefEntities.ARROW_CRAB);
    Item MOORISH_IDOL_SPAWN_EGG = spawnEgg("moorish_idol", ReefEntities.MOORISH_IDOL);
    Item JELLYFISH_SPAWN_EGG = spawnEgg("jellyfish", ReefEntities.JELLYFISH);

    // Buckets
    Item GOBY_BUCKET = bucket("goby", ReefEntities.GOBY);
    Item ANGELFISH_BUCKET = bucket("angelfish", ReefEntities.ANGELFISH);
    Item CRAB_BUCKET = bucket("crab", ReefEntities.CRAB);
    Item PIPEFISH_BUCKET = bucket("pipefish", ReefEntities.PIPEFISH);
    Item DWARF_ANGELFISH_BUCKET = bucket("dwarf_angelfish", ReefEntities.DWARF_ANGELFISH);
    Item TANG_BUCKET = bucket("tang", ReefEntities.TANG);
    Item BASSLET_BUCKET = bucket("basslet", ReefEntities.BASSLET);
    Item CLOWNFISH_BUCKET = bucket("clownfish", ReefEntities.CLOWNFISH);
    Item BOXFISH_BUCKET = bucket("boxfish", ReefEntities.BOXFISH);
    Item ARROW_CRAB_BUCKET = bucket("arrow_crab", ReefEntities.ARROW_CRAB);
    Item BUTTERFISH_BUCKET = bucket("butterflyfish", ReefEntities.BUTTERFLYFISH);
    Item SHARK_BUCKET = bucket("smallshark", ReefEntities.SMALL_SHARK);
    Item SEAHORSE_BUCKET = bucket("seahorse", ReefEntities.SEAHORSE);
    Item HOGFISH_BUCKET = bucket("hogfish", ReefEntities.HOGFISH);
    Item JELLYFISH_BUCKET = bucket("jellyfish", ReefEntities.JELLYFISH);
    Item PARROTFISH_BUCKET = bucket("parrotfish", ReefEntities.PARROTFISH);
    Item MOORISH_IDOL_BUCKET = bucket("moorish_idol", ReefEntities.MOORISH_IDOL);
    Item SPOTTED_EAGLE_RAY_BUCKET = bucket("spotted_eagle_ray", ReefEntities.RAY);

    // Raw
    Item RAW_GOBY = rawFood("raw_goby");
    Item RAW_RAY = rawHeartyFood("raw_ray");
    Item RAW_SMALL_SHARK = rawHeartyFood("raw_small_shark");
    Item RAW_ANGELFISH = rawFood("raw_angelfish");
    Item RAW_MOORISH_IDOL = rawFood("raw_moorish_idol");
    Item RAW_CRAB_MEAT = rawFood("crab_meat");
    Item RAW_ARROW_CRAB = rawFood("arrow_crab");
    Item RAW_TANG = rawFood("raw_tang");
    Item RAW_HOGFISH = rawFood("raw_hogfish");
    Item RAW_BOXFISH = register("raw_boxfish", properties -> new Item(properties.food(
        new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(0.4F).build(),
        Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WITHER, 140, 2))).onConsume(new KillConsumeEffect(ReefDamageTypes.ATE_BOXFISH, 0.01f)).build()
    )));
    Item RAW_CLOWNFISH = rawFood("raw_clownfish");
    Item RAW_BUTTERFISH = rawFood("raw_butterflyfish");
    Item RAW_SEAHORSE = rawFood("raw_seahorse");
    Item RAW_BASSLET = rawFood("raw_basslet");
    Item RAW_PIPEFISH = rawFood("raw_pipefish");
    Item RAW_PARROTFISH = rawHeartyFood("raw_parrotfish");
    Item RAW_DWARF_ANGELFISH = rawFood("raw_dwarf_angelfish");

    // Meals
    Item BASSLET_COOKIE = food("basslet_cookie", 2, 0.1F, false);
    Item BOXFISH_BREAD = food("boxfish_bread", 8, 0.4F, false);
    Item BUTTERED_BUTTERFLYFISH_TOAST = register("buttered_butterflyfish", properties -> new Item(properties.craftRemainder(Items.BUCKET).food(food(8, 0F, false))));
    Item CRAB_CAKE = food("crabcake", 8, 0.5F, false);
    Item CLOWNFISH_CUPCAKE = register("clownfish_cupcake", properties -> new Item(properties.craftRemainder(Items.BUCKET).food(food(1, 1, true))));
    Item CHOCO_RAY_MUFFIN = register("choco_ray_muffin", properties -> new Item(properties.craftRemainder(Items.BUCKET).food(food(7, 0.3f, true))));
    Item COOKED_HOGFISH_BACON = food("cooked_hogfish_bacon", 7, 0.4F, false);
    Item DRIED_SEAHORSE = register("dried_seahorse", properties -> new Item(properties.food(
        new FoodProperties.Builder().nutrition(1).saturationModifier(0.4F).alwaysEdible().build(),
        Consumables.defaultFood().onConsume(new ClearNegativeEffectsConsumeEffect()).build()
    )));
    Item DWARF_ANGELFISH_TARTS = register("dwarf_angelfish_tarts", properties -> new Item(properties.craftRemainder(Items.BUCKET).food(food(1, 0.5f, false))));
    Item GOBY_GUMMY = register("goby_gummy", properties -> new Item(properties.craftRemainder(Items.BUCKET).food(
        food(2, 0.5f, false),
        FAST_EATING
    )));
    Item JELLY_TART = register("jelly_tart", properties -> new Item(properties.craftRemainder(Items.BUCKET).food(food(4, 0.5f, true))));
    Item JELLY_SANDWICH = register("jelly_sandwich", properties -> new Item(properties.craftRemainder(Items.BUCKET).food(food(8, 0.5f, true))));
    Item JELLYFISH_JELLY = register("jellyfish_jelly", properties -> new Item(properties.stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).food(
        food(4, 0.5f, true),
        Consumables.defaultDrink().sound(SoundEvents.HONEY_DRINK).onConsume(new ClearNegativeEffectsConsumeEffect()).build()
    )));
    Item HOGFISH_BACON = food("hogfish_bacon", 1, 0.5F, false);
    Item IDOL_COOKIE = food("idol_cookie", 2, 0.1F, false);
    Item PARROTFISH_PUNCH = register("parrotfish_punch", properties -> new Item(properties.stacksTo(16).food(
        food(5, 1.2f, true),
        effect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 1), new MobEffectInstance(MobEffects.REGENERATION, 300, 0))
    )));
    Item PIPEFISH_SUSHI = register("pipefish_sushi", properties -> new Item(properties.craftRemainder(Items.BUCKET).component(
        DataComponents.CONSUMABLE,
        effect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 1))
    )));
    Item ROASTED_CRAB_MEAT = food("roasted_crab_meat", 5, 0.6F, false);
    Item SHARKBITE_SALAD = register("sharkbite_salad", properties -> new Item(properties.usingConvertsTo(Items.BOWL).stacksTo(16).food(food(5, 0.8f, false))));
    Item TANGY_SOUP = register("tangy_soup", properties -> new Item(properties.usingConvertsTo(Items.GLASS_BOTTLE).stacksTo(1).craftRemainder(Items.GLASS_BOTTLE).food(
        food(12, 0.8f, false),
        effect(new MobEffectInstance(MobEffects.GLOWING, 600, 1))
    )));
    Item BACON_SANDWICH = register("bacon_sandwich", properties -> new Item(properties.food(
        food(14, 0.7F, false),
        effect(new MobEffectInstance(MobEffects.SLOWNESS, 300, 0))
    )));

    // Special Meals
    Item ROCKFISH_CANDY = register("rockfish_candy", properties -> new Item(properties.usingConvertsTo(Items.STICK).food(
        food(5, 0.8f, false),
        FAST_EATING
    )));
    Item FORBIDDEN_SOUP = register("forbidden_soup", properties -> new Item(properties.usingConvertsTo(Items.BOWL).stacksTo(16).food(food(10, 0.35f, false))));
    Item SWEET_TOOTH_SEABURGER = register("sweet_tooth_seaburger", properties -> new Item(properties.usingConvertsTo(Items.BOWL).food(
        food(3, 0.3f, false),
        effect(new MobEffectInstance(MobEffects.NAUSEA, 200, 0), new MobEffectInstance(MobEffects.RESISTANCE, 600, 1))
    )));
    Item HAWAIIAN_BARBEQUE = register("hawaiian_barbeque", properties -> new Item(properties.usingConvertsTo(Items.BOWL).food(
        food(9, 0.7f, false),
        effect(new MobEffectInstance(MobEffects.SATURATION, 600, 0))
    )));

    Item TROPICAL_FISHSTICKS = register("tropical_fishsticks", properties -> new Item(properties.usingConvertsTo(Items.BOWL).food(
        food(5, 0.5f, false),
        Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 600, 1), 0.7F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 600, 1), 0.7F))
        .build()
    )));
    Item SURF_N_TURF = register("surf_n_turf", properties -> new Item(properties.usingConvertsTo(Items.BOWL).food(food(10, 0.7f, false))));
    Item SEASUGAR_SORBET = register("seasugar_sorbet", properties -> new Item(properties.usingConvertsTo(Items.BOWL).food(
        food(7, 0.3f, false),
        effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1)))
    ));

    // Corals
    Item BARREL_CORAL_FAN = coralFan("barrel", ReefBlocks.BARREL_SET);
    Item DEAD_BARREL_CORAL_FAN = deadCoralFan("barrel", ReefBlocks.BARREL_SET);
    Item CHIMNEY_CORAL_FAN = coralFan("chimney", ReefBlocks.CHIMNEY_SET);
    Item DEAD_CHIMNEY_CORAL_FAN = deadCoralFan("chimney", ReefBlocks.CHIMNEY_SET);
    Item SHELF_CORAL_FAN = coralFan("shelf", ReefBlocks.SHELF_SET);
    Item DEAD_SHELF_CORAL_FAN = deadCoralFan("shelf", ReefBlocks.SHELF_SET);
    Item HAND_CORAL_FAN = coralFan("hand", ReefBlocks.HAND_SET);
    Item DEAD_HAND_CORAL_FAN = deadCoralFan("hand", ReefBlocks.HAND_SET);
    Item TOWER_CORAL_FAN = coralFan("tower", ReefBlocks.TOWER_SET);
    Item DEAD_TOWER_CORAL_FAN = deadCoralFan("tower", ReefBlocks.TOWER_SET);
    Item ROSE_CORAL_FAN = coralFan("rose", ReefBlocks.ROSE_SET);
    Item DEAD_ROSE_CORAL_FAN = deadCoralFan("rose", ReefBlocks.ROSE_SET);
    Item FLOWER_CORAL_FAN = coralFan("flower", ReefBlocks.FLOWER_SET);
    Item DEAD_FLOWER_CORAL_FAN = deadCoralFan("flower", ReefBlocks.FLOWER_SET);
    Item RING_CORAL_FAN = coralFan("ring", ReefBlocks.RING_SET);
    Item DEAD_RING_CORAL_FAN = deadCoralFan("ring", ReefBlocks.RING_SET);
    Item BUSH_CORAL_FAN = coralFan("bush", ReefBlocks.BUSH_SET);
    Item DEAD_BUSH_CORAL_FAN = deadCoralFan("bush", ReefBlocks.BUSH_SET);

    // Misc
    Item MUSIC_DISC_CLAW = register("music_disc_claw", properties -> new Item(properties.stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(ReefJukeboxSongs.CLAW)));
    Item GLOB_OF_JELLY = register("glob_of_jelly", Item::new);

    static Item spawnEgg(String name, EntityType<? extends Mob> type) {
        return register(name + "_spawn_egg", properties -> new SpawnEggItem(type, properties));
    }
    
    static Item bucket(String name, EntityType<? extends Mob> type) {
        return register(name + "_bucket", properties -> new MobBucketItem(type, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, properties));
    }

    static Item coralFan(String name, CoralBlockSet set) {
        return register(name + "_coral_fan", properties -> new StandingAndWallBlockItem(set.fan(), set.wallFan(), Direction.DOWN, properties.useBlockDescriptionPrefix()));
    }

    static Item deadCoralFan(String name, CoralBlockSet set) {
        return register("dead_" + name + "_coral_fan", properties -> new StandingAndWallBlockItem(set.deadFan(), set.deadWallFan(), Direction.DOWN, properties.useBlockDescriptionPrefix()));
    }


    static Item rawFood(String name) {
        return register(name, properties -> new Item(properties.food(food(1, 0.4F, false))));
    }

    static Item rawHeartyFood(String name) {
        return register(name, properties -> new Item(properties.food(food(1, 0.8F, false))));
    }

    static Item food(String name, int nutrition, float saturation, boolean alwaysEat) {
        return register(name, properties -> new Item(properties.food(food(nutrition, saturation, alwaysEat))));
    }

    static FoodProperties food(int nutrition, float saturation, boolean alwaysEat) {
        var builder = new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation);
        if (alwaysEat) builder.alwaysEdible();
        return builder.build();
    }

    static Consumable effect(MobEffectInstance effects) {
        return Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(effects)).build();
    }

    static Consumable effect(MobEffectInstance firstEffect, MobEffectInstance secondEffect) {
        return Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(List.of(firstEffect, secondEffect))).build();
    }

    static Item register(String name, Function<Item.Properties, Item> creator) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, RainbowReef.id(name));
        Item.Properties properties = new Item.Properties().setId(key);
        return Registry.register(BuiltInRegistries.ITEM, key, creator.apply(properties));
    }

    static void init() {
    }
}