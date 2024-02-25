package net.tabby.florafaunarebalance.entity.client.renderer;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.entity.client.FFRml;
import net.tabby.florafaunarebalance.entity.client.models.SandDustCloudModel;
import net.tabby.florafaunarebalance.entity.client.renderer.core.VoxelEntityRenderer;
import net.tabby.florafaunarebalance.entity.unique.SandDustCloud;
import org.jetbrains.annotations.NotNull;

public class CloudRenderer extends VoxelEntityRenderer<SandDustCloud, SandDustCloudModel<SandDustCloud>> {
    private static final ResourceLocation CLOUD_TEXTURE = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/entity/dust_cloud.png");
    public CloudRenderer(EntityRendererProvider.Context context) {
        super(context, new SandDustCloudModel<>(context.bakeLayer(FFRml.DUST_CLOUD_LAYER)), 3.9f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SandDustCloud cloud) {
        return CLOUD_TEXTURE;
    }
}
