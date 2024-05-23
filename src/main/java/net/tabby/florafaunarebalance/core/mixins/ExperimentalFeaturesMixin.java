package net.tabby.florafaunarebalance.core.mixins;

import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PrimaryLevelData.class)
public class ExperimentalFeaturesMixin {

    @Inject(method = "hasConfirmedExperimentalWarning", at = @At("HEAD"), cancellable = true)
    private void ffr$disableExperimentalSettingsWarning(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
