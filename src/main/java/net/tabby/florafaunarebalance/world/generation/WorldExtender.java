package net.tabby.florafaunarebalance.world.generation;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;

public class WorldExtender {
    public void bedrockPlus8(WorldGenLevel level, ChunkAccess chunk) {
        LevelHeightAccessor lha = chunk.getHeightAccessorForGeneration();
        ChunkPos chp = chunk.getPos();
        int minX = chp.getMinBlockX();
        int minZ = chp.getMinBlockZ();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        System.out.println(chunk.getMinBuildHeight());
        for (int j = 0; j < 16; j++) {
            for (int k = 0; k < 16; k++) {
                pos.setX(minX + j).setZ(minZ + k);
                for (int l = -63; l > chunk.getMinBuildHeight(); l--) {
                    if (chunk.getBlockState(pos).isAir()) {
                        System.out.println(pos); //# stays at y = 0
                        level.setBlock(pos, Blocks.BEDROCK.defaultBlockState(), 0);
                    }
                }
            }

        }
    }
}
