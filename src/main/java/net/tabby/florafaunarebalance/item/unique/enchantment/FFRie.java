package net.tabby.florafaunarebalance.item.unique.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.item.unique.ChuteItem;
import net.tabby.florafaunarebalance.item.unique.enchantment.unique.BarrageEnchantment;

public class FFRie {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, FloraFaunaRebalance.MOD_ID);

    public static RegistryObject<Enchantment> BARRAGE = ENCHANTMENTS.register("barrage",
        () -> new BarrageEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.create("barrage", item -> item instanceof ChuteItem), EquipmentSlot.MAINHAND));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
