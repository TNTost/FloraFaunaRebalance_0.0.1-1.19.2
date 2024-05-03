package net.tabby.florafaunarebalance.core.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
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
public abstract class LavaMixin extends FlowingFluid { //# must inherit everything from child class.
    @Shadow
    private void fizz(LevelAccessor accessor, BlockPos pos) {}

    @Inject(method = "randomTick", at = @At("HEAD"))
    public void randomTick(@NotNull Level level, @NotNull BlockPos pos, @NotNull FluidState state, RandomSource source, CallbackInfo ci) {
        if (level.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
            for (int i = 0; i < source.nextInt(4); i++) {
                BlockPos d = pos.relative(Direction.getRandom(source));
                if (level.getBlockState(d).is(Blocks.STONE)) {
                    level.setBlockAndUpdate(d, FFRib.IGNEOUS_ROCK.get().defaultBlockState());
                    this.fizz(level, d);
                }
            }
        }
    }
}
