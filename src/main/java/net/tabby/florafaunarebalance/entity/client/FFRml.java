package net.tabby.florafaunarebalance.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;

public class FFRml {
    public static final ModelLayerLocation DUST_CLOUD_LAYER = new ModelLayerLocation(
            new ResourceLocation(FloraFaunaRebalance.MOD_ID, "dust_cloud_layer_main"), "main");

    public static final ModelLayerLocation DUCK_LAYER = new ModelLayerLocation(
            new ResourceLocation(FloraFaunaRebalance.MOD_ID, "duck_layer"), "main");
}
