package net.tabby.florafaunarebalance.world.generation.ore.unique;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class VeinMask <R extends BlockPos> {
    private final ChunkPos chunkPos;

    public VeinMask(ChunkPos cp) {
        this.chunkPos = cp;
    }

    public Set<R> generate() {
        return new HashSet<>();
    }
}
