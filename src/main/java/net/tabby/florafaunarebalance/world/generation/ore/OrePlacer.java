package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.*;
import oshi.util.tuples.Pair;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class OrePlacer {


    private static <K, V> Map<K, V> listToMap(Set<K> keys, List<V> vals) {
        return IntStream.range(0, keys.size()).boxed().collect(Collectors.toMap(keys.stream().toList()::get, vals::get));
    }

    public void placeOresIn(ChunkAccess chunk, WorldGenLevel level) {
        FFRcd cd = new FFRcd();
        Map<Block, Pair<?, BlockState>> definition = listToMap(cd.getPredicate(), cd.getConvert());
        ChunkPos c = chunk.getPos();

        Stream<SectionPos> sec = SectionPos.betweenClosedStream(c.x, chunk.getMinSection(), c.z, c.x, chunk.getMaxSection(), c.z).parallel();
        List<Stream<BlockPos>> secStreams = sec.map(e -> e.blocksInside().filter(
                pos -> cd.getPredicate().contains(level.getBlockState(pos).getBlock()))).toList();
        // .filter() does not work correctly it returns EVERY block...
        //# TODO: optimise out the 2 for loops and include function <checkRelative> in stream..

        for (Stream<BlockPos> replaceableOres : secStreams) {
            for (BlockPos pos : replaceableOres.toList()) {
                System.out.println(level.getBlockState(pos));
            }
                //Pair<?, BlockState> convert = definition.get(level.getBlockState(pos).getBlock());
                //if (checkRelative(level, pos, Predicate.isEqual(convert.getA()))) {
                //    level.setBlock(pos, convert.getB(), 0);
                //    System.out.println(pos);
                //}
        }
    }

    protected boolean checkRelative(WorldGenLevel level, BlockPos pos, Predicate<Block> predicate) {
        for (Direction d : Direction.values()) {
            BlockPos rlt = pos.relative(d);
            //int x = rlt.getX();
            //int y = rlt.getY();
            //int z = rlt.getZ();
            //int sign = y == 16 ? 1 : y < 0 ? -1 : 0;
            //y -= sign * 16;

            if (predicate.test(level.getBlockState(rlt).getBlock())) {
                return true;
            }
            //if (x >= 0 && x < 16 && z >= 0 && z < 16) {
            //    if (predicate.test(lcs[index + sign].getBlockState(x, y, z).getBlock())) {
            //        return true;
            //    } //# TODO: get neighbor chunks if direction out of bounds...
            //} else if (false) {
            //}
        }
        return false;
    }
}
