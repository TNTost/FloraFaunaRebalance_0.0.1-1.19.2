package net.tabby.florafaunarebalance.core.mixins;

import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.phys.AABB;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.world.generation.ore.OrePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {
    @Unique
    private final OrePlacer orePlacer = new OrePlacer();
    @Inject(method = "applyBiomeDecoration", at = @At("TAIL"))
    private void ffr$applyBiomeDecorations(WorldGenLevel level, ChunkAccess chunk, StructureManager struct, CallbackInfo ci) {
        orePlacer.placeOres(level, (ChunkGenerator) ((Object)this), chunk);
        ChunkPos chunkPos = chunk.getPos();
        if (!SharedConstants.debugVoidTerrain(chunkPos)) {
            LevelChunkSection[] lcs = chunk.getSections(); //# getHighestSection() gets top layer of dirt
            for (int index = 0; index < lcs.length; index++) {
                LevelChunkSection lc = lcs[index];
                for (int x = 0; x < 16; x++) {
                    for (int y = 0; y < 16; y++) {
                        for (int z = 0; z < 16; z++) {
                            BlockState iron = lc.getBlockState(x, y, z);
                            if (iron.is(Blocks.IRON_ORE) || iron.is(Blocks.DEEPSLATE_IRON_ORE)) {
                                if (checkRelative(lcs, index, new BlockPos(x, y, z))) {
                                    if (iron.is(Blocks.DEEPSLATE_IRON_ORE)) {
                                        lc.setBlockState(x, y, z, FFRib.DEEPSLATE_PYRITE_ORE.get().defaultBlockState());
                                        System.out.println("<DEEPSLATE IRON ORE> found AND replaced at '" + x + " " + y + " " + z + "'");
                                    } else {
                                        lc.setBlockState(x, y, z, FFRib.PYRITE_ORE.get().defaultBlockState());
                                        System.out.println("<IRON ORE> found AND replaced at '" + x + " " + y + " " + z + "'");
                                    }
                                    //chunk.getBlockStates() get chunkpos and do fancy math to get stream of iron ores, more optimised,
                                } //#TODO; replace system with individual sections for iron/deepslate iron to improve the process
                            }
                        }
                    }
                }
            }
        }
    }
    private boolean checkRelative(LevelChunkSection[] lsc, int index, BlockPos pos) {
        for (Direction d : Direction.values()) {
            BlockPos rlt = pos.relative(d);
            int x = rlt.getX();
            int y = rlt.getY();
            int z = rlt.getZ();
            int sign = y == 16 ? 1 : y < 0 ? -1 : 0;
            y -= sign * 16;
            if (x >= 0 && x < 16 && z >= 0 && z < 16) { //# TODO: get neighbor chunks if direction out of bounds...
                if (lsc[index + sign].getBlockState(x, y, z).getFluidState().is(FluidTags.LAVA)) {
                    return true; //# might cause out of bounds error, if possible check each position of iron for the entire chunk and then calculate coordinates from there
                    //# if IF possible turn chunk into stream and filter out iron blocks and lava blocks then compare at offsets od Direction.values().
                }
            } else if (false) {
            }
        }
        return false;
    }
}

