package net.tabby.florafaunarebalance.item.core.custom;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.ForgeEventFactory;
import net.tabby.florafaunarebalance.entity.custom.DartProjectileEntity;
import net.tabby.florafaunarebalance.item.FFRii;
import net.tabby.florafaunarebalance.util.FFRTags;
import org.jetbrains.annotations.NotNull;


import java.util.function.Consumer;
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
                    ammo = new ItemStack(FFRii.UNTIPPED_DART.get()); //# set dart in case of no item present.
                }
                float pow = getPowerForTime(getUseDuration(chuteItem) - t);
                if ((double) pow >= 0.35) {
                    shootProjectile(level, player, chuteItem, ammo, pow, inf); //# shoot the projectile, duh.
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LLAMA_SPIT, SoundSource.PLAYERS, 1.0f, (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2f); //# interesting sound <- accidental creation
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
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean flag = !player.getProjectile(itemStack).isEmpty();
        InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onArrowNock(itemStack, level, player, hand, flag);
        if (ret != null) {
            return ret;
        } else if (!flag && !player.getAbilities().instabuild) {
            return InteractionResultHolder.fail(itemStack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemStack);
        }

        //int Oxy = player.getAirSupply();
            //if (player.isCrouching() && Oxy > 30) { //# sneaking enables rapid fire at oxygen cost.
            //    player.setAirSupply(Oxy = Math.max(Oxy - 60 + 12 * EnchantmentHelper.getRespiration(player), 0)); //# make fancy counter + checksum to animate the drainage.
            //    player.getCooldowns().addCooldown(itemStack.getItem(), Oxy < 30 ? 75 : 5);
//
            //    shootProjectileWithCooldown(level, player, hand, itemStack);
            //    return InteractionResultHolder.consume(itemStack);
            //} else {

            //}
    }

    protected static void shootProjectile(Level level, Player player, ItemStack chuteItem, ItemStack ammo, float pow, boolean flag) {
        if (!level.isClientSide) {
            Entity projectile;
            if (ammo.is(Items.FIREWORK_ROCKET)) { //# check if is fireworks.
                projectile = new FireworkRocketEntity(level, ammo, player, player.getX(), player.getEyeY() - 0.15000000596046448, player.getZ(), true);
            } else {
                Item i = ammo.getItem(); //# add edge case: .getProjectile() returns arrow when /gmc && no item found.
                DartItem dartItem = (DartItem) (i); //# may induce bug in future, scrap if not.
                DartProjectileEntity dartProjectile = (DartProjectileEntity) dartItem.createDart(level, ammo, player);
                dartProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, pow * 2.0f, 1.0f);
                if (pow == 1.0f) {
                    dartProjectile.setCritArrow(true);
                }
                if (flag) {
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
    @Override
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                int i = arm == HumanoidArm.RIGHT ? 1 : -1;
                poseStack.translate(i * 0.56f, -0.52f, -0.72f);
                if (player.getUseItem() == itemInHand && player.isUsingItem()) {
                    poseStack.translate(0.0, -0.05, 0.0);
                }   return true;
            }
        });
    }

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
