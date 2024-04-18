package net.tabby.florafaunarebalance.util.Math;

import net.minecraft.util.Mth;

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



}
