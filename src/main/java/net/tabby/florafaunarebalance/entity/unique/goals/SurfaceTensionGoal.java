package net.tabby.florafaunarebalance.entity.unique.goals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.tabby.florafaunarebalance.entity.unique.SkeeterEntity;

public class SurfaceTensionGoal extends Goal {
    SkeeterEntity skeet;
    public SurfaceTensionGoal(SkeeterEntity waterSkeeter, float v) {
        this.skeet = waterSkeeter;

    }

    @Override
    public boolean canUse() {
        SkeeterEntity skt = this.skeet;
        return !skt.isInWater() && skt.level.getBlockState(skt.getOnPos().offset(0d, 0.125d, 0d)).is(Blocks.WATER); //.offset(0d, -0.125d, 0d)
    }
    @Override
    public void tick() {
        this.skeet.setDeltaMovement(this.skeet.getDeltaMovement().add(new Vec3(0d, 0.079d + Math.min(this.skeet.getDeltaMovement().y * 0.01 + 0.0024, 0), 0d)));
    }


    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}
