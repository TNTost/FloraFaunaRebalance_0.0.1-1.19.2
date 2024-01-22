package net.tabby.florafaunarebalance;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.client.renderer.entity.DartProjectileRenderer;
import net.tabby.florafaunarebalance.entity.FFREntityTypes;
import net.tabby.florafaunarebalance.item.FFRii;
import net.tabby.florafaunarebalance.util.FFRItemProperties;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FloraFaunaRebalance.MOD_ID)
public class FloraFaunaRebalance
{ public static final String MOD_ID = "florafaunarebalance";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FloraFaunaRebalance()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        FFRii.register(modEventBus);
        FFRib.register(modEventBus);
        FFREntityTypes.register(modEventBus);

        modEventBus.addListener(this::Setup);
        modEventBus.addListener(this::clientSetup);



        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FFRConfig.SPEC);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        FFRItemProperties.addCustomItemProperties();
    }
    private void Setup(final FMLCommonSetupEvent event) {
    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(FFREntityTypes.DART.get(), DartProjectileRenderer::new);
        }
    }
}
