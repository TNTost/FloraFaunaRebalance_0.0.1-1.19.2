package net.tabby.florafaunarebalance.entity.unique;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.tabby.florafaunarebalance.entity.FFRenty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DuckEntity extends Animal {
    public DuckEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    //# TODO: float but not bob goal / fix.
    //# TODO /simplify add goals using list/array...
    @Override
    protected void registerGoals() {
        GoalSelector ai = this.goalSelector;
        ai.addGoal(0, new FloatGoal(this));
        ai.addGoal(1, new BreedGoal(this, 1.35)); // eggs, nests.
        //# replace with peas.
        ai.addGoal(3, new TemptGoal(this, 0.75, Ingredient.of(Items.BREAD), true));
        ai.addGoal(2, new FollowParentGoal(this, 1.15));
        ai.addGoal(4, new RandomSwimmingGoal(this, 1, 70)); //mob, speed, interval.
        ai.addGoal(4, new RandomStrollGoal(this, 1.15));

        ai.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4));
        ai.addGoal(6, new RandomLookAroundGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.65)
                .add(Attributes.ATTACK_DAMAGE, 0.5f)
                .add(Attributes.FOLLOW_RANGE, 300);
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
