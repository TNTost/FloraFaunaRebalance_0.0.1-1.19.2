package net.tabby.florafaunarebalance.util.blockstate;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class FFRBlockStateProperties {
    public static final IntegerProperty NUTRIENTS;

    static {
        NUTRIENTS = IntegerProperty.create("fertility", 0, 5);
    }
}
