package net.tabby.florafaunarebalance.block.fluid.core.unique;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.tabby.florafaunarebalance.block.core.blockstate.FFRbsp;
import net.tabby.florafaunarebalance.block.fluid.FFRff;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;



public class FarFlowingFluidBlock extends LiquidBlock {

    public static final IntegerProperty QUANTITY = FFRbsp.QUANTITY;
    public static final int QUANTITY_LIMIT = 24;

    private final FlowingFluid fluid;
    private final List<FluidState> stateCache;



    public FarFlowingFluidBlock(Supplier<? extends FFRff> supplier, BlockBehaviour.Properties properties) {
        super(supplier, properties);
        this.supplier = supplier;
        this.fluid = null;
        this.stateCache = Lists.newArrayList();
        registerDefaultState(defaultBlockState().setValue(QUANTITY, 24)); //# whatever written here is set as default fluidState.
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos, CollisionContext context) {
        return context.isAbove(STABLE_SHAPE, pos, true) && state.getValue(QUANTITY) == 0 && context.canStandOnFluid(getter.getFluidState(pos.above()), state.getFluidState()) ? STABLE_SHAPE : Shapes.empty();
    } //cannot set integerProperty <level>[0, 1, 2, 3, 4, 5, 6, 7, 8] to 24.
    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        int q = state.getValue(QUANTITY);
        if (!fluidStateCacheInitialized) initFluidStateCache();
        return this.stateCache.get(Math.min(q, QUANTITY_LIMIT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(QUANTITY);
    }

    @Override
    public @NotNull ItemStack pickupBlock(@NotNull LevelAccessor level, @NotNull BlockPos pos, BlockState state) {
        if (state.getValue(QUANTITY) == 0) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            return new ItemStack(this.fluid.getBucket());
        } else return ItemStack.EMPTY;
    }
    private final java.util.function.Supplier<? extends ForgeFlowingFluid> supplier;

    public @NotNull FFRff getFluid() {
        return (FFRff) this.supplier.get();
    }

    protected boolean fluidStateCacheInitialized = false;
    protected synchronized void initFluidStateCache() {
        if (!fluidStateCacheInitialized) {
            this.stateCache.add(getFluid().getSource(false)); //set QUANTITY property from 1 to 23 [24?]
            for (int j = 1; j < QUANTITY_LIMIT; j++) {
                this.stateCache.add(getFluid().getFlowing(QUANTITY_LIMIT - j, false));
            }
            this.stateCache.add(getFluid().getFlowing(QUANTITY_LIMIT, true));
            for (FluidState state : stateCache) {
                System.out.println(state);
            }
            System.out.println("###########################################=^^ stateCache ^^=########################################");
            fluidStateCacheInitialized = true;
        }
    }
}
