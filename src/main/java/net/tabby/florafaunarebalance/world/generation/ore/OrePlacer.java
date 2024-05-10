package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.*;
import oshi.util.tuples.Pair;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class OrePlacer {


    private static <K, V> Map<K, V> listToMap(List<K> keys, List<V> vals) {
        return IntStream.range(0, keys.size()).boxed().collect(Collectors.toMap(keys::get, vals::get));
    }

    public void placeOresIn(ChunkAccess chunk, WorldGenLevel level) {
        FFRcd cd = new FFRcd();
        Map<Block, Pair<?, BlockState>> definition = listToMap(cd.getPredicate(), cd.getConvert());
        LevelChunkSection[] lcs = chunk.getSections();
        for (int index = 0; index < lcs.length; index++) {
            LevelChunkSection lc = lcs[index];

            Stream<BlockPos> replaceableOres = SectionPos.of(chunk.getPos(), index).blocksInside().filter( //# in world coordinates..
                    pos -> cd.getPredicate().stream().anyMatch(Predicate.isEqual(lc.getBlockState(
                            (pos.getX() % 16 + 16) % 16, (pos.getY() % 16 + 16) % 16,  (pos.getZ() % 16 + 16) % 16).getBlock())));
            //# if pos.get(x) = 352, then pos.get(x) >> 4 = 22 and 352 - 22 isn't in bounds...

            for (BlockPos pos : replaceableOres.toList()) {
                BlockPos check = new BlockPos((pos.getX() % 16 + 16) % 16, (pos.getY() % 16 + 16) % 16,  (pos.getZ() % 16 + 16) % 16);
                System.out.println(lc.getBlockState(check.getX(), check.getY(), check.getZ()).getBlock());
                System.out.println(level.getBlockState(pos).getBlock());

                Pair<?, BlockState> convert = definition.get(lc.getBlockState(
                        (pos.getX() % 16 + 16) % 16, (pos.getY() % 16 + 16) % 16,  (pos.getZ() % 16 + 16) % 16).getBlock());
                if (checkRelative(level, pos, lcs, index, Predicate.isEqual(convert.getA()))) {
                    level.setBlock(pos, convert.getB(), 0);
                    System.out.println(pos);
                }
            }
        }
    }

    protected boolean checkRelative(WorldGenLevel level, BlockPos pos, LevelChunkSection[] lcs, int index, Predicate<Block> predicate) {
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
