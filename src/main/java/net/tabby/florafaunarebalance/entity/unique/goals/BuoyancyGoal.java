package net.tabby.florafaunarebalance.entity.unique.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.tabby.florafaunarebalance.entity.unique.core.Avian;
import net.tabby.florafaunarebalance.util.FFRUtil;
import net.tabby.florafaunarebalance.util.Math.Mh;

import java.util.*;


public class BuoyancyGoal extends Goal {
    protected float[] vMap;
    protected float reciprocal;
    protected float BbHeight;

    private final Avian animal;
    public BuoyancyGoal(Avian avian) {
        this.animal = avian;
        BbHeight = avian.getBbHeight();
        reciprocal = 1 / BbHeight;
        vMap = avian.getBuoyancyMap();

        this.setFlags(EnumSet.of(Flag.JUMP));
        avian.getNavigation().setCanFloat(true);
    }
    public boolean canUse() {
        //boolean flg = this.animal.isInWater() && this.animal.getFluidTypeHeight(ForgeMod.WATER_TYPE.get()) > this.animal.getFluidJumpThreshold();
        return this.animal.isInWater() || this.animal.isInFluidType((fluidType, height) -> this.animal.canSwimInFluidType(fluidType) && height > this.animal.getFluidJumpThreshold());
    }
    public boolean requiresUpdateEveryTick() {
        return true;
        //return this.animal.isInWater() || this.animal.isInFluidType(((fluidType, height) -> this.animal.canSwimInFluidType(fluidType) && height > this.animal.getFluidJumpThreshold()));
    } //TODO: add if in water check


    /**buoyancy calculation
     * of<volume underwater[if duck in water(duck bottom y compared to surface, of that percentage underwater) in function(duck volume compared to percentage submerged)], upwards force countering gravity>
     *     due minecraft-entities not rotate along gravity then lookup-table of duck volume percentages suffices...
     *   gravity subtracts 0.08 velocity from entities divided by 0.98, making terminal velocity 3.92 BpT.
     */


    public void tick() { // TODO: if depth > entity-height, apply full force.
        double depthPrc = Math.min(getDepth() * (1 / this.animal.getBbHeight()), 1.0d) * 10; //reciprocal has to be calculated here or will grab wrong BbHeight..
        int ptr = (int) Math.ceil(depthPrc);
        double frac = ptr - depthPrc; // percentage of lower number multiplicant.

        float intermediate = (float) (vMap[ptr + 1] * (1 - frac) + vMap[ptr] * frac);

        float force = this.animal.getBuoyantForce();
        if (this.animal.isBaby()) {
            force *= 0.75f;
        }
        this.animal.moveRelative(intermediate * force, Vec3.atLowerCornerOf(Direction.UP.getNormal())); //use prc for percentage.
    }

    protected double getDepth() {
        Mob ani = this.animal;
        double pDepth = ani.getFluidTypeHeight(ForgeMod.WATER_TYPE.get());
        System.out.println(ani.getBbHeight());
        for (int i = 0; i < 42; i++) {
            BlockPos pos = ani.blockPosition().relative(Direction.UP, i + 1); // plus due block broken when sit not accounting for gap compared to water delay.
            if (ani.getLevel().getBlockState(pos).getFluidState().is(Fluids.EMPTY)) {
                if (i >= 2 || (i == 1 && ani.getY() - ani.getBlockY() < 1 - ani.getBbHeight())) {
                    return i + Mh.frac(pDepth); // if position .2above == air OR if .1above along ani.getY() - ani.getBlockY() smaller than 1 - ani.getBbHeight().
                } else return pDepth;
            }
        }
        ani.hurt(FFRUtil.DEPTH_PRESSURE, 3);
        return 900; //# crush depth reached, check if 900 to kill mob;
    }
}
