package net.tabby.florafaunarebalance.datagen.loot;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.item.FFRii;
import org.jetbrains.annotations.NotNull;


public class FFRBlockLootTables extends BlockLoot {
    private static final LootItemCondition.Builder HAS_SILK_TOUCH;
    private static final LootItemCondition.Builder HAS_SHEARS;
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH;
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH;
    private static final float[] BAMBOO_FRUIT_CHANCES;
    private static final float[] BAMBOO_LEAVES_CHUTE_CHANCES;

    public FFRBlockLootTables() {
    }

    @Override
    protected void addTables() {
        dropSelf(FFRib.BAMBOO_PLANKS.get());
        dropSelf(FFRib.BAMBOO_MOSAIC.get());

        dropSelf(FFRib.BAMBOO_LOG.get());
        dropSelf(FFRib.STRIPPED_BAMBOO_LOG.get());
        dropSelf(FFRib.BAMBOO_WOOD.get());
        dropSelf(FFRib.STRIPPED_BAMBOO_WOOD.get());

        add(FFRib.BAMBOO_LEAVES.get(),
                (block) -> createBambooLeavesDrops(FFRib.BAMBOO_LEAVES.get(), Items.CHORUS_FRUIT,  BAMBOO_FRUIT_CHANCES));

        dropSelf(FFRib.SAPPHIRE_BLOCK.get());
        add(FFRib.SAPPHIRE_ORE.get(),
                (block) -> createOreDrop(FFRib.SAPPHIRE_ORE.get(), FFRii.SAPPHIRE.get()));
        add(FFRib.DEEPSLATE_SAPPHIRE_ORE.get(),
                (block) -> createOreDrop(FFRib.DEEPSLATE_SAPPHIRE_ORE.get(), FFRii.SAPPHIRE.get()));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return FFRib.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
    protected static LootTable.Builder createBambooLeavesDrops(Block pLeaves, ItemLike pFruit, float... pChances) {
        return createSilkTouchOrShearsDispatchTable(pLeaves, ((LootPoolSingletonContainer.Builder)applyExplosionCondition(pLeaves, LootItem.lootTableItem(pFruit))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, pChances)))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                        .add(((LootPoolSingletonContainer.Builder)applyExplosionDecay(pLeaves, LootItem.lootTableItem(Items.BAMBOO)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                ).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, BAMBOO_LEAVES_CHUTE_CHANCES))
                        )
                );
    }

    static {
        HAS_SILK_TOUCH = MatchTool.toolMatches(net.minecraft.advancements.critereon.ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
        HAS_SHEARS = MatchTool.toolMatches(net.minecraft.advancements.critereon.ItemPredicate.Builder.item().of(Items.SHEARS));
        HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
        HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
        BAMBOO_LEAVES_CHUTE_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};
        BAMBOO_FRUIT_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    }
}
