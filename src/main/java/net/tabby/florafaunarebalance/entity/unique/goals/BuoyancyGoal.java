package net.tabby.florafaunarebalance.entity.unique.goals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraftforge.common.ForgeMod;
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
     */
    public void tick() {
        Mob ani = this.animal;
        if (ani.isInWater()) {
            lerpY(ani, ani.getFluidTypeHeight(ForgeMod.WATER_TYPE.get()) - (ani.isBaby() ? 0.05 : 0.24));
        }
// TODO: make duck jump when under water surface, but set pos to same but Y value when under
    }
    public static void lerpY (Mob a, double destination) {
        a.lerpTo(a.getX(), a.getY() + destination, a.getZ(), a.getYRot(), a.getXRot(), 5, true);
        //# temp.
    }
}
