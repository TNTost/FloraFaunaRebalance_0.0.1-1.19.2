package net.tabby.florafaunarebalance.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.entity.client.FFRml;
import net.tabby.florafaunarebalance.entity.client.models.SkeeterModel;
import net.tabby.florafaunarebalance.entity.unique.SkeeterEntity;
import org.jetbrains.annotations.NotNull;

public class SkeeterRenderer extends MobRenderer<SkeeterEntity, SkeeterModel<SkeeterEntity>> {
    public SkeeterRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeeterModel<>(context.bakeLayer(FFRml.SKEETER_LAYER)), 2f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SkeeterEntity skeeter) {
        return new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/entity/water_skeeter.png");
    }

    @Override
    public void render(SkeeterEntity skeeter, float yaw, float subTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int packedLight) {
        if (skeeter.isBaby()) {
            float scale = 0.2f;
            poseStack.scale(scale, scale, scale); //# set it to adult size, then scale to negate vanilla baby-entity scaling.
            skeeter.setBoundingBox(new AABB(new Vec3(skeeter.getX() - 1.15f, skeeter.getY(), skeeter.getZ() - 1.15f), new Vec3(skeeter.getX() + 1.15f, skeeter.getY() + 1.2f, skeeter.getZ() + 1.15f)));
        }
        super.render(skeeter, yaw, subTicks, poseStack, source, packedLight);
    }
}
