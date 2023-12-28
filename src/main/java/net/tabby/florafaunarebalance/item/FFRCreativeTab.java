package net.tabby.florafaunarebalance.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FFRCreativeTab {
    public static final CreativeModeTab FFR_TAB = new CreativeModeTab("florafaunarebalancetab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(FFRii.DART_CHUTE.get());
        }
    };
}
