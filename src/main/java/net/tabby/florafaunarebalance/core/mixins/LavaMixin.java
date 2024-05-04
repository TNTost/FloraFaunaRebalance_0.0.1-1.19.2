package net.tabby.florafaunarebalance.core.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.LavaFluid;
import net.tabby.florafaunarebalance.block.FFRib;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LavaFluid.class)
public abstract class LavaMixin extends FlowingFluid {
    @Shadow
    private void fizz(LevelAccessor level, BlockPos pos) {}

    @Inject(method = "spreadTo", at = @At("HEAD"))
    protected void spreadTo(@NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Direction direction, @NotNull FluidState fluidState, CallbackInfo ci) {
        if (direction == Direction.DOWN) { //# this is reached
            if (state.is(Blocks.STONE)) {
                System.out.println("stone found");

                level.setBlock(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(level, pos, pos, FFRib.IGNEOUS_ROCK.get().defaultBlockState()), 1);
                this.fizz(level, pos);
            }
        }
    }
}
