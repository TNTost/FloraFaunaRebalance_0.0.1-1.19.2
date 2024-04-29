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
import net.tabby.florafaunarebalance.entity.unique.core.BuoyantEntity;
import net.tabby.florafaunarebalance.entity.unique.goals.BuoyancyGoal;
import net.tabby.florafaunarebalance.entity.unique.goals.SurfaceTensionGoal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkeeterEntity extends BuoyantEntity {
    public SkeeterEntity(EntityType<? extends Animal> p_30341_, Level p_30342_) {
        super(p_30341_, p_30342_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0f);
    }
    @Override
    public float[] getBuoyancyMap() {
                         //#   0.4  0.05   0.015   0.03   0.08   0.13   0.17   0.09   0.05   0.02   0.01
        return new float[]{0.00f, 0.09f, 0.12f, 0.23f, 0.37f, 0.53f, 0.66f, 0.83f, 0.92f, 0.97f, 0.99f, 1.0f};
    }
    @Override
    public float getBuoyantForce() {
        return 0.012f;
    }


    @Override
    protected void registerGoals() {
        GoalSelector ai = this.goalSelector;
        ai.addGoal(5, new SurfaceTensionGoal(this, 1f));
        ai.addGoal(9, new BuoyancyGoal(this));
        //# TODO: new EggLayGoal (this, predicate<location criteria>, modify speed).
        ai.addGoal(10, new BreedGoal(this, 1.1));
        ai.addGoal(15, new TemptGoal(this, 1.1, Ingredient.of(Items.SPIDER_EYE), false));
        ai.addGoal(20, new FollowParentGoal(this, 1.15f));
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

    @Override
    public float getScale() {
        return this.isBaby() ? 0.2f : 1.0f;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob skeeter) {
        return FFRenty.WATER_SKEETER.get().create(level); //# TODO: larvae cycle, baby skeeters are in larva state, adult skeeters are tiny, rare old gigantic skeeters are full size
    }
    @Override
    public boolean isFood(ItemStack item) {
        return item.is(Items.SPIDER_EYE); //#TODO: find a-way to <.or> this.
    }
}
