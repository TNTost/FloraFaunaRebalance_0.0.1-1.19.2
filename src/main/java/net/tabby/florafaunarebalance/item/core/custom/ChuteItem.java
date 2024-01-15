package net.tabby.florafaunarebalance.item.core.custom;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.tabby.florafaunarebalance.item.core.custom.animation.FFRia;
import net.tabby.florafaunarebalance.util.FFRTags;
import org.jetbrains.annotations.NotNull;


import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.tabby.florafaunarebalance.util.FFRTags.Items.*;

public class ChuteItem extends BowItem{
    public static final Predicate<ItemStack> DART_ONLY = itemStack -> itemStack.is(DART_TAG);

    public ChuteItem(Properties p_40660_) {
        super(p_40660_);
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return DART_ONLY;
    }
    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack p_40678_) {
        return UseAnim.CUSTOM;
    }



        //# split this up into method call to other class to reduce clutter later..
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
}
