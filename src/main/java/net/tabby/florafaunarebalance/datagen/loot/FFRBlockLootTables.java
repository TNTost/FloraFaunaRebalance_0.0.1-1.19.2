package net.tabby.florafaunarebalance.datagen.loot;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.item.FFRii;
import org.jetbrains.annotations.NotNull;



public class FFRBlockLootTables extends BlockLoot {
    public FFRBlockLootTables() {
    }

    @Override
    protected void addTables() {
        dropSelf(FFRib.BAMBOO_PLANKS.get());
        dropSelf(FFRib.BAMBOO_MOSAIC.get());

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
}
