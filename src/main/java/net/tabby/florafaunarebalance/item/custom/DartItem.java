package net.tabby.florafaunarebalance.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class DartItem extends ArrowItem {
    public final float damage;
    public DartItem(Properties p_40512_properties, float damage) {
        super(p_40512_properties);
        this.damage = damage;
    }
    @Override
    public AbstractArrow createArrow(Level p_40513_level, ItemStack p_40514_, LivingEntity p_40515_shooter) {
        Arrow arrow = new Arrow(p_40513_level, p_40515_shooter);
        arrow.setBaseDamage(this.damage);
        return arrow;
    }
    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == DartItem.class;
    }
}
