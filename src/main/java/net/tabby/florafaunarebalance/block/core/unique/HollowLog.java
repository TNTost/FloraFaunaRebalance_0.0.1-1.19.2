package net.tabby.florafaunarebalance.block.core.unique;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.tabby.florafaunarebalance.block.core.RotatedLogCore;
import net.tabby.florafaunarebalance.block.entity.FFRbe;
import net.tabby.florafaunarebalance.block.entity.unique.HollowLogEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class HollowLog extends RotatedLogCore implements EntityBlock {
    public static int SIZE;
    protected static final DirectionProperty HOLE = BlockStateProperties.FACING;

    public HollowLog(Properties p_49224_, int size) {
        super(p_49224_);
        HollowLog.SIZE = 4;
        registerDefaultState(defaultBlockState().setValue(HOLE, Direction.EAST));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HOLE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction[] dd = Direction.orderedByNearest(Objects.requireNonNull(context.getPlayer()));
        Direction d = dd[0].getOpposite(); //# opposite of nearest direction
        return super.getStateForPlacement(context).setValue(HOLE, dd[context.getClickedFace().equals(d) ? 1 : 0].getOpposite());
        //#TODO: do funny angle math here to find hole possition, build log sprout off of this property, growing a fruit/berry in the direction of the hole, and leaves all around like BuddingLog[deprecated]
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new HollowLogEntity(pos, state);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide) { //#TODO: make container only openable by crouching...
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof HollowLogEntity) {
                NetworkHooks.openScreen((ServerPlayer) player, (HollowLogEntity) be, pos);
            } else throw new IllegalStateException("container provider missing");
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean p_60519_) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof HollowLogEntity) {
                ((HollowLogEntity) be).drops();
            }
        }
        super.onRemove(state, level, pos, newState, p_60519_);
    }

    public boolean triggerEvent(@NotNull BlockState p_49226_, @NotNull Level p_49227_, @NotNull BlockPos p_49228_, int p_49229_, int p_49230_) {
        super.triggerEvent(p_49226_, p_49227_, p_49228_, p_49229_, p_49230_);
        BlockEntity blockentity = p_49227_.getBlockEntity(p_49228_);
        return blockentity != null && blockentity.triggerEvent(p_49229_, p_49230_);
    }
    @Nullable
    public MenuProvider getMenuProvider(@NotNull BlockState p_49234_, Level p_49235_, @NotNull BlockPos p_49236_) {
        BlockEntity blockentity = p_49235_.getBlockEntity(p_49236_);
        return blockentity instanceof MenuProvider ? (MenuProvider)blockentity : null;
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> be) {
        return createTickerHelper(be, FFRbe.HOLLOW_LOG_BE.get(), HollowLogEntity::tick);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> p_152133_, BlockEntityType<E> p_152134_, BlockEntityTicker<? super E> p_152135_) {
        return p_152134_ == p_152133_ ? (BlockEntityTicker<A>)p_152135_ : null;
    }
}
