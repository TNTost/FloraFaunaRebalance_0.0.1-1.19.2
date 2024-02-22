package net.tabby.florafaunarebalance.core.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.tabby.florafaunarebalance.block.FFRib;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterlilyBlock.class)
public class LilyPadMixin implements BonemealableBlock { //# TODO: try remove Override.
    private static final Direction[] DIRECTIONS = Direction.values();
    @Override
    public boolean isValidBonemealTarget(@NotNull BlockGetter p_50897_, BlockPos pos, @NotNull BlockState state, boolean p_50900_) {
        return true;
    }
    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        BlockPos low = pos.below();
        for (int i = 0; i < 4; i++) {
            if (level.getBlockState(low.relative(DIRECTIONS[i + 2])).getFluidState().getType() != Fluids.WATER) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void performBonemeal(@NotNull ServerLevel serverLevel, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        Block waterLily = switch (random.nextInt(2))  {
            default -> FFRib.NYMPHAEACEAE.get();
        };
        serverLevel.setBlockAndUpdate(pos, waterLily.defaultBlockState());
    }
}
