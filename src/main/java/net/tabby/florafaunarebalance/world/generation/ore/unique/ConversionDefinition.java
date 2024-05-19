package net.tabby.florafaunarebalance.world.generation.ore.unique;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import oshi.util.tuples.Triplet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConversionDefinition<P extends Block, C extends Triplet<Integer, ?, BlockState>> {
    protected static final Map<Block, Triplet<Integer, ?, BlockState>> DEFINITION = new HashMap<>();

    private final Block predicate;
    private final Triplet<Integer, ?, BlockState> convert;


    public ConversionDefinition(P predicate, C convert) {
        this.predicate = predicate;
        this.convert = convert;
        DEFINITION.put(predicate, convert);
    }

    public void register(ConversionDefinition<P, C> cd) {
        DEFINITION.put(cd.getPredicate(), cd.getConvert());
    }

    public Block getPredicate() {
        return predicate;
    }
    public Triplet<Integer, ?, BlockState> getConvert() {
        return convert;
    }

    public Map<Block, Triplet<Integer, ?, BlockState>> getMap() {
        return DEFINITION;
    }
}
