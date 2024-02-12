package net.tabby.florafaunarebalance.item.core.custom;


import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.tabby.florafaunarebalance.entity.custom.DartProjectileEntity;
import net.tabby.florafaunarebalance.item.FFRii;
import net.tabby.florafaunarebalance.util.all.FFRUtil;
import org.jetbrains.annotations.NotNull;


import java.util.function.Predicate;

import static java.lang.StrictMath.sqrt;
import static net.tabby.florafaunarebalance.util.FFRTags.Items.*;
import static net.tabby.florafaunarebalance.util.all.FFRUtil.getAmmo;

//# make it so ChuteItem extends ProjectileWeaponItem instead...
public class ChuteItem extends ProjectileWeaponItem {
    public static final float BREATH_DURATION_CAP = 17.0f;
    public static final Predicate<ItemStack> DART_ONLY = itemStack -> itemStack.is(DART_TAG);
    public static final Predicate<ItemStack> DART_OR_FIREWORKS = DART_ONLY.or(itemStack -> itemStack.is(Items.FIREWORK_ROCKET));

    public ChuteItem(Properties p_40660_) {
        super(p_40660_);
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


    public void releaseUsing(ItemStack chuteItem, Level level, LivingEntity entity, int t) {
        if (entity instanceof Player player) { //# instanceof <Type> "variableName" makes new var.
            ItemStack ammo = getProjectile(player, chuteItem, getAllSupportedProjectiles()); //# loops through inv to find item.
            boolean inf = player.getAbilities().instabuild;

            if (!ammo.isEmpty() || inf) {
                if (ammo.isEmpty() || ammo.is(Items.ARROW)) { //# remove arrow...
                    ammo = new ItemStack(FFRii.DART.get()); //# set dart in case of no item present.
                }
                float pow = getPowerForTime(t = getUseDuration(chuteItem) - t);
                if (ammo.is(Items.FIREWORK_ROCKET)) {
                    pow = (pow = getPowerForTime(t / 1.6f)) >= 0.9f ? pow : 0.0f;
                    ammo = getProjectile(player, chuteItem, itemStack -> itemStack.is(Items.FIREWORK_ROCKET)); //# predicate usage...
                }
                if (pow >= 0.15f) { //# TODO: fireworks calls getArrow despite not firing due to low power...
                    //Projectile projectile = fwFullPow ? new FireworkRocketEntity(level, ammo, player, player.getX(), player.getEyeY() - 0.15000000596046448, player.getZ(), true) : null; //# set as new firework rocket when ammo matches, otherwise null.
                    //System.out.println(projectile);
                    shootProjectile(null, level, player, chuteItem, ammo, pow, inf); //# shoot the projectile, duh.
                    //level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LLAMA_SPIT, SoundSource.PLAYERS, 1.0f, (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2f); //# interesting sound <- accidental creation
                    if (!inf) { //# if in creative, do-not test for fireworks here...
                        ammo.shrink(1);
                        if (ammo.isEmpty()) {
                            player.getInventory().removeItem(ammo);
                        }
                    }
                }
            }
        }
    }
    public static float getPowerForTime(float ivt) { //# inverse of time
        return (ivt = (float) (1 - sqrt(1 - ivt / BREATH_DURATION_CAP))) >= 1.0f || (ivt != ivt) ? 1.0f : ivt; //# 1 minus sqrt of 1 minus x...
    }
    //# math was here, but redundant float > double.
    public ItemStack getProjectile(Player player, ItemStack rangedWpn, Predicate<ItemStack> predicate) {
        return net.minecraftforge.common.ForgeHooks.getProjectile(player, rangedWpn, FFRUtil.getAmmo(player, predicate));
    }


    protected static void shootProjectile(Projectile projectile, Level level, Player player, ItemStack chuteItem, ItemStack ammo, float pow, boolean inf) {
        if (!level.isClientSide) { //# set Entity to null when firing non-dart...
            if (projectile == null) { //# default to dart.
                projectile = getDart(level, player, ammo, pow, inf);
            }
            float relativeYaw = 0.0f;
            Quaternion q = new Quaternion(new Vector3f(player.getUpVector(1.0f)), relativeYaw, true);
            Vector3f vec = new Vector3f(player.getViewVector(1.0f));
            vec.transform(q);
            projectile.shoot(vec.x(), vec.y(), vec.z(), pow * 2.0f, 1.0f);

            //# enchant handler /> [unbreaking], [mending], [power], [barrage], [gathering]
            chuteItem.hurtAndBreak(1, player, (breakEvent) -> breakEvent.broadcastBreakEvent(player.getUsedItemHand())); //# odd naming.
            level.addFreshEntity(projectile);
        }
    }
    protected static DartProjectileEntity getDart(Level level, Player player, ItemStack ammo, float pow, boolean inf) {
        DartProjectileEntity dart = (DartProjectileEntity) ((DartItem) ammo.getItem()).createDart(level, ammo, player); //# convert item -> dartItem /> createDart -> dartEntity /> shoot that.
        if (pow == 1.0f) { //# or if insta-shot.                        //# casting is important...
            dart.setCritArrow(true);
        } if (inf) { //# if in /gmc, don't pickup leftover arrow.
            dart.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }
        return dart;
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
