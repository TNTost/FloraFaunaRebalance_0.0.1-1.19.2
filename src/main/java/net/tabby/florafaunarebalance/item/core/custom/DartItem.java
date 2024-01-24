package net.tabby.florafaunarebalance.item.core.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.tabby.florafaunarebalance.entity.custom.DartProjectileEntity;

import java.util.Collection;
import java.util.List;

import static net.minecraft.world.item.alchemy.PotionUtils.setCustomEffects;

public class DartItem extends ArrowItem {
    //public final int col;
    public final float damage;
    public final Collection<MobEffectInstance> effects;

    public DartItem(Properties properties, float damage, List<MobEffectInstance> effects) {
        super(properties);
        this.damage = damage;
        this.effects = effects;
    }
    public ItemStack getDefaultInstance() {
        ItemStack dart = new ItemStack(this); //# creates new itemStack &sets NBT tag...
        return PotionUtils.setCustomEffects(dart, effects); //# missing "PotionUtils." caused bug, E.
    }

    public AbstractArrow createDart(Level level, ItemStack itemStack, LivingEntity shooter) {
        DartProjectileEntity dart = new DartProjectileEntity(shooter, level, this); //# 'this'  is a life-saver
        dart.setBaseDamage(this.damage);
        setCustomEffects(itemStack, effects); //# fixes the non nbt, but in a weird way.TEMP
        dart.setEffectsFromNBT(itemStack); //# gets called when arrow shot, list wrongly initialised when done elsewhere.
        return dart;
    }
    @Override
    public boolean isInfinite(ItemStack stack, ItemStack chute, Player player) {
        int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, chute);
        return enchant > 0 && this.getClass() == DartItem.class; //# actually pretty useless
    }

}
