package net.tabby.florafaunarebalance.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.util.FFR.F;

public class FFRml {
    public static final ModelLayerLocation SLING_LAYER = new ModelLayerLocation(F.lr("sling_layer"), "main");
    public static final ModelLayerLocation DUST_CLOUD_LAYER = new ModelLayerLocation(
            new ResourceLocation(FloraFaunaRebalance.MOD_ID, "dust_cloud_layer_main"), "main");

    public static final ModelLayerLocation DUCK_LAYER = new ModelLayerLocation(
            new ResourceLocation(FloraFaunaRebalance.MOD_ID, "duck_layer"), "main");

    public static final ModelLayerLocation SKEETER_LAYER = new ModelLayerLocation(
            new ResourceLocation(FloraFaunaRebalance.MOD_ID, "water_skeeter_layer"), "main");
}
