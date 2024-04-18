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
import net.minecraft.world.level.material.FluidState;
import net.tabby.florafaunarebalance.entity.FFRenty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkeeterEntity extends Animal {
    public SkeeterEntity(EntityType<? extends Animal> p_30341_, Level p_30342_) {
        super(p_30341_, p_30342_);
    }
    //public boolean canStandOnFluid(@NotNull FluidState fluidState) {
    //    return true;
    //}

    @Override
    protected void registerGoals() {
        GoalSelector ai = this.goalSelector;
        ai.addGoal(10, new BreedGoal(this, 1.1));
        ai.addGoal(15, new TemptGoal(this, 1.1, Ingredient.of(Items.SPIDER_EYE), false));
        ai.addGoal(20, new FollowParentGoal(this, 1.1f));
        ai.addGoal(30, new LookAtPlayerGoal(this, Player.class, 3f));
        //# TODO: new LookAtPreyGoal (this, <radius>, entityList...)
        ai.addGoal(40, new RandomStrollGoal(this, 1.1f));
        ai.addGoal(50, new RandomLookAroundGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 7)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_KNOCKBACK, 1.3f)
                .add(Attributes.ATTACK_SPEED, 0.2f)
                .add(Attributes.ATTACK_DAMAGE, 1.3f)

                .add(Attributes.FOLLOW_RANGE, 500);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob skeeter) {
        return FFRenty.WATER_SKEETER.get().create(level);
    }
    @Override
    public boolean isFood(ItemStack item) {
        return item.is(Items.SPIDER_EYE); //#TODO: find a-way to <.or> this.
    }
}
