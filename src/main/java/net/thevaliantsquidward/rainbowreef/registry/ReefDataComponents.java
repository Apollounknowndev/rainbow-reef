package net.thevaliantsquidward.rainbowreef.registry;

import net.fabricmc.fabric.api.item.v1.ComponentTooltipAppenderRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.component.TooltipProvider;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.*;

import java.util.function.UnaryOperator;

public interface ReefDataComponents {
    DataComponentType<Angelfish.Variant> ANGELFISH_VARIANT = register("angelfish/variant", builder -> builder.persistent(Angelfish.Variant.CODEC));
    DataComponentType<ArrowCrab.Variant> ARROW_CRAB_VARIANT = register("arrow_crab/variant", builder -> builder.persistent(ArrowCrab.Variant.CODEC));
    DataComponentType<Basslet.Variant> BASSLET_VARIANT = register("basslet/variant", builder -> builder.persistent(Basslet.Variant.CODEC));
    DataComponentType<Boxfish.Variant> BOXFISH_VARIANT = register("boxfish/variant", builder -> builder.persistent(Boxfish.Variant.CODEC));
    DataComponentType<Butterfish.Variant> BUTTERFISH_VARIANT = register("butterfish/variant", builder -> builder.persistent(Butterfish.Variant.CODEC));
    DataComponentType<Crab.Variant> CRAB_VARIANT = register("crab/variant", builder -> builder.persistent(Crab.Variant.CODEC));
    DataComponentType<Clownfish.Variant> CLOWNFISH_VARIANT = register("clownfish/variant", builder -> builder.persistent(Clownfish.Variant.CODEC));
    DataComponentType<DwarfAngelfish.Variant> DWARF_ANGELFISH_VARIANT = register("dwarf_angelfish/variant", builder -> builder.persistent(DwarfAngelfish.Variant.CODEC));
    DataComponentType<Goby.Variant> GOBY_VARIANT = register("goby/variant", builder -> builder.persistent(Goby.Variant.CODEC));
    DataComponentType<Hogfish.Variant> HOGFISH_VARIANT = register("hogfish/variant", builder -> builder.persistent(Hogfish.Variant.CODEC));
    DataComponentType<Jellyfish.Variant> JELLYFISH_VARIANT = register("jellyfish/variant", builder -> builder.persistent(Jellyfish.Variant.CODEC));
    DataComponentType<MoorishIdol.Variant> MOORISH_IDOL_VARIANT = register("moorish_idol/variant", builder -> builder.persistent(MoorishIdol.Variant.CODEC));
    DataComponentType<Parrotfish.Variant> PARROTFISH_VARIANT = register("parrotfish/variant", builder -> builder.persistent(Parrotfish.Variant.CODEC));
    DataComponentType<Pipefish.Variant> PIPEFISH_VARIANT = register("pipefish/variant", builder -> builder.persistent(Pipefish.Variant.CODEC));
    DataComponentType<Ray.Variant> RAY_VARIANT = register("ray/variant", builder -> builder.persistent(Ray.Variant.CODEC));
    DataComponentType<Seahorse.Variant> SEAHORSE_VARIANT = register("seahorse/variant", builder -> builder.persistent(Seahorse.Variant.CODEC));
    DataComponentType<SmallShark.Variant> SMALL_SHARK_VARIANT = register("small_shark/variant", builder -> builder.persistent(SmallShark.Variant.CODEC));
    DataComponentType<Tang.Variant> TANG_VARIANT = register("tang/variant", builder -> builder.persistent(Tang.Variant.CODEC));

    private static <T extends TooltipProvider> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> operator) {
        var component = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            RainbowReef.id(name),
            operator.apply(new DataComponentType.Builder<>()).build()
        );
        ComponentTooltipAppenderRegistry.addFirst(component);
        return component;
    }

    static void init() {

    }
}
