package net.tabby.florafaunarebalance.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.item.custom.ChuteItem;
import net.tabby.florafaunarebalance.item.custom.DartItem;
import net.tabby.florafaunarebalance.item.custom.TippedDartItem;

public class ModItemsINIT {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FloraFaunaRebalance.MOD_ID);

    public static final RegistryObject<Item> DART_CHUTE = ITEMS.register("dart_chute",
            () -> new ChuteItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).durability(43)));
    public static final RegistryObject<Item> UNTIPPED_DART = ITEMS.register("untipped_dart",
            () -> new DartItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT),
                    1.20f, null));
    public static final RegistryObject<Item> POISON_DART = ITEMS.register("poison_dart",
            () -> new TippedDartItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT),
                    0.85f, MobEffects.POISON));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
