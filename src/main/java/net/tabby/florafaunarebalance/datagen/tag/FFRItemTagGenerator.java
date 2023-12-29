package net.tabby.florafaunarebalance.datagen.tag;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import org.jetbrains.annotations.Nullable;

public class FFRItemTagGenerator extends ItemTagsProvider {
    public FFRItemTagGenerator(DataGenerator pGenerator, BlockTagsProvider pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pProvider, FloraFaunaRebalance.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ItemTags.PLANKS)
                .add(FFRib.BAMBOO_PLANKS.get().asItem());
    }
}
