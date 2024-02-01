package net.tabby.florafaunarebalance.client.renderer.entity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.entity.custom.DartProjectileEntity;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class DartProjectileRenderer extends ArrowRenderer<DartProjectileEntity> {
    private static final ResourceLocation DART_TEXTURE = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/entity/projectile/dart.png");
    //# texture location.^^

    public DartProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(DartProjectileEntity dartProjectileEntity) {
        return DART_TEXTURE; //# decide here which texture to use...
    }
}