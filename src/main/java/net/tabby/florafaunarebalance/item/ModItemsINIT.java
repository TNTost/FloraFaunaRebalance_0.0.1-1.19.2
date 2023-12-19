package net.tabby.florafaunarebalance.item;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.item.custom.DartItem;

public class ModItemsINIT {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FloraFaunaRebalance.MOD_ID);

    public static final RegistryObject<Item> DARTCHUTE = ITEMS.register("dart_chute",
            () -> new BowItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).durability(43)));
    public static final RegistryObject<Item> DART = ITEMS.register("dart",
            () -> new DartItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT),
                    1.25f));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
