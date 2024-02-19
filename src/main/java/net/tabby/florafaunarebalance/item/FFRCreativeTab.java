package net.tabby.florafaunarebalance.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.item.core.unique.ChuteItem;
import org.jetbrains.annotations.NotNull;

public class FFRCreativeTab {

    public static final CreativeModeTab FFR_VANILLA = new CreativeModeTab("florafaunavanillatab") {
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(FFRib.BUDDING_OAK_LOG.get());
        }
    };
    public static final CreativeModeTab FFR_TAB = new CreativeModeTab("florafaunarebalancetab") {
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(FFRii.DART_CHUTE.get());
        }
    }.setEnchantmentCategories(new EnchantmentCategory[]{EnchantmentCategory.create("barrage", item -> item instanceof ChuteItem)});


}
