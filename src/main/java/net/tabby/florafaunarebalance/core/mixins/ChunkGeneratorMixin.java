package net.tabby.florafaunarebalance.core.mixins;

import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.tabby.florafaunarebalance.world.generation.WorldExtender;
import net.tabby.florafaunarebalance.world.generation.ore.unique.replacement.OrePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {

    @Unique
    WorldExtender eX = new WorldExtender();

    @Inject(method = "applyBiomeDecoration", at = @At("TAIL"))
    private void ffr$applyBiomeDecorations(WorldGenLevel level, ChunkAccess chunk, StructureManager struct, CallbackInfo ci) {
        eX.bedrockPlus8(level, chunk);


        OrePlacer orePlacer = new OrePlacer(); //# ore stuff
        orePlacer.replaceOres(chunk, level);
        orePlacer.placeVeins(chunk, level);
    }
}