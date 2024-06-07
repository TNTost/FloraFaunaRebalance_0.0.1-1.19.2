package net.tabby.florafaunarebalance.core.mixins.defaults;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Attributes.class)
public class AttributesMixin {
    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void ffr$slowDefaultMiningSpeed(String str, Attribute atr, CallbackInfoReturnable<Attribute> cir) {
        double health = 16.5;
        if (str.equals("generic.max_health")) {
            cir.setReturnValue(Registry.register(Registry.ATTRIBUTE, str, new RangedAttribute("attribute.name.generic.max_health", health, 1.0D, 1024.0D)).setSyncable(true));
            System.out.printf("#####################&*=- %s reduced to %s internally as double -=*&#####################%n", str, health); // slowed -> reduced..
        }
    }
}
