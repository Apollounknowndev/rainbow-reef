package net.thevaliantsquidward.rainbowreef.registry;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.thevaliantsquidward.rainbowreef.entity.Tang;

public interface ReefTrades {
    static void init() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 4, listings -> {
            var bucket = new ItemStack(ReefItems.TANG_BUCKET);
            bucket.set(ReefDataComponents.TANG_VARIANT, Tang.Variant.GEM);
            listings.add(new VillagerTrades.ItemsForEmeralds(bucket, 64, 1, 1, 30));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 2, listings -> {
            listings.add(new VillagerTrades.EmeraldForItems(ReefBlocks.POLISHED_CORALSTONE, 4, 16, 10));
        });
    }
}
