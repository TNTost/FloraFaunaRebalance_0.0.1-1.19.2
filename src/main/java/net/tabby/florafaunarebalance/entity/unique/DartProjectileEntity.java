package net.tabby.florafaunarebalance.entity.unique;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.tabby.florafaunarebalance.entity.FFRet;
import net.tabby.florafaunarebalance.item.core.unique.DartItem;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static net.tabby.florafaunarebalance.util.all.FFRUtil.getRgStr;

public class DartProjectileEntity extends AbstractArrow { //# implements DartVariants
    private static final EntityDataAccessor<String> DATA_ID_VARIANT;
    private final Item referenceItem;
    private final Set<MobEffectInstance> effects;


    public DartProjectileEntity(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.referenceItem = null;
        effects = new HashSet<>();
    }
    public DartProjectileEntity(LivingEntity shooter, Level level, Item ref) {
        super(FFRet.DART.get(), shooter, level);
        this.referenceItem = ref;
        setDartVariant(ref);
        effects = new HashSet<>(); //# creates new HashSet foreach DartProjectile.
    }
    protected void defineSynchedData() {
        super.defineSynchedData(); //# calling super very important &otherwise causes crash...
        this.entityData.define(DATA_ID_VARIANT, Variant.UNTIPPED.str);
    }


    public void setDartVariant(Item ref) {
        System.out.println(Variant.byStr(getRgStr(ref))); //# magic solution, sets entityData when entity created... YEET.
        this.entityData.set(DATA_ID_VARIANT, Variant.byStr(getRgStr(ref)).str);
    }
    public void setDartVariant(Variant var) {
        this.entityData.set(DATA_ID_VARIANT, var.str);
    }
    public Variant getDartVariant() {
        return Variant.byStr(this.entityData.get(DATA_ID_VARIANT));
    }



    public void setEffectsFromNBT(ItemStack dart) {
        for (MobEffectInstance entry : PotionUtils.getCustomEffects(dart)) { //# get effect as list.
            effects.add(new MobEffectInstance(entry)); //# new keyword important to not cause hard-soft copy issues...
        }
    }
    protected void doPostHurtEffects(@NotNull LivingEntity entity) {
        entity.invulnerableTime = 1; //# YUSHHHH..
        for (MobEffectInstance effect : effects) {
            entity.addEffect(effect, this.getEffectSource()); //# iterate through list &add to entity.
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
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("Type", this.getDartVariant().getStr());
    }
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Type", 8)) {
            this.setDartVariant(Variant.byStr(tag.getString("Type")));
        }
    }
    public enum Variant { //# .ordinal() can be used to get [i] of values()...
        UNTIPPED("untipped_dart"),
        POISON("poison_dart"),
        HEALING("dart_of_healing");

        private final String str;

        Variant(String name) {
            this.str = name;
        }
        public String getStr() {
            return this.str;
        }
        public static Variant byStr(String str) {
            Variant[] var = values();
            for (Variant entry : var) {
                if (entry.getStr().equals(str)) {
                    return entry;
                }
            }
            return var[0];
        }
    }
    static {
        DATA_ID_VARIANT = SynchedEntityData.defineId(DartProjectileEntity.class, EntityDataSerializers.STRING);
    }
    @Override
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.MANGROVE_ROOTS_HIT;
    }
    @Override
    public @NotNull ItemStack getPickupItem() { //# doesnt return nbt item...
        return ((DartItem) this.referenceItem).setDefaultTag(new ItemStack(this.referenceItem)); //# cast item to DartItem, then use method to set effect tag for pickup...
    }
    //# TODO: OVERRIDE sound effects here...
    //oo pretti color :3.
}
