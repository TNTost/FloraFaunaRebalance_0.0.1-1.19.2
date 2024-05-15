package net.tabby.florafaunarebalance.world.generation.ore;

import it.unimi.dsi.fastutil.objects.Object2ShortArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.chunk.*;
import oshi.util.tuples.Pair;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.BaseStream;
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
        Set<Block> set = cd.getPredicate();

        Stream<SectionPos> sec = SectionPos.betweenClosedStream(c.x, chunk.getMinSection(), c.z, c.x, chunk.getMaxSection(), c.z).parallel();
        Stream<BlockPos> replaceableOres = sec.flatMap(SectionPos::blocksInside).filter(pos -> set.contains(level.getBlockState(pos).getBlock())).peek(pos -> System.out.println(level.getBlockState(pos)));

        // .filter() does not work correctly it returns EVERY block...
        //# TODO: optimise out the 2 for loops and include function <checkRelative> in stream..

        for (BlockPos pos : replaceableOres.toList()) {
            Pair<?, BlockState> convert = definition.getOrDefault(level.getBlockState(pos).getBlock(), new Pair<>(Blocks.EMERALD_BLOCK, Blocks.EMERALD_BLOCK.defaultBlockState())); //# remove null..
            if (checkRelative(level, pos, Predicate.isEqual(convert.getA()))) {
                level.setBlock(pos, convert.getB(), 0);
                System.out.println(pos);
            }
        }
    }
    //private void sift(Stream<BlockPos> posStream, FFRcd cd, Map<Block, Pair<?, BlockState>> definition) {
    //    Pair<?, BlockState> convert = definition.getOrDefault()    ;
    //}

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
