package net.tabby.florafaunarebalance.util.FFR;

import com.mojang.math.Matrix4f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.Arrays;
import java.util.List;

public enum DD implements Rotation {
    NONE(Direction.UP, Direction.DOWN),
    UP_LEFT(Direction.UP, Direction.WEST),
    UP_RIGHT(Direction.UP, Direction.EAST),
    UP_FRONT(Direction.UP, Direction.NORTH),
    UP_HIND(Direction.UP, Direction.SOUTH),
    FRONT_LEFT(Direction.NORTH, Direction.WEST),
    FRONT_RIGHT(Direction.NORTH, Direction.EAST),
    HIND_LEFT(Direction.SOUTH, Direction.WEST),
    HIND_RIGHT(Direction.SOUTH, Direction.EAST),
    DOWN_LEFT(Direction.DOWN, Direction.WEST),
    DOWN_RIGHT(Direction.DOWN, Direction.EAST),
    DOWN_FRONT(Direction.DOWN, Direction.NORTH),
    DOWN_HIND(Direction.DOWN, Direction.SOUTH);

    private final Direction ddx;
    private final Direction ddy;
    private static final List<DD> MIDDLE = List.of(FRONT_LEFT, FRONT_RIGHT, HIND_LEFT, HIND_RIGHT, NONE);
    private static final List<DD> CENTER = List.of(UP_FRONT, UP_HIND, DOWN_FRONT, DOWN_HIND, NONE);
    private static final List<DD> INTER = List.of(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT, NONE);


    DD(Direction ddy, Direction ddx) {
        this.ddy = ddy;
        this.ddx = ddx;
    }

    public DD byDirection(Direction dx, Direction dy) {
        return Arrays.stream(DD.values()).filter(dd -> dd.ddx == dx && dd.ddy == dy || dd.ddx == dy && dd.ddy == dx).findAny().get();
    }


    public Enum<? extends Enum<?>> getX() {
        return !CENTER.contains(this) ? this.ddx : NONE;
    }
    public Enum<? extends Enum<?>> getY() {
        return !MIDDLE.contains(this) ? this.ddy : NONE;
    }
    public Enum<? extends Enum<?>> getZ() {
        Direction xOrY = CENTER.contains(this) ? ddx : ddy;
        return !INTER.contains(this) ? xOrY : NONE;
    }

    public static BlockPos relative(BlockPos pos, DD dd) {
        return pos.relative(dd.ddy).relative(dd.ddx);
    }
    public static BlockPos relative(BlockPos pos, DD dd, int amount) {
        return pos.relative(dd.ddy, amount).relative(dd.ddx, amount);
    }


    @Override
    public BlockPos relativeTo(BlockPos pos) {
        return relative(pos, this);
    }
    @Override
    public Rotation rotate(Direction d) {
        Direction rx = Direction.rotate(new Matrix4f(d.getRotation()), this.ddx);
        Direction ry = Direction.rotate(new Matrix4f(d.getRotation()), this.ddy);
        return byDirection(rx, ry);
    }
}
