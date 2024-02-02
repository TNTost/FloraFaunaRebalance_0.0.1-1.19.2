package net.tabby.florafaunarebalance.client.renderer.entity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.client.renderer.entity.core.DartVariants;
import net.tabby.florafaunarebalance.entity.custom.DartProjectileEntity;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class DartProjectileRenderer extends ArrowRenderer<DartProjectileEntity> {
    private static final ResourceLocation DART_TEXTURE = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/entity/projectile/dart.png");
    private static final ResourceLocation POISON_DART_TEXTURE = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/entity/projectile/poison.png");
    private static final ResourceLocation DART_OF_HEALING_TEXTURE = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/entity/projectile/healing.png");
    //# texture locations.^^

    public DartProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(DartProjectileEntity dartEntity) {
        return switch (dartEntity.getVariant()) { //# you can return a switch statement...
            case "untipped_dart" -> DART_TEXTURE;
            case "poison_dart" -> POISON_DART_TEXTURE;
            case "dart_of_healing" -> DART_OF_HEALING_TEXTURE; //# choose between diff dart textures.
            default -> DART_TEXTURE;
        };
    }
}