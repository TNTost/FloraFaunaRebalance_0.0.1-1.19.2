package net.tabby.florafaunarebalance.core.mixins.defaults;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Attributes.class)
public class AttributesMixin {


    @Redirect(method = "<clinit>", at = @At(value = "NEW", target = "net.minecraft.world.entity.ai.attributes.RangedAttribute"))
    private static RangedAttribute ffr$rangedAttributeRedirect(String translationKey, double fallback, double min, double max) {
        double health = 16d;
        if (translationKey.equals("attribute.name.generic.max_health")) {
            System.out.printf("#####################&*=- %s reduced to %s internally as double -=*&#####################%n", translationKey, health); // slowed -> reduced..
            return new RangedAttribute(translationKey, health, min, max);
        } else {
            return new RangedAttribute(translationKey, fallback, min, max);
        }
    }
    //@Inject(method = "register", at = @At("HEAD"), cancellable = true)
    //private static void ffr$slowDefaultMiningSpeed(String str, Attribute atr, CallbackInfoReturnable<Attribute> cir) {
    //    double health = 16;
    //    if (str.equals("generic.max_health")) {
    //        cir.setReturnValue(Registry.register(Registry.ATTRIBUTE, str, new RangedAttribute("attribute.name.generic.max_health", health, 1.0D, 1024.0D)).setSyncable(true));
//
    //    }
    //    //# TODO: implement yat's optimisation.
    //}
}
