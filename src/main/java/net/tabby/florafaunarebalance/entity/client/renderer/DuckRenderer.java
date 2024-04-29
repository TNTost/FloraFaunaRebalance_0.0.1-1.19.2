package net.tabby.florafaunarebalance.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.entity.FFRenty;
import net.tabby.florafaunarebalance.entity.client.FFRml;
import net.tabby.florafaunarebalance.entity.client.models.DuckModel;
import net.tabby.florafaunarebalance.entity.unique.DuckEntity;
import org.jetbrains.annotations.NotNull;

public class DuckRenderer extends MobRenderer<DuckEntity, DuckModel<DuckEntity>> {
    private static final ResourceLocation DUCKLING = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/entity/duckling.png");
    private static final ResourceLocation DUCK_WHITE = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/entity/duck_white.png");

    Vec3 halfVec = new Vec3(FFRenty.duckWidth, FFRenty.duckHeight * 2, FFRenty.duckWidth);
    public DuckRenderer(EntityRendererProvider.Context context) {
        super(context, new DuckModel<>(context.bakeLayer(FFRml.DUCK_LAYER)), 0.9f);
    }

    @Override
    public ResourceLocation getTextureLocation(DuckEntity duck) {
        ResourceLocation loc = duck.isBaby() ? DUCKLING : DUCK_WHITE;
        return loc;
    }

    @Override
    public void render(@NotNull DuckEntity duck, float yaw, float subTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int light) {
        if (duck.isBaby()) {
            float scale = duck.getChickAge() < -70 ? 1.4f : 0.5f; //# always less than 7, never less than -70.
            poseStack.scale(scale, scale, scale);
            Vec3 duckPos = new Vec3(duck.getX(), duck.getY(), duck.getZ());
            //duck.setBoundingBox(new AABB(halfVec.scale(-scale).add(duckPos), halfVec.scale(scale).add(duckPos).subtract(0, FFRenty.duckHeight * 2, 0)));

        }
        super.render(duck, yaw, subTicks, poseStack, source, light);
    }
}
