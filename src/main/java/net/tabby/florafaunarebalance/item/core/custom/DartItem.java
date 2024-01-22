package net.tabby.florafaunarebalance.item.core.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.tabby.florafaunarebalance.entity.custom.DartProjectileEntity;
import java.util.List;
import java.util.Objects;

public class DartItem extends ArrowItem {
    public final int col;
    public final float damage;
    public final List<MobEffectInstance> effects;

    public DartItem(Properties p_40512_properties, float damage, List<MobEffectInstance> effect, int col) {
        super(p_40512_properties);
        this.damage = damage;
        this.effects = effect;
        this.col = col;
    }
    public AbstractArrow createDart(Level level, ItemStack itemStack, LivingEntity shooter) {
        DartProjectileEntity dart = new DartProjectileEntity(shooter, level, this, col); //# 'this'  is a life-saver
        dart.setBaseDamage(this.damage);
        dart.setEffectsFromList(effects);
        dart.setEffectsFromRegistryName(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(itemStack.getItem())).getPath());
        return dart;
    }
    @Override
    public boolean isInfinite(ItemStack stack, ItemStack chute, Player player) {
        int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, chute);
        return enchant > 0 && this.getClass() == DartItem.class;
    }
}
