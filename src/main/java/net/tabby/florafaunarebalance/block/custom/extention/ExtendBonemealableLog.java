package net.tabby.florafaunarebalance.block.custom.extention;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.block.custom.BuddingLog;

public class ExtendBonemealableLog extends RotatedPillarBlock implements BonemealableBlock {
    public ExtendBonemealableLog(Properties p_55926_) {
        super(p_55926_);
    }

    public boolean isValidBonemealTarget(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos pos, BlockState state) {
        if (state.is(FFRib.BAMBOO_LOG.get()) || state.is(FFRib.BAMBOO_WOOD.get())) {
            serverLevel.setBlock(pos, BuddingLog.createNewBuddingLog(state), 2);
        }
    }
}
