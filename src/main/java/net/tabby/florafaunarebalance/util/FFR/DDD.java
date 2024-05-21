package net.tabby.florafaunarebalance.util.FFR;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public enum DDD implements Rotation {
    UP_FRONT_LEFT(Direction.UP, Direction.NORTH, Direction.WEST),
    UP_FRONT_RIGHT(Direction.UP, Direction.NORTH, Direction.EAST),
    UP_HIND_LEFT(Direction.UP, Direction.SOUTH, Direction.WEST),
    UP_HIND_RIGHT(Direction.UP, Direction.SOUTH, Direction.EAST),
    DOWN_FRONT_LEFT(Direction.DOWN, Direction.NORTH, Direction.WEST),
    DOWN_FRONT_RIGHT(Direction.DOWN, Direction.NORTH, Direction.EAST),
    DOWN_HIND_LEFT(Direction.DOWN, Direction.SOUTH, Direction.WEST),
    DOWN_HIND_RIGHT(Direction.DOWN, Direction.SOUTH, Direction.EAST);

    private final Direction dddy;
    private final Direction dddz;
    private final Direction dddx;


    DDD(Direction dddy, Direction dddz, Direction dddx) {
        this.dddy = dddy;
        this.dddz = dddz;
        this.dddx = dddx;
    }

    public Direction getY() {
        return this.dddy;
    }
    public Direction getX() {
        return this.dddx;
    }
    public Direction getZ() {
        return this.dddz;
    }


    public static BlockPos relative(BlockPos pos, DDD ddd) {
        return pos.relative(ddd.dddy).relative(ddd.dddz).relative(ddd.dddx);
    }
    public static BlockPos relative(BlockPos pos, DDD ddd, int amount) {
        return pos.relative(ddd.dddy, amount).relative(ddd.dddz, amount).relative(ddd.dddx, amount);
    }


    @Override
    public BlockPos relativeTo(BlockPos pos) {
        return relative(pos, this);
    }
}
