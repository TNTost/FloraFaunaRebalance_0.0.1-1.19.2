package net.tabby.florafaunarebalance.world.generation.ore.unique;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.tabby.florafaunarebalance.util.FFR.DD;
import net.tabby.florafaunarebalance.util.FFR.DDD;
import net.tabby.florafaunarebalance.util.FFR.Rotation;

import java.util.*;

public class OreMask<O extends BlockPos, T extends Boolean, F extends Boolean, R extends Boolean, Y extends Boolean, H extends Boolean, L extends Boolean> {
    protected final BlockPos position;
    protected final List<Boolean> relativePresent = new ArrayList<>();

    protected final List<Rotation> cubeFace = List.of(DD.UP_FRONT, DDD.UP_FRONT_RIGHT, DD.UP_RIGHT, DDD.UP_HIND_RIGHT, DD.UP_HIND, DDD.UP_HIND_LEFT, DD.UP_LEFT, DDD.UP_FRONT_LEFT);
    //make direction.values esque collection of 3x3 block mask [split into sections that overlap]
    //find out how to rotate the directions in said collection

    public OreMask(BlockPos pos, boolean topPresent, boolean frontPresent, boolean rightPresent, boolean ynderPresent, boolean hindPresent, boolean leftPresent) {
        this.position = pos;
        relativePresent.addAll(List.of(topPresent, frontPresent, rightPresent, ynderPresent, hindPresent, leftPresent));
    }

    public List<BlockPos> getSphere() { //# returns sphere cutout of all non-air blocks IF that direction is present
        List<BlockPos> mask = new ArrayList<>();
        for (Direction d : Direction.values()) {
            if (present(d)) {
                for (Rotation diagonal : cubeFace) { //handle rotation magic.
                    mask.add(diagonal.rotate(d).relativeTo(position.relative(d)));
                }
            }
        }
        return mask;
    }

    private boolean present(Direction d) {
        return switch (d) {
            case UP -> topExists();
            case NORTH -> frontExists();
            case EAST -> rightExists();
            case DOWN -> underExists();
            case SOUTH -> hindExists();
            case WEST -> leftExists();

        };
    }


    private boolean topExists() {
        return relativePresent.get(0);
    }
    private boolean frontExists() {
        return relativePresent.get(1);
    }
    private boolean rightExists() {
        return relativePresent.get(2);
    }
    private boolean underExists() {
        return relativePresent.get(3);
    }
    private boolean hindExists() {
        return relativePresent.get(4);
    }
    private boolean leftExists() {
        return relativePresent.get(5);
    }
}
