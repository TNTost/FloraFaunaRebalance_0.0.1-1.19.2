package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.tabby.florafaunarebalance.block.FFRib;
import oshi.util.tuples.Pair;

import java.util.List;
import java.util.Set;

public class FFRcd {
    private final Set<Block> predicate = Set.of(
            Blocks.IRON_ORE,
            Blocks.DEEPSLATE_IRON_ORE);

    private final List<Pair<?, BlockState>> convert = List.of(
            new Pair<>(FluidTags.LAVA, FFRib.PYRITE_ORE.get().defaultBlockState()),
            new Pair<>(FluidTags.LAVA, FFRib.DEEPSLATE_PYRITE_ORE.get().defaultBlockState()));


    public FFRcd() {
    }

    public Set<Block> getPredicate() {
        return predicate;
    }
    public List<Pair<?, BlockState>> getConvert() {
        return convert;
    }
}
