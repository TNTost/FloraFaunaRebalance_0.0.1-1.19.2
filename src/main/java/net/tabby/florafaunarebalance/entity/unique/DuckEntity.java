package net.tabby.florafaunarebalance.entity.unique;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.tabby.florafaunarebalance.entity.FFRenty;
import net.tabby.florafaunarebalance.entity.unique.goals.BuoyancyGoal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DuckEntity extends Animal {
    public boolean waterBelow;
    public DuckEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0f);
    }

    //# TODO: float but not bob goal / fix.
    //# TODO /simplify add goals using list/array...
    @Override
    protected void registerGoals() { // TODO: boids, flight
        GoalSelector ai = this.goalSelector;
        ai.addGoal(0, new BuoyancyGoal(this));
        ai.addGoal(1, new BreedGoal(this, 0.9)); // eggs, nests.
        //# replace with peas.
        ai.addGoal(3, new TemptGoal(this, 1.75, Ingredient.of(Items.BREAD), false));
        ai.addGoal(2, new FollowParentGoal(this, 1.2));
        ai.addGoal(4, new RandomSwimmingGoal(this, 1.15, 70)); //mob, speed, interval.
        ai.addGoal(5, new RandomStrollGoal(this, 1)); //TODO: fix: ducks speedy on land compared to water...

        ai.addGoal(6, new LookAtPlayerGoal(this, Player.class, 4.7f));
        ai.addGoal(7, new RandomLookAroundGoal(this));

    }
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 0.5f)
                .add(Attributes.FOLLOW_RANGE, 300)

                .add(Attributes.FLYING_SPEED, 1.15f);
    }
    @Override
    public double getFluidJumpThreshold() {
        return this.isBaby() ? 0.15 : 0.21;
    }

    @Override
    public int getMaxAirSupply() {
        return 650;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob) {
        return FFRenty.DUCK.get().create(level);
    }
    @Override
    public boolean isFood(@NotNull ItemStack item) {
        return item.is(Items.BREAD);
    }
}
