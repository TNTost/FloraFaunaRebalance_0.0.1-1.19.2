package net.tabby.florafaunarebalance.world.generation.ore.unique;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.level.chunk.BulkSectionAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@FunctionalInterface
public interface OrePlacer {
    void placeBlock(BulkSectionAccess access, LevelChunkSection section);
}
