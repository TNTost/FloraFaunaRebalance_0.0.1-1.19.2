package net.tabby.florafaunarebalance.block.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.common.ToolAction;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.block.core.unique.HollowLog;
import net.tabby.florafaunarebalance.item.FFRii;
import net.tabby.florafaunarebalance.item.core.unique.ChiselItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
        } else if (context.getItemInHand().getItem() instanceof ShovelItem) {
            if (state.is(FFRib.VALYRIAN_LOG.get())) {
                BlockState s = FFRib.VALYRIAN_HOLLOW_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                return s.setValue(FACING, HollowLog.getDirectionFrom(context));
            }
            if (state.is(FFRib.ELYSIAN_LOG.get())) {
                BlockState s = FFRib.ELYSIAN_HOLLOW_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                return s.setValue(FACING, HollowLog.getDirectionFrom(context));
            }
        } else if (context.getItemInHand().getItem() instanceof ChiselItem) {
            if (state.is(FFRib.VALYRIAN_HOLLOW_LOG.get())) {
                drop(context, List.of(FFRii.BARK_SCRAP.get(), Items.STICK, FFRii.TWIG.get()));
                return FFRib.VALYRIAN_PLANKS.get().defaultBlockState();
            }
            if (state.is(FFRib.ENHANCED_VALYRIAN_HOLLOW_LOG.get())) {
                drop(context, List.of(FFRii.BARK_SCRAP.get(), Items.STICK, FFRii.TWIG.get(), Items.ENDER_PEARL));
                return FFRib.VIBRANT_VALYRIAN_PLANKS.get().defaultBlockState();
            }
            if (state.is(FFRib.ELYSIAN_HOLLOW_LOG.get())) {
                drop(context, List.of(FFRii.BARK_SCRAP.get(), Items.STICK, FFRii.TWIG.get(), Items.BLAZE_POWDER));
                return FFRib.ELYSIAN_PLANKS.get().defaultBlockState();
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
    protected void drop(UseOnContext context, List<ItemLike> items) {
        double chance = 6;
        RandomSource r = RandomSource.create();
        for (ItemLike i : items) {
            BlockPos pos = context.getClickedPos();
            ItemStack item = chance >= 1 ? new ItemStack(i, r.nextIntBetweenInclusive(Mth.ceil(chance) - 1, Mth.ceil(chance))) : r.nextFloat() < chance ? new ItemStack(i) : ItemStack.EMPTY;
            context.getLevel().addFreshEntity(new ItemEntity(context.getLevel(), pos.getX(), pos.getY(), pos.getZ(), item, r.nextFloat(), r.nextFloat(), r.nextFloat()));
            chance *= 0.39;
        }
    }
}
