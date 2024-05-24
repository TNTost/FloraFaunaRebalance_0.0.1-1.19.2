package net.tabby.florafaunarebalance.core.mixins;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.tabby.florafaunarebalance.world.generation.ore.FFRop;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeDefaultFeatures.class)
public class DefaultFeaturesMixin {

    @Inject(method = "addDefaultOres", at = @At("TAIL"))
    private static void ffr$addCustomOres(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FFRop.ORE_PYRITE.getHolder().get());
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FFRop.ORE_SAPHYRE.getHolder().get());
    }
}
