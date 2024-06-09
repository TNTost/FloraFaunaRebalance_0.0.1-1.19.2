package net.tabby.florafaunarebalance.entity.client.renderer.core;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tabby.florafaunarebalance.entity.client.FFRml;
import net.tabby.florafaunarebalance.entity.client.models.PhysicsEntityModel;
import net.tabby.florafaunarebalance.entity.unique.core.PhysicsEntity;
import net.tabby.florafaunarebalance.util.FFR.F;
import org.jetbrains.annotations.NotNull;

public class PhysicsEntityRenderer extends VoxelEntityRenderer<PhysicsEntity, PhysicsEntityModel<PhysicsEntity>> {
    public PhysicsEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new PhysicsEntityModel<>(context.bakeLayer(FFRml.SLING_LAYER)));
    }
    @Override
    public void render(@NotNull PhysicsEntity entity, float p_115309_, float subTicks, PoseStack poseStack, MultiBufferSource source, int light) {
        super.render(entity, p_115309_, subTicks, poseStack, source, light);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull PhysicsEntity entity) {
        return F.lr("textures/entity/physics/sling_entity.png");
    }
}
