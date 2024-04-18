package net.tabby.florafaunarebalance.entity.unique.core;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;

public class Avian extends Animal implements FlyingAnimal {
    protected Avian(EntityType<? extends Animal> Avian, Level level) {
        super(Avian, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0f);
    }
    public float[] getBuoyancyMap() {
        return new float[]{0.0f, 0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};
    }
    public float getBuoyantForce() {
        return 0.019f;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
