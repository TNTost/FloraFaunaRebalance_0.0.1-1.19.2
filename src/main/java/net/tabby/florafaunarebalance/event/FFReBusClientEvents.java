package net.tabby.florafaunarebalance.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.entity.client.FFRml;
import net.tabby.florafaunarebalance.entity.client.models.DuckModel;
import net.tabby.florafaunarebalance.entity.client.models.SandDustCloudModel;
import net.tabby.florafaunarebalance.entity.client.models.SkeeterModel;

@Mod.EventBusSubscriber(modid = FloraFaunaRebalance.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FFReBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FFRml.DUST_CLOUD_LAYER, SandDustCloudModel::createBodyLayer);
        event.registerLayerDefinition(FFRml.DUCK_LAYER, DuckModel::createBodyLayer);
        event.registerLayerDefinition(FFRml.SKEETER_LAYER, SkeeterModel::createBodyLayer);
    }

}
