package net.tabby.florafaunarebalance.item.core.custom;


import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;


import java.util.function.Predicate;

import static net.tabby.florafaunarebalance.util.FFRTags.Items.*;

public class ChuteItem extends BowItem {
    public static final Predicate<ItemStack> DART_ONLY = itemStack -> itemStack.is(DART_TAG);
    public ChuteItem(Properties p_40660_) {
        super(p_40660_);
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return DART_ONLY;
    }
    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack p_40678_) {
        return UseAnim.SPYGLASS;
    }
}
