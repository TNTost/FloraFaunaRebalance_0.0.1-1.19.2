package net.tabby.florafaunarebalance.block.custom.blockstate;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class FFRProperties {
    public static final IntegerProperty NUTRIENTS;

    static {
        NUTRIENTS = IntegerProperty.create("fertility", 0, 5);
    }
}
