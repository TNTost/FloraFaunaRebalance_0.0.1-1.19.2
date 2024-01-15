package net.tabby.florafaunarebalance.item.core.custom;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;


import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.tabby.florafaunarebalance.util.FFRTags.Items.*;
//# make it so ChuteItem extends ProjectileWeaponItem instead...
public class ChuteItem extends ProjectileWeaponItem {
    public static final Predicate<ItemStack> DART_ONLY = itemStack -> itemStack.is(DART_TAG);

    public ChuteItem(Properties p_40660_) {
        super(p_40660_);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!player.getProjectile(itemStack).isEmpty()) {
            int Oxy;
            if (player.isCrouching() && (Oxy = player.getAirSupply()) > 0) {
                System.out.println(Oxy);
                return InteractionResultHolder.consume(itemStack);
            } else {
                return InteractionResultHolder.consume(itemStack);
            }
        } else {
            return InteractionResultHolder.fail(itemStack);
        }
    }


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
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return DART_ONLY;
    }
    public int getDefaultProjectileRange() {
        return 12;
    }
}
