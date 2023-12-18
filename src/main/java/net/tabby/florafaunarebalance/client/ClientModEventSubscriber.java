package net.tabby.florafaunarebalance.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.client.renderer.entity.DartProjectileRenderer;
import net.tabby.florafaunarebalance.entity.ModEntityType;

@Mod.EventBusSubscriber(modid = FloraFaunaRebalance.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityType.DART.get(), DartProjectileRenderer::new);
    }
}
