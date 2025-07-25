package net.thevaliantsquidward.rainbowreef.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

public final class RRTags {
    public static final TagKey<Block> CONSIDERED_NEMS = block("considered_nems");
    public static final TagKey<Block> TROPICAL_NEMS = block("tropical_nems");
    public static final TagKey<Block> TEMPERATE_NEMS = block("temperate_nems");
    public static final TagKey<Block> TROPICAL_STARS = block("tropical_stars");
    public static final TagKey<Block> TEMPERATE_STARS = block("temperate_stars");
    public static final TagKey<Block> HOG_DIGGABLE = block("hogfish_diggable");
    public static final TagKey<Block> BUTTERFLY_DIET = block("butterfly_diet");
    public static final TagKey<Block> PARROTFISH_DIET = block("parrotfish_diet");
    public static final TagKey<Block> ANGELFISH_DIET = block("angelfish_diet");
    public static final TagKey<Block> CLOWNFISH_DIET = block("clownfish_diet");
    public static final TagKey<Block> TANG_DIET = block("tang_diet");
    public static final TagKey<Block> MOORISH_DIET = block("moorish_diet");
    public static final TagKey<Item> NEM_DIET = item("nem_diet");
    public static final TagKey<Biome> EEL_FRESH = biome("eel_fresh");
    public static final TagKey<Biome> EEL_SALT = biome("eel_salt");

    private static TagKey<Block> block(String name) {
        return TagKey.create(Registries.BLOCK, RainbowReef.id(name));
    }

    private static TagKey<Biome> biome(String name) {
        return TagKey.create(Registries.BIOME, RainbowReef.id(name));
    }

    private static TagKey<EntityType<?>> entity(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, RainbowReef.id(name));
    }

    private static TagKey<Item> item(String name) {
        return TagKey.create(Registries.ITEM, RainbowReef.id(name));
    }
}