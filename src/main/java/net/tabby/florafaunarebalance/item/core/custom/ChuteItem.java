package net.tabby.florafaunarebalance.item.core.custom;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;


import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.tabby.florafaunarebalance.util.FFRTags.Items.*;
//# make it so ChuteItem extends ProjectileWeaponItem instead...
public class ChuteItem extends ProjectileWeaponItem {
    public static final Predicate<ItemStack> DART_ONLY = itemStack -> itemStack.is(DART_TAG);
    public static final Predicate<ItemStack> DART_OR_FIREWORKS = DART_ONLY.or(itemStack -> itemStack.is(Items.FIREWORK_ROCKET));

    public ChuteItem(Properties p_40660_) {
        super(p_40660_);
    }


    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int i) {
        if (entity instanceof Player player && i > 0) { //# instanceof <Type> "variableName" makes new var
            System.out.println("hello this is use duration " + i);
        } else {
            System.out.println("you held it too long " + 1);
        }
    }

    //# make it allow fireworks to be pulled only / require ignition source...
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
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
    public int getUseDuration(ItemStack p_41454_) {
        return 270; //# method required to make projectile weapon, <releaseUsing.i> takes "return value - useTime"
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return DART_OR_FIREWORKS;
    }
    public int getDefaultProjectileRange() {
        return 12;
    }
}
