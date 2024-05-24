package net.tabby.florafaunarebalance.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;

import java.util.Objects;
import java.util.function.Predicate;

public class FFRUtil {
    public static ResourceLocation id (String path) {
        return new ResourceLocation(FloraFaunaRebalance.MOD_ID, path);
    }

    public static final DamageSource DEPTH_PRESSURE = (new DamageSource("depthPressure")).bypassArmor();
    public static ItemStack getItem(Player player, Predicate<ItemStack> predicate) {
        for (int idx = 0; idx < player.getInventory().getContainerSize(); ++idx) {
            ItemStack item = player.getInventory().getItem(idx);
            if (predicate.test(item)) {
                return item;
            }
        } //# loops through inv to find item.
        return ItemStack.EMPTY;
    }

    public static String getRgStr(Item item) { //# get string from Item...
        return Nn(ForgeRegistries.ITEMS.getKey(item));
    }
    public static String getRgStr(BlockState state) { //# get string from blockState...
        return Nn(ForgeRegistries.BLOCKS.getKey(state.getBlock()));
    }


    public static String Nn(ResourceLocation loc) { //# objects require non null duplication removal.
        return Objects.requireNonNull(loc).getPath();
    }
}
