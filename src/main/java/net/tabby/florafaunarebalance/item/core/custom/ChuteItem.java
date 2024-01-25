package net.tabby.florafaunarebalance.item.core.custom;


import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.tabby.florafaunarebalance.entity.custom.DartProjectileEntity;
import net.tabby.florafaunarebalance.item.FFRii;
import org.jetbrains.annotations.NotNull;


import java.util.function.Predicate;

import static net.tabby.florafaunarebalance.util.FFRTags.Items.*;
//# make it so ChuteItem extends ProjectileWeaponItem instead...
public class ChuteItem extends ProjectileWeaponItem {
    public static final float BREATH_DURATION_CAP = 12.0f;
    public static final Predicate<ItemStack> DART_ONLY = itemStack -> itemStack.is(DART_TAG);
    public static final Predicate<ItemStack> DART_OR_FIREWORKS = DART_ONLY.or(itemStack -> itemStack.is(Items.FIREWORK_ROCKET));

    public ChuteItem(Properties p_40660_) {
        super(p_40660_);
    }


    public void releaseUsing(ItemStack chuteItem, Level level, LivingEntity entity, int t) {
        if (entity instanceof Player player) { //# instanceof <Type> "variableName" makes new var.
            ItemStack ammo = player.getProjectile(chuteItem); //# loops through inv to find item.
            boolean inf = player.getAbilities().instabuild;

            if (!ammo.isEmpty() || inf) { //# get and check if dart OR in creative
                if (ammo.isEmpty() || ammo.is(Items.ARROW)) { //# remove arrow
                    ammo = new ItemStack(FFRii.DART.get()); //# set dart in case of no item present.
                }
                float pow = getPowerForTime(getUseDuration(chuteItem) - t);
                if ((double) pow >= 0.35) {
                    Entity projectile = ammo.is(Items.FIREWORK_ROCKET) ? new FireworkRocketEntity(level, ammo, player, player.getX(), player.getEyeY() - 0.15000000596046448, player.getZ(), true) : null; //# set as new firework rocket when ammo matches, otherwise null.
                    shootProjectile(projectile, level, player, chuteItem, ammo, pow, inf); //# shoot the projectile, duh.
                    //level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LLAMA_SPIT, SoundSource.PLAYERS, 1.0f, (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2f); //# interesting sound <- accidental creation
                    if (!inf) {
                        ammo.shrink(1);
                        if (ammo.isEmpty()) {
                            player.getInventory().removeItem(ammo);
                        }
                    }
                }
            }
        }
    }
    public static float getPowerForTime(int t) {
        float str = t / BREATH_DURATION_CAP;
        if ((str = (str * str + str * 3.5f) / 4.5f) > 1.0f) {
            str = 1.0f;
        }
        return str;
    }

    //# make it allow fireworks to be pulled only / require ignition source...
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack chuteItem = player.getItemInHand(hand);
        boolean ammo = !player.getProjectile(chuteItem).isEmpty();
        if (ammo || player.getAbilities().instabuild) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(chuteItem);
        } else {
            return InteractionResultHolder.fail(chuteItem);
        }
        //# not sure if introduces bug due to not use forgeEvents.onArrowNock...
        //# remember setAirSupply.TEMP.
    }

    protected static void shootProjectile(Entity projectile, Level level, Player player, ItemStack chuteItem, ItemStack ammo, float pow, boolean inf) {
        if (!level.isClientSide) { //# set Entity to null when firing non-dart...
            if (projectile == null) {
                DartItem dartItem = (DartItem) (ammo.getItem()); //# convert item -> dartItem /> createDart -> dartEntity /> shoot that.
                DartProjectileEntity dartProjectile = (DartProjectileEntity) dartItem.createDart(level, ammo, player); //# casting is important...
                dartProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, pow * 2.0f, 1.0f);
                if (pow == 1.0f) {
                    dartProjectile.setCritArrow(true);
                }
                if (inf) {
                    dartProjectile.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }
                projectile = dartProjectile;
            }
            //# enchant handler /> [unbreaking], [mending], [power], [barrage], [gathering]
            chuteItem.hurtAndBreak(1, player, (breakEvent) -> breakEvent.broadcastBreakEvent(player.getUsedItemHand())); //# odd naming.
            level.addFreshEntity(projectile);
        }
    }

    //public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
    //    //# make scheduler for arrow fire to allow gatling/barrage enchant to fire multiple arrows with time delta...
    //}
    //public static void shootProjectileWithCooldown(Level level, LivingEntity player, InteractionHand hand, ItemStack itemStack) {
    //}




    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack p_40678_) {
        return UseAnim.SPYGLASS;
    }
    //# split this up into method call to other class to reduce clutter later...
    //@Override
    //public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
    //    consumer.accept(new IClientItemExtensions() {
    //        @Override
    //        public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
    //            int i = arm == HumanoidArm.RIGHT ? 1 : -1;
    //            poseStack.translate(i * 0.56f, -0.52f, -0.72f);
    //            if (player.getUseItem() == itemInHand && player.isUsingItem()) {
    //                poseStack.translate(0.0, -0.05, 0.0);
    //            }   return true;
    //        }
    //    });
    //}

    public int getUseDuration(@NotNull ItemStack p_41454_) {
        return 270; //# method required to make projectile weapon, <releaseUsing.i> takes "return value - useTime"
    }
    public boolean useOnRelease(ItemStack itemStack) {
        return itemStack.is(this);
    }

    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return DART_OR_FIREWORKS;
    }
    public int getDefaultProjectileRange() {
        return 12;
    }
}
