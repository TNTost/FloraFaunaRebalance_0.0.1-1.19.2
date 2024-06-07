package net.tabby.florafaunarebalance.world.generation;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.tabby.florafaunarebalance.world.generation.structure.FFRsd;

public class WorldExtender {
    public void bedrockPlus8(WorldGenLevel level, ChunkAccess chunk) {
        ChunkPos chp = chunk.getPos();
        int minX = chp.getMinBlockX();
        int minZ = chp.getMinBlockZ();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int jx = 0; jx < 16; jx++) {
            for (int kz = 0; kz < 16; kz++) {
                pos.setX(minX + jx).setZ(minZ + kz);
                for (int ly = -63; ly > chunk.getMinBuildHeight() + FFRsd.VOID_CITY_HEIGHT; ly--) {
                    pos.setY(ly);
                    level.setBlock(pos, Blocks.BEDROCK.defaultBlockState(), 0);
                }
            }

        }
    }
}
