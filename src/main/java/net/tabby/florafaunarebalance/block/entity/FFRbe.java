package net.tabby.florafaunarebalance.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.block.entity.unique.HollowLogEntity;

public class FFRbe {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FloraFaunaRebalance.MOD_ID);

    public static final RegistryObject<BlockEntityType<HollowLogEntity>> HOLLOW_LOG_BE = BLOCK_ENTITY.register("hollow_low_be",
            () -> BlockEntityType.Builder.of(HollowLogEntity::new, FFRib.VALYRIAN_HOLLOW_LOG.get(), FFRib.ENHANCED_VALYRIAN_HOLLOW_LOG.get(), FFRib.ELYSIAN_HOLLOW_LOG.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY.register(eventBus);
    }
}
