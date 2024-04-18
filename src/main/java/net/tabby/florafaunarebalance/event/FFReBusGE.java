package net.tabby.florafaunarebalance.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.entity.FFRenty;
import net.tabby.florafaunarebalance.entity.unique.DuckEntity;
import net.tabby.florafaunarebalance.entity.unique.SkeeterEntity;

@Mod.EventBusSubscriber(modid = FloraFaunaRebalance.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FFReBusGE {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(FFRenty.DUCK.get(), DuckEntity.createAttributes().build());
        event.put(FFRenty.WATER_SKEETER.get(), SkeeterEntity.createAttributes().build());
    }

}
