package net.tabby.florafaunarebalance.block.fluid;

import com.mojang.math.Vector3f;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.fluid.core.FluidTemplate;

public class FFRfluity {
    public static final ResourceLocation ACEQUIA_AQUA_STILL = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "block/fluid/acequia_aqua_still");
    public static final ResourceLocation ACEQUIA_AQUA_FLOWING = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "block/fluid/acequia_aqua_flowing");
    public static final ResourceLocation ACEQUIA_AQUA_OVERLAY = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "block/fluid/acequia_aqua_overlay");


    public static final DeferredRegister<FluidType> FLUID_TYPE =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, FloraFaunaRebalance.MOD_ID);


    public static final RegistryObject<FluidType> ACEQUIA_AQUA_TYPE = FLUID_TYPE.register("acequia_aqua",
            () -> new FluidTemplate(ACEQUIA_AQUA_STILL, ACEQUIA_AQUA_FLOWING, ACEQUIA_AQUA_OVERLAY, 0x9544F0DB //# hex alpha-r-g-b.
                    ,new Vector3f(68 / 255f, 240 / 255f, 219 / 255f), FluidType.Properties.create()
                    .lightLevel(3).density(2).viscosity(5).sound(SoundAction.get("drink"), SoundEvents.AMBIENT_UNDERWATER_EXIT)));

    public static void register(IEventBus eventBus) {
        FLUID_TYPE.register(eventBus);
    }
}
