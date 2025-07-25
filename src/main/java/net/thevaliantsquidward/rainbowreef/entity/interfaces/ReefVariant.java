package net.thevaliantsquidward.rainbowreef.entity.interfaces;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public interface ReefVariant extends StringRepresentable, TooltipProvider {
    int index();

    String entity();

    @Override
    default void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flags, DataComponentGetter getter) {
        consumer.accept(Component.translatable("entity.rainbowreef." + this.entity() + ".variant_" + this.getSerializedName()).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }
}
