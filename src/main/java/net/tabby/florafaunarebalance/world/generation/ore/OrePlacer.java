package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.*;
import net.tabby.florafaunarebalance.block.FFRib;
import oshi.util.tuples.Triplet;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class OrePlacer {




    public void placeOresIn(ChunkAccess chunk, WorldGenLevel level) {
        Map<Block, Triplet<Integer, ?, BlockState>> definition = new ConversionDefinition<>( //#TODO: replace raw data with call to registry...
                Set.of(Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
                Set.of(new Triplet<>(1, FluidTags.LAVA, FFRib.PYRITE_ORE.get().defaultBlockState()), new Triplet<>(1, FluidTags.LAVA, FFRib.DEEPSLATE_PYRITE_ORE.get().defaultBlockState())))
                .getMap();
        Set<Block> set = definition.keySet();

        Triplet<Integer, ?, BlockState> defaultPair = new Triplet<>(6, Blocks.NETHERITE_BLOCK, Blocks.REDSTONE_LAMP.defaultBlockState());

        Stream<SectionPos> sec = SectionPos.betweenClosedStream(chunk.getPos().x, chunk.getMinSection(), chunk.getPos().z, chunk.getPos().x, chunk.getMaxSection(), chunk.getPos().z).parallel();
        Stream<BlockPos> replaceableOres = sec.flatMap(SectionPos::blocksInside).filter(pos -> set.contains(level.getBlockState(pos).getBlock())).filter(
                        pos -> {
                            Triplet<Integer, ?, BlockState> convert = definition.getOrDefault(level.getBlockState(pos).getBlock(), defaultPair);
                            return checkRelative(level, pos, convert.getA(), Predicate.isEqual(convert.getB()));
                        });

        replaceableOres.peek(pos -> System.out.println(level.getBlockState(pos))).forEach(pos -> level.setBlock(pos, definition.get(level.getBlockState(pos).getBlock()).getC(), 0));
    }
    //private void sift(Stream<BlockPos> posStream, FFRcd cd, Map<Block, Pair<?, BlockState>> definition) {
    //    Pair<?, BlockState> convert = definition.getOrDefault()    ;
    //}

    protected boolean checkRelative(WorldGenLevel level, BlockPos pos, int amount, Predicate<? super BlockState> predicate) {
        int count = 0;
        for (Direction d : Direction.values()) {
            BlockPos rlt = pos.relative(d);
            //int x = rlt.getX(); //# TODO: make this method return int and check to amount to lessen 1 parameter..
            //int y = rlt.getY();
            //int z = rlt.getZ();
            //int sign = y == 16 ? 1 : y < 0 ? -1 : 0;
            //y -= sign * 16;

            if (predicate.equals(level.getBlockState(rlt))) {
                count++;
            }
            //if (x >= 0 && x < 16 && z >= 0 && z < 16) {
            //    if (predicate.test(lcs[index + sign].getBlockState(x, y, z).getBlock())) {
            //        return true;
            //    }
            //} else if (false) {
            //}
        }
        return count >= amount;
    }
}
