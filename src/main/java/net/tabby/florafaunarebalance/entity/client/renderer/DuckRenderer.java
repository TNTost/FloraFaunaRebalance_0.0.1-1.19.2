package net.tabby.florafaunarebalance.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.entity.client.FFRml;
import net.tabby.florafaunarebalance.entity.client.models.DuckModel;
import net.tabby.florafaunarebalance.entity.unique.DuckEntity;
import org.jetbrains.annotations.NotNull;

public class DuckRenderer extends MobRenderer<DuckEntity, DuckModel<DuckEntity>> {
    private static final ResourceLocation DUCKLING = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/entity/duckling.png");
    private static final ResourceLocation DUCK_WHITE = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/entity/duck_white.png");

    public DuckRenderer(EntityRendererProvider.Context context) {
        super(context, new DuckModel<>(context.bakeLayer(FFRml.DUCK_LAYER)), 0.9f);
    }

    @Override
    public ResourceLocation getTextureLocation(DuckEntity duck) {
        ResourceLocation loc = duck.isBaby() ? DUCKLING : DUCK_WHITE;
        return loc;
    }

    @Override
    public void render(@NotNull DuckEntity duck, float p_115456_, float subTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int light) {
        if (duck.isBaby()) {
            poseStack.scale(0.50f, 0.50f, 0.50f);
        }
        super.render(duck, p_115456_, subTicks, poseStack, source, light);
    }
}
