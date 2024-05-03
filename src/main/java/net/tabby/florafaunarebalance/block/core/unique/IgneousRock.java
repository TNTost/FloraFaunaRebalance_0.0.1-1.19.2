package net.tabby.florafaunarebalance.block.core.unique;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.tabby.florafaunarebalance.block.FFRib;
import org.jetbrains.annotations.NotNull;

public class IgneousRock extends Block {
    public IgneousRock(Properties p) {
        super(p);
    }
    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource source) {
        if (!level.isClientSide) {
            for (Direction d : Direction.values()) {
                BlockPos sPos = pos.relative(d);
                if (this.checkNextToLava(level, sPos)) {
                    level.setBlockAndUpdate(sPos, FFRib.IGNEOUS_ROCK.get().defaultBlockState());
                    this.fizz(level, sPos);
                }
            }
        }
    }
    protected boolean checkNextToLava(ServerLevel level, BlockPos pos) {
        if (level.getBlockState(pos).is(Blocks.STONE)) {
            for (Direction dd : Direction.values()) { // inefficient, doubles occur.
                FluidState lava = level.getFluidState(pos.relative(dd));
                if (lava.is(FluidTags.LAVA)) {
                    return true;
                }
            }
        }
        return false;
    }
    protected void fizz(Level level, BlockPos pos) {
        level.levelEvent(1501, pos, 0);
    }
}
