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
        double health = translationKey.equals("attribute.name.generic.max_health") ? 16d : fallback;
        System.out.printf("#####################&*=- %s reduced to %s internally as double -=*&#####################%n", translationKey, health); // slowed -> reduced..
        return new RangedAttribute(translationKey, health, min, max);
    }
}