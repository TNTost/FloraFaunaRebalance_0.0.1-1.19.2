package net.tabby.florafaunarebalance.entity.custom;

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

import java.util.*;

public class DartProjectileEntity extends AbstractArrow {


    private final int col;
    private final Item referenceItem;
    private final Set<MobEffectInstance> effects;
    public DartProjectileEntity(EntityType<? extends AbstractArrow> p_36721_type, Level p_36722_level) {
        super(p_36721_type, p_36722_level);
        this.referenceItem = ModItemsINIT.UNTIPPED_DART.get();
        this.effects = new HashSet<>();
        this.col = -1;
    }
    public DartProjectileEntity(LivingEntity p_36718_shooter, Level p_36719_level, Item referenceItem, int col) {
        super(ModEntityType.DART.get(), p_36718_shooter, p_36719_level);
        this.referenceItem = referenceItem;
        this.effects = new HashSet<>();
        this.col = col;
    }

    public void setEffectsFromList (List<MobEffectInstance> pList) {
        List<MobEffectInstance> listCopy = List.copyOf(pList);
        if (!listCopy.isEmpty()) {
           for (MobEffectInstance entry : listCopy) {
               this.effects.add(new MobEffectInstance(entry));
           }
        }
    }
   protected void onHitEntity (@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);

        Entity target = entityHitResult.getEntity();
        if (target instanceof LivingEntity livingEntity) {
            if(!this.effects.isEmpty()) {

                for (MobEffectInstance entry : this.effects) {
                    livingEntity.addEffect(entry);
                }
            }
        }
   }
   /* public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.makeParticle(1);
                }
            } else {
                this.makeParticle(2);
            }
        } else if (this.inGround && this.inGroundTime != 0 && this.effect.isEmpty() && this.inGroundTime >= 600) {
            this.level.broadcastEntityEvent(this, (byte) 0);
            this.effect.clear();
        }
    }
    private void makeParticle(int p_36877_Amount) {
        int colour = this.col;
        if (colour != -1 && p_36877_Amount > 0) {
            double ColP1 = (double) (colour >> 16 & 255) / 255;
            double ColP2 = (double) (colour >> 8 & 255) / 255;
            double ColP3 = (double) (colour & 255) / 255;

            for (int i = 0; i < p_36877_Amount; i++) {
                this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX( 0.5), this.getRandomY(), this.getRandomZ( 0.5), ColP1, ColP2, ColP3);
            }
        }
    }*/


    @Override
    public @NotNull ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }
}
