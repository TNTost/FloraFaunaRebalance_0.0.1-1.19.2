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
                        .add(FFRib.BUDDING_OAK_LOG.get().asItem());

        tag(ItemTags.OAK_LOGS)
                .add(FFRib.BUDDING_OAK_LOG.get().asItem());


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
                        .add(FFRib.BUDDING_OAK_LOG.get().asItem());

        tag(ItemTags.LEAVES)
                .add(FFRib.BAMBOO_LEAVES.get().asItem());

        tag(FFRTags.Items.DART_TAG)
                .add(FFRii.UNTIPPED_DART.get())
                .add(FFRii.POISON_DART.get());
    }
}
