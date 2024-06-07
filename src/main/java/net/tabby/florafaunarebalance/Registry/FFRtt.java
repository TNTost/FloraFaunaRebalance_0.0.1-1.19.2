package net.tabby.florafaunarebalance.Registry;

import com.lowdragmc.lowdraglib.utils.EnumHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.Registry.pre.PreInitialisation;
import net.tabby.florafaunarebalance.item.FFRii;
import net.tabby.florafaunarebalance.util.FFR.F;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class FFRtt {
    private static boolean isFrozen = false;
    public static final DeferredRegister<Tier> TIER =
            DeferredRegister.create(new ResourceLocation(FloraFaunaRebalance.MOD_ID, "tiers"), FloraFaunaRebalance.MOD_ID);



    public static final Tier COPPER = new TT("copper_tier", List.of(Tiers.STONE), List.of(Tiers.IRON),
                    176, 5.2f, 1.4f, 1, 19, () -> Ingredient.of(Items.COPPER_INGOT)); //# more advanced repair system?
    public static final Tier TANZANITE = new TT("tanzanite_tier", List.of(Tiers.NETHERITE), List.of("mythril"), //# as not to break loading order, this should have the tier-name instead.
                    2791, 11f, 5f, 5, 18, () -> Ingredient.of(PreInitialisation.TANZANITE.get()));
    public static final Tier MYTHRIL = new TT("mythril_tier", List.of("tanzanite"), List.of(),
                    3621, 14f, 6.5f, 6, 23, () -> Ingredient.of(PreInitialisation.MYTHRIL_INGOT.get()));


    public static void register(IEventBus eventBus) {
        if (isFrozen) throw new IllegalStateException("Tool-Tier registry has already been frozen");
        TIER.makeRegistry(RegistryBuilder::new);
        TIER.register(eventBus);
        isFrozen = true;
    }


    public record TT(String name, List<Object> previousTiers, List<Object> nextTiers, int uses, float speed, float damageBonus, int level, int enchantValue, Supplier<Ingredient> repairItem) implements Tier {
        public TT {
            Objects.requireNonNull(name);
            Objects.requireNonNull(previousTiers);
            Objects.requireNonNull(nextTiers);
            Objects.requireNonNull(repairItem);
            TierSortingRegistry.registerTier(this, F.l(name), previousTiers, nextTiers);
        }
        public int getUses() {
            return uses;
        }
        public float getSpeed() {
            return speed;
        }
        public float getAttackDamageBonus() {
            return damageBonus;
        }
        public int getLevel() {
            return level;
        }
        public int getEnchantmentValue() {
            return enchantValue;
        }
        public @NotNull Ingredient getRepairIngredient() {
            return repairItem.get();
        }
    }
}
