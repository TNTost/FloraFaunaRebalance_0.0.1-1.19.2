package net.tabby.florafaunarebalance.item.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.tabby.florafaunarebalance.entity.custom.DartProjectileEntity;
import net.tabby.florafaunarebalance.item.ModItemsINIT;

import javax.annotation.Nullable;

public class DartItem extends ArrowItem {
    public final float damage;
    public final MobEffect effect;
    public DartItem(Properties p_40512_properties, float damage, @Nullable MobEffect effect) {
        super(p_40512_properties);
        this.damage = damage;
        this.effect = effect;
    }
    //CustomArrow arrow = new CustomArrow(shooter, level, ModItems.CUSTOM_ARROW.get());
    @Override
    public AbstractArrow createArrow(Level p_40513_level, ItemStack p_40514_, LivingEntity p_40515_shooter) {
        DartProjectileEntity arrow = new DartProjectileEntity(p_40515_shooter, p_40513_level, ModItemsINIT.UNTIPPED_DART.get(), effect);
        arrow.setBaseDamage(this.damage);
        return arrow;
    }
    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
        return enchant > 0 && this.getClass() == DartItem.class;
    }
}
