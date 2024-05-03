package net.tabby.florafaunarebalance.block.core.blockstate;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class FFRbsp {
    public static final IntegerProperty NUTRIENTS;

    static {
        NUTRIENTS = IntegerProperty.create("fertility", 0, 5);
    }
}
