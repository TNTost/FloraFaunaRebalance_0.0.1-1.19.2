package net.tabby.florafaunarebalance.entity.unique.core;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public abstract class Avian extends BuoyantEntity implements FlyingAnimal {
    protected Avian(EntityType<? extends Animal> Avian, Level level) {
        super(Avian, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0f);
    }
    @Override
    public boolean isFlying() {
        return false;
    }

    //@Override
    //public float getBuoyantForce() {
    //    return this.isBaby() ? f *= 0.75f : f;
    //}
}
