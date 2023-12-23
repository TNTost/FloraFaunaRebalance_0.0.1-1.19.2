package net.tabby.florafaunarebalance.item.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.EntityHitResult;

public class TippedDartItem extends DartItem{
public final MobEffect EFFECT;
    public TippedDartItem(Properties p_40512_properties, float damage, MobEffect effect) {
        super(p_40512_properties, damage);
         this.EFFECT = effect;
    }
    protected void OnHitEntity(EntityHitResult entityHitResult){
        Entity target = entityHitResult.getEntity();
        if (target instanceof LivingEntity targetEntity) {
            targetEntity.addEffect(new MobEffectInstance(this.EFFECT, 3, 3));
        }
    }
}
