package net.tabby.florafaunarebalance.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.entity.unique.DartProjectileEntity;
import net.tabby.florafaunarebalance.entity.unique.DuckEntity;
import net.tabby.florafaunarebalance.entity.unique.SandDustCloud;

public class FFRenty {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FloraFaunaRebalance.MOD_ID);


    //ARROW = register("arrow", EntityType.Builder.of(Arrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20));
    public static final RegistryObject<EntityType<DartProjectileEntity>> DART = ENTITY_TYPES.register("dart",
            () -> EntityType.Builder.<DartProjectileEntity>of(DartProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20)
                            .build(new ResourceLocation(FloraFaunaRebalance.MOD_ID, "dart")
                                    .toString()));

    public static final RegistryObject<EntityType<SandDustCloud>> DUST_CLOUD = ENTITY_TYPES.register("dust_cloud",
            () -> EntityType.Builder.<SandDustCloud>of(SandDustCloud::new, MobCategory.MISC)
                    .sized(2.9f, 2.4f).updateInterval(10)
                    .build(new ResourceLocation(FloraFaunaRebalance.MOD_ID, "dust_cloud")
                            .toString()));



    public static final RegistryObject<EntityType<DuckEntity>> DUCK = ENTITY_TYPES.register("duck",
            () -> EntityType.Builder.of(DuckEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.65f).build("duck"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
