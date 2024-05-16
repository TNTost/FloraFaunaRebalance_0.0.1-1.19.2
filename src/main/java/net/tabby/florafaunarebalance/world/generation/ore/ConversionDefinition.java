package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.tabby.florafaunarebalance.block.FFRib;
import oshi.util.tuples.Triplet;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConversionDefinition<P extends Set<Block>, C extends Set<Triplet<Integer, ?, BlockState>>> {

    private final Set<Block> predicate;
    private final Set<Triplet<Integer, ?, BlockState>> convert;


    public ConversionDefinition(P predicate, C convert) {
        this.predicate = predicate;
        this.convert = convert;
    }

    public Set<Block> getPredicate() {
        return predicate;
    }
    public Set<Triplet<Integer, ?, BlockState>> getConvert() {
        return convert;
    }

    public Map<Block, Triplet<Integer, ?, BlockState>> getMap() {
        return IntStream.range(0, this.predicate.size()).boxed().collect(Collectors.toMap(this.predicate.stream().toList()::get, this.convert.stream().toList()::get));
    }
}
