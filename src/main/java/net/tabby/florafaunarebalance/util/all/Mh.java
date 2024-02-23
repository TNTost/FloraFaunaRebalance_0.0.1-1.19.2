package net.tabby.florafaunarebalance.util.all;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.HitResult;
import oshi.util.tuples.Pair;

public class Mh { //# damnit intelIj, why not allow unicode chars....
    public static float piOver180 = 0.017453292519943295769f;
    public static float tan(float theta) {
        return Mth.sin(theta * piOver180) / Mth.cos(theta * piOver180);
    }
    public static float rad(float degree) {
        return degree * piOver180;
    }
    /////////Vec3 hajime = new Vec3(ply.getX(), ply.getY() + ply.getEyeHeight(), ply.getZ());
    /////////Vec3 mato = hajime.add(ply.getViewVector(1.0f).scale(12));
    /////////event.getLevel().setBlockAndUpdate(new BlockPos(mato), Blocks.DIAMOND_BLOCK.defaultBlockState());
/////////
/////////
    /////////float xRot = ply.getXRot(); //upwards rotation
    /////////float yRot = ply.getYRot(); //sideways angle
/////////
    /////////double pY = ply.getY() + ply.getEyeHeight(); //height of eyes/ray start
    /////////System.out.println(pY);
    /////////double dy = pY - Mth.floor(pY);
/////////
    /////////float tan1 = -1 / Mh.tan(xRot);
    /////////double IntcVecY = dy * tan1;
    /////////int upOfst = xRot < 0 ? 1 : -1;
/////////
    ///////////TODO fix x & z calc.........
    /////////Vec3 YVec = new Vec3(IntcVecY * -Mth.sin(Mh.rad(yRot)), pY, IntcVecY * Mth.cos(Mh.rad(yRot))); //forgot to add dy to initial point..
    /////////Vec3 DeltaY = new Vec3(tan1 * -Mth.sin(Mh.rad(yRot)), upOfst, tan1 * Mth.cos(Mh.rad(yRot)));
/////////
    /////////for (int i = 0; i < 2; i++) {
    /////////    event.getLevel().setBlockAndUpdate(new BlockPos(ply.getX() + YVec.x, YVec.y + upOfst, ply.getZ() + YVec.z), Blocks.DIRT.defaultBlockState());
    /////////    YVec = YVec.add(DeltaY);
    /////////}

    //BlockPos waterAt = getWaterHitPos(event.getLevel(), event.getEntity());
    //System.out.println(waterAt);
    //when marching forward by dx ORdy ORdz, check if hit block in direction of axis [if checking next Y, check block above intersect..]

}
