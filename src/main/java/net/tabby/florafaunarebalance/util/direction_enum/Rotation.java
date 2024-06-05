package net.tabby.florafaunarebalance.util.direction_enum;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public interface Rotation {
    BlockPos relativeTo(BlockPos pos);

    Rotation rotate(Direction d);
}
