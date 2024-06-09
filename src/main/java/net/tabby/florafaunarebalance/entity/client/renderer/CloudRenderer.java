package net.tabby.florafaunarebalance.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.entity.client.FFRml;
import net.tabby.florafaunarebalance.entity.client.models.SandDustCloudModel;
import net.tabby.florafaunarebalance.entity.client.renderer.core.VoxelEntityRenderer;
import net.tabby.florafaunarebalance.entity.unique.SandDustCloud;
import org.jetbrains.annotations.NotNull;

public class CloudRenderer extends VoxelEntityRenderer<SandDustCloud, SandDustCloudModel<SandDustCloud>> {
    private static final ResourceLocation CLOUD_TEXTURE = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/entity/dust_cloud.png");
    public CloudRenderer(EntityRendererProvider.Context context) { //# make cloud use several moving / animated particle layers to simulate the effect rain tries to create... OR just fix the transparency issue..
        super(context, new SandDustCloudModel<>(context.bakeLayer(FFRml.DUST_CLOUD_LAYER)), 1.4f);
    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SandDustCloud cloud) {
        return CLOUD_TEXTURE;
    }

    @Override
    public void render(@NotNull SandDustCloud cloud, float p_115309_, float subTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int light) {
        float size = Mth.clamp(cloud.getSize(), 0.5f, 12f);
        poseStack.scale(size, size, size);



        super.render(cloud, p_115309_, subTicks, poseStack, source, light);
    }

    @Override
    protected float getOpacity(SandDustCloud cloud) {
        float transparency = 0.65f;
        return (float) (transparency * cloud.getOpacity());
    }
}
