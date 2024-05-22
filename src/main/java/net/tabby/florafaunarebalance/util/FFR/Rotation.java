package net.tabby.florafaunarebalance.util.FFR;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public interface Rotation {
    BlockPos relativeTo(BlockPos pos);

    Rotation rotate(Direction d);
}
