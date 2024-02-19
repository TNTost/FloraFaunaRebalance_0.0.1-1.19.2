package net.tabby.florafaunarebalance.item.core.custom.enchantment.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class BarrageEnchantment extends Enchantment {
    public BarrageEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }
    @Override
    public int getMaxLevel() {
        return 3;
    }


    @Override
    public boolean allowedInCreativeTab(Item book, CreativeModeTab tab) {
        return super.allowedInCreativeTab(book, tab);
    }
}
