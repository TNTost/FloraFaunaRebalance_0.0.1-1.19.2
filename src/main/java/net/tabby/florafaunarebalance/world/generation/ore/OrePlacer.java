package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.*;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.Registry.FFRgr;
import net.tabby.florafaunarebalance.world.generation.ore.unique.ConversionDefinition;
import net.tabby.florafaunarebalance.world.generation.ore.unique.VeinMask;
import oshi.util.tuples.Triplet;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;



public class OrePlacer {
    private final Set<BlockPos> veinPositions = new HashSet<>();
    public void placeVeins(ChunkAccess chunk, WorldGenLevel level) {
        //# range, chance, clustering
        //# mask generated with noise gets put over entire function so some areas have and some don't;
        ChunkPos cp = chunk.getPos();
        Set<BlockPos> mask = new VeinMask<>(cp).generate();

        Stream<BlockPos> cutout = SectionPos.betweenClosedStream(cp.x, chunk.getMinSection(), cp.z, cp.x, chunk.getMaxSection(), cp.z)
                .parallel().flatMap(SectionPos::blocksInside).filter(mask::contains);
    }

    public void replaceOres(ChunkAccess chunk, WorldGenLevel level) {
        Map<Block, Triplet<Integer, ?, BlockState>> definition = getRegistryMap(); //Map<Block, Triplet<Integer, ?, BlockState>> definition = new ConversionDefinition<>().getMap();
        Set<Block> set = definition.keySet();
        ChunkPos cp = chunk.getPos();

        Stream<BlockPos> replaceableOres = SectionPos.betweenClosedStream(cp.x, chunk.getMinSection(), cp.z, cp.x, chunk.getMaxSection(), cp.z).parallel().flatMap(SectionPos::blocksInside)
                .filter(pos -> set.contains(level.getBlockState(pos).getBlock())).filter(
                        pos -> { Triplet<Integer, ?, BlockState> convert = definition.get(level.getBlockState(pos).getBlock());
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
                if (amount > 6) {
                    this.veinPositions.add(rlt);
                    // have a switch(d) to select extra blocks, test those also for stone and generate a spherical mask,
                    //# send blockpos over to whereever
                }
            }
        }
        return count >= amount;
    }
    private Map<Block, Triplet<Integer,?, BlockState>> getRegistryMap() {
        Map<Block, Triplet<Integer, ?, BlockState>> m = new HashMap<>();
        for (RegistryObject<ConversionDefinition<?, ?>> obj : FFRgr.CONVERSION_DEFINITION.getEntries()) {
            m.put(obj.get().getPredicate(), obj.get().getConvert());
        }
        return m;
    }
}
