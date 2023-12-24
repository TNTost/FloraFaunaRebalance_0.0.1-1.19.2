package net.tabby.florafaunarebalance.entity.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.tabby.florafaunarebalance.entity.ModEntityType;
import net.tabby.florafaunarebalance.item.ModItemsINIT;
import org.jetbrains.annotations.NotNull;

public class DartProjectileEntity extends AbstractArrow {

    private final Item referenceItem;
    private final MobEffect effect;
        public DartProjectileEntity(EntityType<? extends AbstractArrow> p_36721_type, Level p_36722_level) {
        super(p_36721_type, p_36722_level);
        this.referenceItem = ModItemsINIT.UNTIPPED_DART.get();
        this.effect = null;
    }
    public DartProjectileEntity(LivingEntity p_36718_shooter, Level p_36719_level, Item referenceItem, MobEffect effect) {
        super(ModEntityType.DART.get(), p_36718_shooter, p_36719_level);
        this.referenceItem = referenceItem;
        this.effect = effect;
    }

    @Override
    public @NotNull ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }
    protected void onHitEntity(@NotNull EntityHitResult entityHitResult){
            super.onHitEntity(entityHitResult);
            Entity target = entityHitResult.getEntity();
            if (target instanceof LivingEntity targetEntity) {
                if (this.effect != null) {
                    targetEntity.addEffect(new MobEffectInstance(this.effect, 60, 1));
            }
        }
    }
}
