package net.tabby.florafaunarebalance.block.entity.unique.menu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.entity.unique.menu.unique.HollowLogMenu;

import java.awt.*;

public class FFRmt {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, FloraFaunaRebalance.MOD_ID);

    public static final RegistryObject<MenuType<HollowLogMenu>> HOLLOW_LOG_MENU = registerMenuType(HollowLogMenu::new, "hollow_log_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {

        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
