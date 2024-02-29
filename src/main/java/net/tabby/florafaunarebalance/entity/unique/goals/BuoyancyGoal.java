package net.tabby.florafaunarebalance.entity.unique.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.tabby.florafaunarebalance.util.FFRUtil;
import net.tabby.florafaunarebalance.util.Math.Mh;

import java.util.EnumSet;

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
     *     due minecraft-entities not rotate along gravity then lookup-table of duck volume percentages suffices...
     *   gravity subtracts 0.08 velocity from entities divided by 0.98, making terminal velocity 3.92 BpT.
     */
    public void tick() { // TODO: if depth > entity-height, apply full force.
        this.animal.moveRelative((float) Math.min(getDepth() / this.animal.getBbHeight(), 2.0d), new Vec3(0, 0.014, 0)); //use prc for percentage.
    } //this.animal.getJumpControl().jump(); <<--- alternative...

    protected double getDepth() {
        Mob ani = this.animal;
        double pDepth = ani.getFluidTypeHeight(ForgeMod.WATER_TYPE.get());
        for (int i = 0; i < 42; i++) {
            BlockPos pos = ani.blockPosition().relative(Direction.UP, i + 1); // plus due block broken when sit not accounting for gap compared to water delay.
            if (ani.getLevel().getBlockState(pos).getFluidState().is(Fluids.EMPTY)) {
                if (i >= 2 || (i >= 1 && ani.getY() - ani.getBlockY() < 1 - ani.getBbHeight())) {
                    return i + Mh.frac(pDepth); // if position .2above == air OR if .1above along ani.getY() - ani.getBlockY() smaller than 1 - ani.getBbHeight().
                } else return pDepth;
            }
        }
        ani.hurt(FFRUtil.DEPTH_PRESSURE, 3);
        return 900; //# crush depth reached, check if 900 to kill mob;
    }
}
