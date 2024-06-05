package net.tabby.florafaunarebalance;

import com.lowdragmc.lowdraglib.LDLib;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tabby.florafaunarebalance.Registry.pre.PreInitialisation;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.block.entity.FFRbe;
import net.tabby.florafaunarebalance.block.entity.unique.menu.FFRmt;
import net.tabby.florafaunarebalance.block.entity.unique.menu.unique.HollowLogScreen;
import net.tabby.florafaunarebalance.block.fluid.FFRfluity;
import net.tabby.florafaunarebalance.block.fluid.FFRif;
import net.tabby.florafaunarebalance.entity.client.renderer.CloudRenderer;
import net.tabby.florafaunarebalance.entity.client.renderer.DartProjectileRenderer;
import net.tabby.florafaunarebalance.entity.FFRenty;
import net.tabby.florafaunarebalance.entity.client.renderer.DuckRenderer;
import net.tabby.florafaunarebalance.entity.client.renderer.SkeeterRenderer;
import net.tabby.florafaunarebalance.item.FFRii;
import net.tabby.florafaunarebalance.Registry.FFRtt;
import net.tabby.florafaunarebalance.item.unique.enchantment.FFRie;
import net.tabby.florafaunarebalance.util.FFR.FFRItemProperties;
import net.tabby.florafaunarebalance.Registry.FFRgr;
import net.tabby.florafaunarebalance.world.generation.ore.FFRof;
import net.tabby.florafaunarebalance.world.generation.ore.FFRop;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FloraFaunaRebalance.MOD_ID)
public class FloraFaunaRebalance
{ public static final String MOD_ID = "florafaunarebalance";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FloraFaunaRebalance()
    {
        IEventBus ffrEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        PreInitialisation.register(ffrEventBus); //# cyclic dependency avoidance system, -> cdas for short.

        FFRtt.register(ffrEventBus); //# tool-tiers.
        FFRii.register(ffrEventBus); //# items.
        FFRie.ENCHANTMENTS.register(ffrEventBus); //# enchants.
        ToolAction.get("shovel_hollow");

        FFRib.register(ffrEventBus); //# blocks.

        FFRif.register(ffrEventBus); //# fluids.
        FFRfluity.register(ffrEventBus); //# fluid-types.

        FFRgr.register(ffrEventBus); //# ore-definition.
        FFRof.register(ffrEventBus); //# <ores> configured-feature.
        FFRop.register(ffrEventBus); //# <ores> placed-feature.

        FFRbe.register(ffrEventBus); //# block-entities.
        FFRmt.register(ffrEventBus); //# be^-menus.

        FFRenty.register(ffrEventBus); //# entities.

        ffrEventBus.addListener(this::Setup);
        ffrEventBus.addListener(this::clientSetup);



        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FFRConfig.SPEC);
    }

    private void Setup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) { //# subscribes to eventbus and does client-setupEvents...
        FFRItemProperties.addCustomItemProperties();
        EntityRenderers.register(FFRenty.DART.get(), DartProjectileRenderer::new); // renders Darts..
        EntityRenderers.register(FFRenty.DUST_CLOUD.get(), CloudRenderer::new); // renders dust-Clouds..

        Minecraft.getInstance().getBlockColors().register((p_92567_, p_92568_, p_92569_, p_92570_) -> p_92568_ != null && p_92569_ != null ? 2129968 : 7455580, FFRib.NYMPHAEACEAE.get()); // registers new plant colours for blocks x &y &z &...
        EntityRenderers.register(FFRenty.DUCK.get(), DuckRenderer::new); // renders ducks;
        EntityRenderers.register(FFRenty.WATER_SKEETER.get(), SkeeterRenderer::new); // renders funny water-bug;

        MenuScreens.register(FFRmt.HOLLOW_LOG_MENU.get(), HollowLogScreen::new);

        ItemBlockRenderTypes.setRenderLayer(FFRif.ACEQUIA_AQUA_SOURCE.get(), RenderType.translucent()); //# transparent liquid.
        ItemBlockRenderTypes.setRenderLayer(FFRif.ACEQUIA_AQUA_FLOWING.get(), RenderType.translucent()); //# transparent flowing-liquid.
    }
    public static boolean isGregLoaded() {
        return LDLib.isModLoaded("gtceu");
    }
  }