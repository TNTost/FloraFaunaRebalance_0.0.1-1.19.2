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
    public static double frac(double y) {
        return y - Mth.floor(y);
    }
    public static int mod(int num, int div) {
        if (num < 100) {
            while (num >= div) {
                num -= div;
            }
        }
        return num;
    }

}
