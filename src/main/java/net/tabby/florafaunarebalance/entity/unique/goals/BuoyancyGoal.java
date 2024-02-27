package net.tabby.florafaunarebalance.entity.unique.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeMod;
import net.tabby.florafaunarebalance.util.all.Mh;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class BuoyancyGoal extends Goal {
    private final Mob animal;
    public BuoyancyGoal(Mob duck) {
        this.animal = duck;
        this.setFlags(EnumSet.of(Flag.JUMP));
        duck.getNavigation().setCanFloat(true);
    }
    public boolean canUse() {
        //boolean flg = this.animal.isInWater() && this.animal.getFluidTypeHeight(ForgeMod.WATER_TYPE.get()) > this.animal.getFluidJumpThreshold();
        return this.animal.isInWater() || this.animal.isInFluidType((fluidType, height) -> this.animal.canSwimInFluidType(fluidType) && height > this.animal.getFluidJumpThreshold());
    }
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    /**buoyancy calculation
     * of<volume underwater[if duck in water(duck bottom y compared to surface, of that percentage underwater) in function(duck volume compared to percentage submerged)], upwards force countering gravity>
     */
    public void tick() {
        getDepth();
        //this.animal.getJumpControl().jump();
    }

    public void getDepth() {
        Mob ani = this.animal;
        double pDepth = ani.getFluidTypeHeight(ForgeMod.WATER_TYPE.get());
        for (int i = 0; i < 120; i++) {
            BlockPos pos = ani.blockPosition().relative(Direction.UP, i + 1); // plus due block broken when sit not accounting for gap compared to water delay
            if (ani.getLevel().getBlockState(pos).getFluidState().is(Fluids.EMPTY)) {
                if (i >= 2 || (i >= 1 && ani.getY() - ani.getBlockY() < 1 - ani.getBbHeight())) {
                    System.out.println(i + Mh.frac(pDepth));
                } else {
                    System.out.println(pDepth);
                }
                break;
            }
        }
        // if position .2above == air OR if .1above along ani.getY() - ani.getBlockY() smaller than 1 - ani.getBbHeight()
    }
}
