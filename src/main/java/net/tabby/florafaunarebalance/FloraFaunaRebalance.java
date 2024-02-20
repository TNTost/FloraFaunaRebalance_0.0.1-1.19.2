package net.tabby.florafaunarebalance;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.entity.client.renderer.CloudRenderer;
import net.tabby.florafaunarebalance.entity.client.renderer.DartProjectileRenderer;
import net.tabby.florafaunarebalance.entity.FFRenty;
import net.tabby.florafaunarebalance.item.FFRii;
import net.tabby.florafaunarebalance.item.core.unique.enchantment.FFRie;
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


        FFRii.register(modEventBus); //# items.
        FFRib.register(modEventBus); //# blocks.
        FFRenty.register(modEventBus); //# entities.

        FFRie.ENCHANTMENTS.register(modEventBus); //# enchants.

        modEventBus.addListener(this::Setup);
        modEventBus.addListener(this::clientSetup);



        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FFRConfig.SPEC);
    }

    private void Setup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) { //# subscribes to eventbus and does client-setupEvents...
        FFRItemProperties.addCustomItemProperties();
        EntityRenderers.register(FFRenty.DART.get(), DartProjectileRenderer::new); // renders Darts..
        EntityRenderers.register(FFRenty.DUST_CLOUD.get(), CloudRenderer::new); // renders dust-Clouds..
    }
  }
