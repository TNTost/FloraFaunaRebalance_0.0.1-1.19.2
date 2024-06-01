package net.tabby.florafaunarebalance.block.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.common.ToolAction;
import net.tabby.florafaunarebalance.block.FFRib;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Nullable;

public class RotatedLogCore extends RotatedPillarBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public RotatedLogCore(Properties p_55926_) {
        super(p_55926_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(FFRib.BAMBOO_LOG.get())) {
                return FFRib.STRIPPED_BAMBOO_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            } else if (state.is(FFRib.BAMBOO_WOOD.get())) {
                return FFRib.STRIPPED_BAMBOO_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if (state.is(FFRib.VALYRIAN_LOG.get())) {
            }
            if (state.is(FFRib.ELYSIAN_LOG.get())) {
            }
        } else if (context.getItemInHand().getItem() instanceof ShovelItem) {
            if (state.is(FFRib.VALYRIAN_LOG.get())) {
                return FFRib.VALYRIAN_HOLLOW_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(FFRib.ELYSIAN_LOG.get())) {
                return FFRib.ELYSIAN_HOLLOW_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
