package net.tabby.florafaunarebalance.datagen.tag;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.item.FFRii;
import net.tabby.florafaunarebalance.util.FFRTags;
import org.jetbrains.annotations.Nullable;

public class FFRItemTagGenerator extends ItemTagsProvider {
    public FFRItemTagGenerator(DataGenerator pGenerator, BlockTagsProvider pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pProvider, FloraFaunaRebalance.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ItemTags.PLANKS)
                .add(FFRib.BAMBOO_PLANKS.get().asItem());
        tag(ItemTags.LOGS)
                .add(FFRib.BAMBOO_LOG.get().asItem())
                .add(FFRib.STRIPPED_BAMBOO_LOG.get().asItem())
                .add(FFRib.BAMBOO_WOOD.get().asItem())
                .add(FFRib.STRIPPED_BAMBOO_WOOD.get().asItem())
                .add(FFRib.BUDDING_BAMBOO_LOG.get().asItem())
                .add(FFRib.BUDDING_BAMBOO_WOOD.get().asItem())
                        .add(FFRib.BUDDING_OAK_LOG.get().asItem())
                        .add(FFRib.BUDDING_OAK_WOOD.get().asItem())
                        .add(FFRib.BUDDING_BIRCH_LOG.get().asItem())
                        .add(FFRib.BUDDING_BIRCH_WOOD.get().asItem())
                        .add(FFRib.BUDDING_SPRUCE_LOG.get().asItem())
                        .add(FFRib.BUDDING_SPRUCE_WOOD.get().asItem())
                        .add(FFRib.BUDDING_JUNGLE_LOG.get().asItem())
                        .add(FFRib.BUDDING_JUNGLE_WOOD.get().asItem())
                        .add(FFRib.BUDDING_ACACIA_LOG.get().asItem())
                        .add(FFRib.BUDDING_ACACIA_WOOD.get().asItem())
                        .add(FFRib.BUDDING_DARK_OAK_LOG.get().asItem())
                        .add(FFRib.BUDDING_DARK_OAK_WOOD.get().asItem())
                        .add(FFRib.BUDDING_MANGROVE_LOG.get().asItem())
                        .add(FFRib.BUDDING_MANGROVE_WOOD.get().asItem());

        tag(ItemTags.OAK_LOGS)
                .add(FFRib.BUDDING_OAK_LOG.get().asItem())
                .add(FFRib.BUDDING_OAK_WOOD.get().asItem());
        tag(ItemTags.BIRCH_LOGS)
                .add(FFRib.BUDDING_BIRCH_LOG.get().asItem())
                .add(FFRib.BUDDING_BIRCH_WOOD.get().asItem());
        tag(ItemTags.SPRUCE_LOGS)
                .add(FFRib.BUDDING_SPRUCE_LOG.get().asItem())
                .add(FFRib.BUDDING_SPRUCE_WOOD.get().asItem());
        tag(ItemTags.JUNGLE_LOGS)
                .add(FFRib.BUDDING_JUNGLE_LOG.get().asItem())
                .add(FFRib.BUDDING_JUNGLE_WOOD.get().asItem());
        tag(ItemTags.ACACIA_LOGS)
                .add(FFRib.BUDDING_ACACIA_LOG.get().asItem())
                .add(FFRib.BUDDING_ACACIA_WOOD.get().asItem());
        tag(ItemTags.DARK_OAK_LOGS)
                .add(FFRib.BUDDING_DARK_OAK_LOG.get().asItem())
                .add(FFRib.BUDDING_DARK_OAK_WOOD.get().asItem());
        tag(ItemTags.MANGROVE_LOGS)
                .add(FFRib.BUDDING_MANGROVE_LOG.get().asItem())
                .add(FFRib.BUDDING_MANGROVE_WOOD.get().asItem());


        tag(FFRTags.Items.BAMBOO_LOGS)
                .add(FFRib.BAMBOO_LOG.get().asItem())
                .add(FFRib.STRIPPED_BAMBOO_LOG.get().asItem())
                .add(FFRib.BAMBOO_WOOD.get().asItem())
                .add(FFRib.STRIPPED_BAMBOO_WOOD.get().asItem())
                .add(FFRib.BUDDING_BAMBOO_LOG.get().asItem())
                .add(FFRib.BUDDING_BAMBOO_WOOD.get().asItem());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(FFRib.BAMBOO_LOG.get().asItem())
                .add(FFRib.STRIPPED_BAMBOO_LOG.get().asItem())
                .add(FFRib.BAMBOO_WOOD.get().asItem())
                .add(FFRib.STRIPPED_BAMBOO_WOOD.get().asItem())
                .add(FFRib.BUDDING_BAMBOO_LOG.get().asItem())
                .add(FFRib.BUDDING_BAMBOO_WOOD.get().asItem())
                        .add(FFRib.BUDDING_OAK_LOG.get().asItem())
                        .add(FFRib.BUDDING_OAK_WOOD.get().asItem())
                        .add(FFRib.BUDDING_BIRCH_LOG.get().asItem())
                        .add(FFRib.BUDDING_BIRCH_WOOD.get().asItem())
                        .add(FFRib.BUDDING_SPRUCE_LOG.get().asItem())
                        .add(FFRib.BUDDING_SPRUCE_WOOD.get().asItem())
                        .add(FFRib.BUDDING_JUNGLE_LOG.get().asItem())
                        .add(FFRib.BUDDING_JUNGLE_WOOD.get().asItem())
                        .add(FFRib.BUDDING_ACACIA_LOG.get().asItem())
                        .add(FFRib.BUDDING_ACACIA_WOOD.get().asItem())
                        .add(FFRib.BUDDING_DARK_OAK_LOG.get().asItem())
                        .add(FFRib.BUDDING_DARK_OAK_WOOD.get().asItem())
                        .add(FFRib.BUDDING_MANGROVE_LOG.get().asItem())
                        .add(FFRib.BUDDING_MANGROVE_WOOD.get().asItem());

        tag(ItemTags.LEAVES)
                .add(FFRib.BAMBOO_LEAVES.get().asItem());

        tag(FFRTags.Items.DART_TAG)
                .add(FFRii.DART.get())
                .add(FFRii.POISON_DART.get())
                .add(FFRii.DART_OF_HEALING.get());
    }
}
