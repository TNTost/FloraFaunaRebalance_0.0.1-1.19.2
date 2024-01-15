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
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.ForgeRegistries;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.block.core.LogRotatedPillarBlock;
import net.tabby.florafaunarebalance.util.blockstate.FFRBlockStateProperties;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class BuddingLog extends LogRotatedPillarBlock {
    public static final int GROWTH_CHANCE = 3;
    public static final Direction[] DIRECTIONS = Direction.values();
    public static final IntegerProperty NUTRIENTS;

    public BuddingLog(Properties p_55926_) {
        super(p_55926_);
        registerDefaultState(defaultBlockState().setValue(NUTRIENTS, 0));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(NUTRIENTS);
    }

    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, RandomSource randomSource) {
        if (randomSource.nextInt(GROWTH_CHANCE) == 0) {
            Block leafType = null;
            String str;
            int index = randomSource.nextInt(DIRECTIONS.length);
            if ((str = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(state.getBlock())).getPath()).contains("log")) {
                int preReMap = randomSource.nextInt(DIRECTIONS.length - 2);
                switch (state.getValue(AXIS)) {
                    case Y -> index = preReMap + 2;
                    case Z -> index = switch (preReMap) {
                        case  1 -> 1;
                        case  2 -> 4;
                        case  3 -> 5;
                        default -> 0;
                    };
                    case X -> index = preReMap;
                }
            }
            BlockPos randomAdjBlock = pos.relative(DIRECTIONS[index]);
            BlockState potentialLeafState = level.getBlockState(randomAdjBlock);
            if (canLeavesGrowAtState(potentialLeafState)) {
                if (str.contains("bamboo")) { leafType = FFRib.BAMBOO_LEAVES.get();
                } else if (str.contains("oak")) {
                    if (str.contains("dark")) { leafType = Blocks.DARK_OAK_LEAVES;
                    } else { leafType = Blocks.OAK_LEAVES;
                    }
                } else if (str.contains("birch")) { leafType = Blocks.BIRCH_LEAVES;
                } else if (str.contains("spruce")) { leafType = Blocks.SPRUCE_LEAVES;
                } else if (str.contains("jungle")) { leafType = Blocks.JUNGLE_LEAVES;
                } else if (str.contains("acacia")) { leafType = Blocks.ACACIA_LEAVES;
                } else if (str.contains("mangrove")) { leafType = Blocks.MANGROVE_LEAVES;
                }
            }
            if (leafType != null) {
                BlockState finalState = ((leafType.defaultBlockState().setValue(LeavesBlock.DISTANCE, 1)).setValue(LeavesBlock.WATERLOGGED, potentialLeafState.getFluidState().getType() == Fluids.WATER));
                level.setBlockAndUpdate(randomAdjBlock, finalState);
            }
        }
    }
    public static boolean canLeavesGrowAtState (BlockState state) {
        return (state.isAir() || state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8); //#  && state.getValue(NUTRIENTS) > 0
    }

    public static BlockState createNewBuddingLog(BlockState state) {
        String str = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(state.getBlock())).getPath();
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(FloraFaunaRebalance.MOD_ID, "budding_" + str))).defaultBlockState().setValue(AXIS, state.getValue(AXIS));
    }
    static {
        NUTRIENTS = FFRBlockStateProperties.NUTRIENTS;
    }
}
