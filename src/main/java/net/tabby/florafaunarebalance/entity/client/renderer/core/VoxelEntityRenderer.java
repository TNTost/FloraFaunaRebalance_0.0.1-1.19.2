package net.tabby.florafaunarebalance.entity.client.renderer.core;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;


public abstract class VoxelEntityRenderer<T extends Entity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
    protected M model;
    protected final List<RenderLayer<T, M>> layers = Lists.newArrayList();
    protected VoxelEntityRenderer(EntityRendererProvider.Context p_174008_, M model, float radius) {
        super(p_174008_);
        this.model = model;
        this.shadowRadius = radius ;
    }
    public @NotNull M getModel() {
        return this.model;
    }
    public void render(T entity, float p_115309_, float p_115310_, PoseStack poseStack, MultiBufferSource p_115312_, int p_115313_) {
        poseStack.pushPose();

        float f2 = 0.0f; //# head-body rotation offset..
        float f6 = Mth.lerp(p_115310_, entity.xRotO, entity.getXRot());

        float f7 = this.getBob(entity, p_115310_);
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        this.scale(entity, poseStack, p_115310_);
        poseStack.translate(0.0D, -1.501F, 0.0D);
        float f8 = 0.0F;
        float f5 = 0.0F;

        this.model.prepareMobModel(entity, f5, f8, p_115310_);
        this.model.setupAnim(entity, f5, f8, f7, f2, f6);
        Minecraft minecraft = Minecraft.getInstance();
        boolean flag = this.isVisible(entity);
        boolean flag1 = !flag && !entity.isInvisibleTo(minecraft.player);
        boolean flag2 = minecraft.shouldEntityAppearGlowing(entity);
        RenderType rendertype = this.getRenderType(entity, flag, flag1, flag2);
        if (rendertype != null) {
            VertexConsumer vertexconsumer = p_115312_.getBuffer(rendertype);
            int i = getOverlayCoords(entity, 0.0f);
            this.model.renderToBuffer(poseStack, vertexconsumer, p_115313_, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
        }

        if (!entity.isSpectator()) {
            for(RenderLayer<T, M> renderlayer : this.layers) {
                renderlayer.render(poseStack, p_115312_, p_115313_, entity, f5, f8, p_115310_, f7, f2, f6);
            }
        }

        poseStack.popPose();
        super.render(entity, p_115309_, p_115310_, poseStack, p_115312_, p_115313_);
    }
    @Nullable
    protected RenderType getRenderType(T p_115322_, boolean p_115323_, boolean p_115324_, boolean p_115325_) {
        ResourceLocation resourcelocation = this.getTextureLocation(p_115322_);
        if (p_115324_) {
            return RenderType.itemEntityTranslucentCull(resourcelocation);
        } else if (p_115323_) {
            return this.model.renderType(resourcelocation);
        } else {
            return p_115325_ ? RenderType.outline(resourcelocation) : null;
        }
    }
    public static int getOverlayCoords(Entity p_115339_, float p_115340_) {
        return OverlayTexture.pack(OverlayTexture.u(p_115340_), OverlayTexture.v(false));
    }
    protected float getWhiteOverlayProgress(T p_115334_, float p_115335_) {
        return 0.0F;
    }
    protected boolean isVisible(T cloud) {
        return !cloud.isInvisible();
    }
    protected float getBob(T entity, float p_115306_) {
        return (float)entity.tickCount + p_115306_;
    }
    protected void scale(T p_115314_, PoseStack p_115315_, float p_115316_) {
    }
}
