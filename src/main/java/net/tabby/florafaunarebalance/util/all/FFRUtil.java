package net.tabby.florafaunarebalance.util.all;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.Objects;

public class FFRUtil {
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
