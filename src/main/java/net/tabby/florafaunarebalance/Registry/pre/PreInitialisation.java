package net.tabby.florafaunarebalance.Registry.pre;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.item.FFRCreativeTab;
import net.tabby.florafaunarebalance.item.FFRii;

public class PreInitialisation {
    public static DeferredRegister<Item> PRE_ITEMS = FFRii.ITEMS;

    public static RegistryObject<Item> TANZANITE = PRE_ITEMS.register("tanzanite",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_MINERALS)));
    public static RegistryObject<Item> MYTHRIL_INGOT = PRE_ITEMS.register("mythril_ingot",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_MINERALS)));


    public static void register(IEventBus eventBus) {
        PRE_ITEMS.register(eventBus);
    }
}
