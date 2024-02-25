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
import org.jetbrains.annotations.NotNull;

import java.util.List;


@OnlyIn(Dist.CLIENT)
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


    public void render(T entity, float p_115309_, float subTicks, PoseStack poseStack, MultiBufferSource source, int light) {
        poseStack.pushPose();

        float f2 = 0.0f; //# head-body rotation offset..
        float f6 = Mth.lerp(subTicks, entity.xRotO, entity.getXRot());

        float f7 = entity.tickCount + subTicks;
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        this.scale(entity, poseStack, subTicks);
        poseStack.translate(0.0D, -1.501F, 0.0D);

        this.model.prepareMobModel(entity, 0.0f, 0.0f, subTicks);
        this.model.setupAnim(entity, 0.0f, 0.0f, f7, f2, f6);

        RenderType rendertype = RenderType.entityTranslucent(this.getTextureLocation(entity), true); //# magic, entityTranslucent < entityTranslucentEmissive..
        this.model.renderToBuffer(poseStack, source.getBuffer(rendertype), light, getOverlayCoords(entity, 0.0f), 1.0F, 1.0F, 1.0F, 0.65f); // last param: opacity..

        if (!entity.isSpectator()) {
            for(RenderLayer<T, M> renderlayer : this.layers) {
                renderlayer.render(poseStack, source, light, entity, 0.0f, 0.0f, subTicks, f7, f2, f6);
            }
        }

        poseStack.popPose();
        super.render(entity, p_115309_, subTicks, poseStack, source, light);
    }
    public static int getOverlayCoords(Entity p_115339_, float p_115340_) {
        return OverlayTexture.pack(OverlayTexture.u(p_115340_), OverlayTexture.v(false));
    }
    protected void scale(T p_115314_, PoseStack p_115315_, float p_115316_) {
    }
    //TODO: fix water transparency issue..
}
