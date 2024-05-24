package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.*;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.Registry.FFRgr;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.world.generation.ore.unique.ConversionDefinition;
import net.tabby.florafaunarebalance.world.generation.ore.unique.OreMask;
import oshi.util.tuples.Triplet;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;



public class OrePlacer {
    private final Set<OreMask<BlockPos, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean>> veinPositions = new HashSet<>();

    //# range, chance, clustering
    //# mask generated with noise gets put over entire function so some areas have and some don't;
    public void placeVeins(ChunkAccess chunk, WorldGenLevel level) {
        ChunkPos cp = chunk.getPos();
        Set<BlockPos> mask = new HashSet<>();
        for (OreMask<?, ?, ?, ?, ?, ?, ?> oreMask : veinPositions) {
            mask.addAll(oreMask.getSphere());
        }
        Stream<BlockPos> cutout = SectionPos.betweenClosedStream(cp.x - 1, chunk.getMinSection(), cp.z - 1, cp.x + 1, chunk.getMaxSection(), cp.z + 1)
                .parallel().flatMap(SectionPos::blocksInside).filter(mask::contains).filter(pos -> !level.getBlockState(pos).isAir());

        cutout.forEach(pos -> level.setBlock(pos, Blocks.WAXED_COPPER_BLOCK.defaultBlockState(), 0));
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
        List<Boolean> rltPresent = new ArrayList<>();
        for (Direction d : Direction.values()) {
            BlockPos rlt = pos.relative(d);
            boolean result = predicate.test(level.getBlockState(rlt).getBlock());
            rltPresent.add(amount > 6 && result);
            count += result ? 1 : 0;
        }
        if (amount > 6) {
            this.veinPositions.add(new OreMask<>(pos, rltPresent.get(0), rltPresent.get(1), rltPresent.get(2), rltPresent.get(3), rltPresent.get(4), rltPresent.get(5)));
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
