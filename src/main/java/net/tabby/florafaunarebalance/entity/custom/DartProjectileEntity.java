package net.tabby.florafaunarebalance.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tabby.florafaunarebalance.entity.ModEntityType;
import net.tabby.florafaunarebalance.item.ModItemsINIT;

public class DartProjectileEntity extends AbstractArrow {
    private final Item referenceItem;
    public DartProjectileEntity(EntityType<? extends AbstractArrow> p_36721_type, Level p_36722_level) {
        super(p_36721_type, p_36722_level);
        this.referenceItem = ModItemsINIT.DART.get();
    }

    public DartProjectileEntity(LivingEntity p_36718_shooter, Level p_36719_level, Item referenceItem) {
        super(ModEntityType.DART.get(), p_36718_shooter, p_36719_level);
        this.referenceItem = referenceItem;
    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }
}
