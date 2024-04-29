package net.tabby.florafaunarebalance.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.entity.FFRenty;
import net.tabby.florafaunarebalance.item.core.unique.ChuteItem;
import net.tabby.florafaunarebalance.item.core.unique.DartItem;

import java.util.List;

public class FFRii {
    public static final int TPS = 20;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FloraFaunaRebalance.MOD_ID);



    public static final RegistryObject<Item> DUCK_SPAWN_EGG = ITEMS.register("duck_spawn_egg",
            () -> new ForgeSpawnEggItem(FFRenty.DUCK, 0x856B5B, 0x70df92, new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));
    public static final RegistryObject<Item> SKEETER_SPAWN_EGG = ITEMS.register("skeeter_spawn_egg",
            () -> new ForgeSpawnEggItem(FFRenty.WATER_SKEETER, 0x5D3FD3, 0xCF9FFF, new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));
    public static final RegistryObject<Item> NYMPHAEACEAE = ITEMS.register("nymphaeaceae",
            () -> new PlaceOnWaterBlockItem(FFRib.NYMPHAEACEAE.get(), new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));

    public static final RegistryObject<Item> DART_CHUTE = ITEMS.register("dart_chute",
            () -> new ChuteItem(new Item.Properties().tab(FFRCreativeTab.FFR_TAB).durability(43)));


    public static final RegistryObject<Item> DART = ITEMS.register("untipped_dart",
            () -> new DartItem(new Item.Properties().tab(FFRCreativeTab.FFR_TAB),
                    1.65f, null));
    public static final RegistryObject<Item> POISON_DART = ITEMS.register("poison_dart",
            () -> new DartItem(new Item.Properties().tab(FFRCreativeTab.FFR_TAB),
                    0.85f, List.of(new MobEffectInstance(MobEffects.POISON, 9 * TPS, 1), new MobEffectInstance(MobEffects.WEAKNESS, 27 * TPS, 0))));
    public static final RegistryObject<Item> DART_OF_HEALING = ITEMS.register("dart_of_healing",
            () -> new DartItem(new Item.Properties().tab(FFRCreativeTab.FFR_TAB),
                    0.35f, List.of(new MobEffectInstance(MobEffects.HEAL, 1, 0), (new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 5 * TPS, 0)))));




    //# TODO: fire/ash powder to ignite fireworks in chute...
    public static final RegistryObject<Item> FIRE_ASH_POWDER = ITEMS.register("fire_ash_powder",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));

    public static final RegistryObject<Item> TAPERED_FLINT = ITEMS.register("tapered_flint",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB))); //# rock + flint makes it sharp...
    //# lashing is when a sharp object is attached to a stick.



    public static final RegistryObject<Item> BARK_SCRAP = ITEMS.register("bark_scrap",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));

    public static final RegistryObject<Item> TWIG = ITEMS.register("twig",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));
    public static final RegistryObject<Item> POINTED_STICK = ITEMS.register("pointed_stick",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));


    
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
