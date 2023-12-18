package net.tabby.florafaunarebalance.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.entity.custom.DartProjectileEntity;

public class ModEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FloraFaunaRebalance.MOD_ID);


    //ARROW = register("arrow", EntityType.Builder.of(Arrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20));
    public static final RegistryObject<EntityType<DartProjectileEntity>> DART =
            ENTITY_TYPES.register("dart", () ->
                    EntityType.Builder.<DartProjectileEntity>of(DartProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20)
                            .build(new ResourceLocation(FloraFaunaRebalance.MOD_ID, "dart").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
