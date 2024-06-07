package net.tabby.florafaunarebalance.block.fluid;

import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.tabby.florafaunarebalance.block.core.blockstate.FFRbsp;
import org.jetbrains.annotations.NotNull;

public abstract class FFRff extends ForgeFlowingFluid {
    public static final IntegerProperty QUANTITY = FFRbsp.QUANTITY;
    protected static final int QUANTITY_LIMIT = 24;
    private static final ThreadLocal<Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey>> OCCLUSION_CACHE = ThreadLocal.withInitial(() -> {
        Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey> object2bytelinkedopenhashmap = new Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey>(200) {
            protected void rehash(int p_76102_) {
            }
        };
        object2bytelinkedopenhashmap.defaultReturnValue((byte)127);
        return object2bytelinkedopenhashmap;
    });

    protected FFRff(Properties properties) {
        super(properties);
    }


    public @NotNull FluidState getFlowing(int q, boolean falling) {
        return this.getFlowing().defaultFluidState().setValue(QUANTITY, Integer.valueOf(q)).setValue(FALLING, Boolean.valueOf(falling));
    }
    public @NotNull FluidState getSource(boolean falling) {
        return this.getSource().defaultFluidState().setValue(FALLING, Boolean.valueOf(falling));
    }


    @Override
    protected @NotNull FluidState getNewLiquid(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state) {
        int i = 0;
        int j = 0;
        for (Direction d : Direction.Plane.HORIZONTAL) {
            BlockPos rlt = pos.relative(d);
            BlockState s = level.getBlockState(rlt);
            FluidState f = s.getFluidState();
            if (f.getType().isSame(this) && this.canPassThroughWall(d, level, pos, state, rlt, s)) {
                if (f.isSource() && net.minecraftforge.event.ForgeEventFactory.canCreateFluidSource(level, rlt, s, f.canConvertToSource(level, rlt))) {
                    j++; //# assert: j is zero when glass-test.
                }
                i = Math.max(i, f.getAmount());
            }
        }
        if (j >= 2) {
            BlockState blw = level.getBlockState(pos.below());
            FluidState f = blw.getFluidState();
            if (blw.getMaterial().isSolid() || this.isSourceBlockOfThisType(f)) {
                return this.getSource(false);
            }
        }
        BlockPos abv = pos.above();
        BlockState s = level.getBlockState(abv);
        FluidState f = s.getFluidState();
        if (!f.isEmpty() && f.getType().isSame(this) && this.canPassThroughWall(Direction.UP, level, pos, state, abv, s)) {
            return this.getFlowing(QUANTITY_LIMIT, true);
        } else {
            int k = i - this.getDropOff(level);
            //System.out.println(k); check for fluid quantity..

            return  k <= 0 ? Fluids.EMPTY.defaultFluidState() : this.getFlowing(k, false);
        }
    }
    private boolean isSourceBlockOfThisType(FluidState p_76097_) {
        return p_76097_.getType().isSame(this) && p_76097_.isSource();
    }

    private boolean canPassThroughWall(Direction p_76062_, BlockGetter p_76063_, BlockPos p_76064_, BlockState p_76065_, BlockPos p_76066_, BlockState p_76067_) {
        Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey> object2bytelinkedopenhashmap;
        if (!p_76065_.getBlock().hasDynamicShape() && !p_76067_.getBlock().hasDynamicShape()) {
            object2bytelinkedopenhashmap = OCCLUSION_CACHE.get();
        } else {
            object2bytelinkedopenhashmap = null;
        }

        Block.BlockStatePairKey block$blockstatepairkey;
        if (object2bytelinkedopenhashmap != null) {
            block$blockstatepairkey = new Block.BlockStatePairKey(p_76065_, p_76067_, p_76062_);
            byte b0 = object2bytelinkedopenhashmap.getAndMoveToFirst(block$blockstatepairkey);
            if (b0 != 127) {
                return b0 != 0;
            }
        } else {
            block$blockstatepairkey = null;
        }

        VoxelShape voxelshape1 = p_76065_.getCollisionShape(p_76063_, p_76064_);
        VoxelShape voxelshape = p_76067_.getCollisionShape(p_76063_, p_76066_);
        boolean flag = !Shapes.mergedFaceOccludes(voxelshape1, voxelshape, p_76062_);
        if (object2bytelinkedopenhashmap != null) {
            if (object2bytelinkedopenhashmap.size() == 200) {
                object2bytelinkedopenhashmap.removeLastByte();
            }

            object2bytelinkedopenhashmap.putAndMoveToFirst(block$blockstatepairkey, (byte)(flag ? 1 : 0));
        }

        return flag;
    }

    public static class Flowing extends FFRff
    {
        public Flowing(Properties properties) {
            super(properties);
            registerDefaultState(defaultFluidState().setValue(QUANTITY, 0)); //# defaultFluidState instead of StateDefinition().any().
        }
        protected void createFluidStateDefinition(StateDefinition.@NotNull Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(QUANTITY);
        }
        public int getAmount(FluidState state) {
            return state.getValue(QUANTITY);
        }
        public boolean isSource(@NotNull FluidState state) {
            return false;
        }
    }

    public static class Source extends FFRff
    {
        public Source(Properties properties)
        {
            super(properties);
        }
        public int getAmount(FluidState state) {
        return QUANTITY_LIMIT;
        }
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
