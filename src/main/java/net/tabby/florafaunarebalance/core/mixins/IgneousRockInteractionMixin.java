package net.tabby.florafaunarebalance.core.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.FluidType;
import net.tabby.florafaunarebalance.block.FFRib;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oshi.util.tuples.Pair;

import java.util.*;

@Mixin(FluidInteractionRegistry.class)
public abstract class IgneousRockInteractionMixin { //# must inherit everything from child class.
    private static final Map<Block, List<Pair<FluidType, BlockState>>> SOLID_INTERACTIONS = new HashMap<>();
    @Inject(method = "canInteract", at = @At("HEAD"), cancellable = true, remap = false)
    private static void FFRSolidInteraction(Level level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        for (Direction d : LiquidBlock.POSSIBLE_FLOW_DIRECTIONS) {
            BlockPos relative = pos.relative(d);
            for (Pair<FluidType, BlockState> p : SOLID_INTERACTIONS.getOrDefault(level.getBlockState(relative).getBlock(), Collections.emptyList())) {
                if (level.getBlockState(pos).getFluidState().getFluidType().equals(p.getA())) {
                    level.setBlockAndUpdate(relative, p.getB());
                    level.levelEvent(1501, relative, 0);
                    cir.setReturnValue(true);
                }
            }
        }
    }

    private static synchronized void addInteraction(Block convert, Pair<FluidType, BlockState> interaction) {
        SOLID_INTERACTIONS.computeIfAbsent(convert, s -> new ArrayList<>()).add(interaction);
    } //# every method and field must be private..

    static {
        addInteraction(Blocks.STONE, new Pair<>(
                ForgeMod.LAVA_TYPE.get(), FFRib.RHYOLITIC_PUMICE.get().defaultBlockState()
        ));

        addInteraction(Blocks.IRON_ORE, new Pair<>(
                ForgeMod.LAVA_TYPE.get(), FFRib.PYRITE_ORE.get().defaultBlockState()
        ));
        addInteraction(Blocks.DEEPSLATE_IRON_ORE, new Pair<>(
                ForgeMod.LAVA_TYPE.get(), FFRib.DEEPSLATE_PYRITE_ORE.get().defaultBlockState()
        ));
    }
}
