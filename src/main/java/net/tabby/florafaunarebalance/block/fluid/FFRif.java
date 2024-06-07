package net.tabby.florafaunarebalance.block.fluid;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.item.FFRii;

public class FFRif {
    public static final DeferredRegister<Fluid> FLUID =
            DeferredRegister.create(ForgeRegistries.FLUIDS, FloraFaunaRebalance.MOD_ID);

    public static final RegistryObject<FFRff> ACEQUIA_AQUA_SOURCE = FLUID.register("acequia_aqua_source",
            () -> new FFRff.Source(FFRif.ACEQUIA_AQUA_PROPERTIES));
    public static final RegistryObject<FFRff> ACEQUIA_AQUA_FLOWING= FLUID.register("acequia_aqua_flowing",
            () -> new FFRff.Flowing(FFRif.ACEQUIA_AQUA_PROPERTIES));

    public static final ForgeFlowingFluid.Properties ACEQUIA_AQUA_PROPERTIES = new ForgeFlowingFluid.Properties(
            FFRfluity.ACEQUIA_AQUA_TYPE, ACEQUIA_AQUA_SOURCE, ACEQUIA_AQUA_FLOWING).slopeFindDistance(11).levelDecreasePerBlock(1)
            .block(FFRib.ACEQUIA_AQUA).bucket(FFRii.ACEQUIA_AQUA_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUID.register(eventBus);
    }
}
