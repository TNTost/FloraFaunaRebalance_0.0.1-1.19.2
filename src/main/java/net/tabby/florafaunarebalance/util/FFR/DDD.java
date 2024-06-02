package net.tabby.florafaunarebalance.util.FFR;

import com.mojang.math.Matrix4f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public DDD byDirection(Direction dx, Direction dy, Direction dz) {
        return Arrays.stream(DDD.values()).filter(ddd -> ddd.dddx == dx && ddd.dddy == dy && ddd.dddz == dz).findAny().get();
    } //doing this for further dddd would result in d! complexity in this check.

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

    public static final List<Direction> LR = List.of(Direction.EAST, Direction.WEST);
    public static final List<Direction> FH = List.of(Direction.SOUTH, Direction.NORTH);
    public static final List<Direction> UD = List.of(Direction.DOWN, Direction.UP);

    public List<Direction> getXYZ() {
        return List.of(this.dddx, this.dddy, this.dddz);
    }

    @Override
    public Rotation rotate(Direction d) {
        List<Direction> drc = new ArrayList<>();
        getXYZ().forEach(w -> drc.add(Direction.rotate(new Matrix4f(d.getRotation()), w)));
        Direction rx = drc.stream().filter(LR::contains).findAny().get();
        Direction ry = drc.stream().filter(UD::contains).findAny().get();
        Direction rz = drc.stream().filter(FH::contains).findAny().get();
        return byDirection(rx, ry, rz);
    }
}
