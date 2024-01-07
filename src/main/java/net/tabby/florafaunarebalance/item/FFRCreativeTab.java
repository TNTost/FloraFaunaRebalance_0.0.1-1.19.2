package net.tabby.florafaunarebalance.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.tabby.florafaunarebalance.block.FFRib;
import org.jetbrains.annotations.NotNull;

public class FFRCreativeTab {
    public static final CreativeModeTab FFR_VANILLA = new CreativeModeTab("florafaunavanillatab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(FFRib.BUDDING_OAK_LOG.get());
        }
    };
    public static final CreativeModeTab FFR_TAB = new CreativeModeTab("florafaunarebalancetab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(FFRii.DART_CHUTE.get());
        }
    };
}
