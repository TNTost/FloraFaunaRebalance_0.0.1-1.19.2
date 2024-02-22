package net.tabby.florafaunarebalance.block.core.unique;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;
import net.tabby.florafaunarebalance.block.FFRib;
import org.jetbrains.annotations.NotNull;

public class WaterLilyFlower extends BushBlock {
    protected static final VoxelShape AABB = Block.box(1.0d, -0.5d, 1.0d, 15d, 2d, 15d);
    public WaterLilyFlower(Properties p_58162_) {
        super(p_58162_);
    }
    protected boolean mayPlaceOn(@NotNull BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos) {
        return (getter.getFluidState(pos).getType() == Fluids.WATER || state.getMaterial() == Material.ICE) && getter.getFluidState(pos.above()).getType() == Fluids.EMPTY;
    }
    public VoxelShape getShape(@NotNull BlockState p_60555_, @NotNull BlockGetter p_60556_, @NotNull BlockPos pos, @NotNull CollisionContext collisionContext) {
        return AABB;
    }

    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (random.nextInt(6) == 0) {
            level.setBlockAndUpdate(pos.below(), FFRib.WATER_LILY_ROOTS.get().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true));
        }
    }
    @Override
    public net.minecraftforge.common.PlantType getPlantType(BlockGetter getter, BlockPos pos) {
        return PlantType.WATER;
    }
}
