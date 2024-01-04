package net.tabby.florafaunarebalance.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.ForgeRegistries;
import net.tabby.florafaunarebalance.block.FFRib;

import java.util.Objects;


public class BuddingLog extends LogRotatedPillarBlock {
    public static final int GROWTH_CHANCE = 5;
    public static final Direction[] DIRECTIONS = Direction.values();
    public BuddingLog(Properties p_55926_) {
        super(p_55926_);
    }


    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource) {
        if (randomSource.nextInt(GROWTH_CHANCE) == 0) {
            String str = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(state.getBlock())).getPath();
            int index = 0;
            if (str.contains("log")) {
                Direction.Axis axis = state.getValue(AXIS);
                switch (axis) {
                    case Y -> index = switch (randomSource.nextInt(DIRECTIONS.length - 2)) {
                        case  0 -> 2;
                        case  1 -> 3;
                        case  2 -> 4;
                        case  3 -> 5;
                        default -> 0;
                    };
                    case Z -> index = switch (randomSource.nextInt(DIRECTIONS.length - 2)) {
                        case  0 -> 0;
                        case  1 -> 1;
                        case  2 -> 4;
                        case  3 -> 5;
                        default -> 0;
                    };
                    case X -> index = randomSource.nextInt(DIRECTIONS.length - 2);
                }
            } else {
                index = randomSource.nextInt(DIRECTIONS.length);
            }
            Direction randomSide = DIRECTIONS[index]; //pick a side, choiche
            BlockPos adjBlock = pos.relative(randomSide);
            BlockState potentialLeafState = serverLevel.getBlockState(adjBlock);
            Block resultBlock = null;
            if (canLeavesGrowAtState(potentialLeafState)) {
                resultBlock = FFRib.BAMBOO_LEAVES.get();
            }
            if (resultBlock != null) {
                BlockState finalState = ((resultBlock.defaultBlockState().setValue(LeavesBlock.DISTANCE, 1)).setValue(LeavesBlock.WATERLOGGED, potentialLeafState.getFluidState().getType() == Fluids.WATER));
                serverLevel.setBlockAndUpdate(adjBlock, finalState);
            }
        }
    }
    public static boolean canLeavesGrowAtState (BlockState state) {
        return state.isAir() || state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8;
    }

    public static BlockState createNewBuddingLog(BlockState state) {
        if (state.is(FFRib.BAMBOO_LOG.get())) {
            return FFRib.BUDDING_BAMBOO_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        } else if (state.is(FFRib.BAMBOO_WOOD.get())) {
            return FFRib.BUDDING_BAMBOO_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        } else if (state.is(Blocks.OAK_LOG)) {
            return FFRib.BUDDING_OAK_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        } else {
            String str = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(state.getBlock())).getPath();
            return Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "stripped_" + str))).defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        }
    }
}
