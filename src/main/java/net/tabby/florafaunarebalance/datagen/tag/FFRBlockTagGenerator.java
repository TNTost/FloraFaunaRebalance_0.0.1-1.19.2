package net.tabby.florafaunarebalance.datagen.tag;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import org.jetbrains.annotations.Nullable;

public class FFRBlockTagGenerator extends BlockTagsProvider {
    public FFRBlockTagGenerator(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, FloraFaunaRebalance.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(FFRib.BAMBOO_PLANKS.get())
                .add(FFRib.BAMBOO_MOSAIC.get());
        tag(BlockTags.PLANKS)
                .add(FFRib.BAMBOO_PLANKS.get())
                .add(FFRib.BAMBOO_MOSAIC.get());


        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(FFRib.SAPPHIRE_ORE.get())
                .add(FFRib.DEEPSLATE_SAPPHIRE_ORE.get())
                .add(FFRib.SAPPHIRE_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(FFRib.SAPPHIRE_ORE.get())
                .add(FFRib.DEEPSLATE_SAPPHIRE_ORE.get())
                .add(FFRib.SAPPHIRE_BLOCK.get());
    }
}
