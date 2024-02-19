package net.tabby.florafaunarebalance.item.core.unique;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tabby.florafaunarebalance.entity.unique.DartProjectileEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class DartItem extends ArrowItem {
    //public final int col;
    public final float damage;
    public final Collection<MobEffectInstance> effects;

    public DartItem(Properties properties, float damage, List<MobEffectInstance> effects) {
        super(properties);
        this.damage = damage; //# initialise damage / effectList.
        this.effects = effects;
    }

    public AbstractArrow createDart(Level level, ItemStack itemStack, LivingEntity shooter) {
        DartProjectileEntity dart = new DartProjectileEntity(shooter, level, this); //# 'this'  is a life-saver
        dart.setBaseDamage(this.damage);
        dart.setEffectsFromNBT(itemStack); //# gets called when arrow shot, list wrongly initialised when done elsewhere.
        return dart;
    }

    @Override
    public void fillItemCategory(@NotNull CreativeModeTab tab, @NotNull NonNullList<ItemStack> itemList) { //# generate default nbt alongside item...
        if (this.allowedIn(tab)) {
            ItemStack dart = new ItemStack(this); //# item to itemStack...
            itemList.add(setDefaultTag(dart)); //# splitting this into a method call makes it more readable.
        }
    }
    public ItemStack setDefaultTag(ItemStack dart) {
        if (this.effects != null) { //# itemStack created before check guarantees non-effect darts get registered.
            ListTag nbt = new ListTag();
            for (MobEffectInstance entry : effects) { //# getOrCreateTag gets tag or when null creates NEW Tag...
                nbt.add(entry.save(new CompoundTag()));
            }
            dart.getOrCreateTag().put("CustomPotionEffects", nbt);
        }
        return dart;
    }
}
