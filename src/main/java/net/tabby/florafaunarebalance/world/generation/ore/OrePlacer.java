package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.material.Fluid;
import net.tabby.florafaunarebalance.block.FFRib;
import oshi.util.tuples.Triplet;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class OrePlacer {




    public void placeOresIn(ChunkAccess chunk, WorldGenLevel level) {
        Map<Block, Triplet<Integer, ?, BlockState>> definition = new ConversionDefinition<>( //#TODO: replace raw data with call to registry...
                Set.of(Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
                Set.of(new Triplet<>(1, Blocks.LAVA, FFRib.PYRITE_ORE.get().defaultBlockState()), new Triplet<>(1, Blocks.LAVA, FFRib.DEEPSLATE_PYRITE_ORE.get().defaultBlockState())))
                .getMap();
        Set<Block> set = definition.keySet();
        Triplet<Integer, ?, BlockState> defaultTriplet = new Triplet<>(6, Blocks.NETHERITE_BLOCK, Blocks.REDSTONE_LAMP.defaultBlockState());

        Stream<BlockPos> replaceableOres = SectionPos.betweenClosedStream(chunk.getPos().x, chunk.getMinSection(), chunk.getPos().z, chunk.getPos().x, chunk.getMaxSection(), chunk.getPos().z).parallel().flatMap(SectionPos::blocksInside).filter(
                pos -> set.contains(level.getBlockState(pos).getBlock())).filter(
                        pos -> {
                            Triplet<Integer, ?, BlockState> convert = definition.getOrDefault(level.getBlockState(pos).getBlock(), defaultTriplet);
                            return checkRelative(level, pos, convert.getA(), Predicate.isEqual(convert.getB()));});

        replaceableOres.forEach(pos -> level.setBlock(pos, definition.get(level.getBlockState(pos).getBlock()).getC(), 0));
    }
    //method name: sift..

    protected boolean checkRelative(WorldGenLevel level, BlockPos pos, int amount, Predicate<Block> predicate) {
        int count = 0;
        for (Direction d : Direction.values()) {
            BlockPos rlt = pos.relative(d);
            if (predicate.test(level.getBlockState(rlt).getBlock())) {
                count++;
            }
        }
        return count >= amount;
    }
}
