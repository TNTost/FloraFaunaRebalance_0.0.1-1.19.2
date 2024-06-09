package net.tabby.florafaunarebalance.entity.client.renderer.core;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tabby.florafaunarebalance.entity.client.models.PhysicsEntityModel;
import net.tabby.florafaunarebalance.entity.unique.core.PhysicsEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@OnlyIn(Dist.CLIENT)
public abstract class VoxelEntityRenderer<T extends Entity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
    protected M model;
    protected final List<RenderLayer<T, M>> layers = Lists.newArrayList();
    protected VoxelEntityRenderer(EntityRendererProvider.Context p_174008_, M model, float radius) { //# cloud entity.
        super(p_174008_);
        this.model = model;
        this.shadowRadius = radius ;
    }

    public VoxelEntityRenderer(EntityRendererProvider.Context context, M model) { //# physics entity.
        super(context);
        this.model = model;
    }

    public @NotNull M getModel() {
        return this.model;
    }


    public void render(@NotNull T entity, float p_115309_, float subTicks, PoseStack poseStack, MultiBufferSource source, int light) {
        poseStack.pushPose();
        float opacity = getOpacity(entity);
        float f6 = Mth.lerp(subTicks, entity.xRotO, entity.getXRot());

        float time = entity.tickCount + subTicks;
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.translate(0.0D, -1.501F, 0.0D);

        this.model.prepareMobModel(entity, 0.0f, 0.0f, subTicks);
        this.model.setupAnim(entity, 0.0f, 0.0f, time, 0.0f, f6);  //# 5th var -> head-body rotation offset..

        RenderType rendertype = RenderType.entityTranslucent(this.getTextureLocation(entity), true); //# magic, entityTranslucent < entityTranslucentEmissive..
        this.model.renderToBuffer(poseStack, source.getBuffer(rendertype), light, getOverlayCoords(entity, 0.0f), 1.0F, 1.0F, 1.0F, opacity);
        if (!entity.isSpectator()) {
            for(RenderLayer<T, M> renderlayer : this.layers) {
                renderlayer.render(poseStack, source, light, entity, 0.0f, 0.0f, subTicks, time, 0.0f, f6);
            }
        }

        poseStack.popPose();
        super.render(entity, p_115309_, subTicks, poseStack, source, light);
    }
    protected float getOpacity(T entity) {
        return 1;
    }
    public static int getOverlayCoords(Entity p_115339_, float p_115340_) {
        return OverlayTexture.pack(OverlayTexture.u(p_115340_), OverlayTexture.v(false));
    }
    //TODO: fix water transparency issue..
}
