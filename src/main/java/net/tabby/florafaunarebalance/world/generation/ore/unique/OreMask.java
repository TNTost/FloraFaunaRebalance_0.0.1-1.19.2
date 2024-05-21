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

    public List<BlockPos> getSphere() { //# returns sphere cutout of all non air blocks IF that direction is present
        List<BlockPos> mask = new ArrayList<>();
        for (Direction d : Direction.values()) {
            for (Rotation diagonal : cubeFace) {
                diagonal.relativeTo(position.relative(d));
            } //handle rotations
        }
    }


    private boolean topIsPresent() {
        return relativePresent.get(0);
    }
}
