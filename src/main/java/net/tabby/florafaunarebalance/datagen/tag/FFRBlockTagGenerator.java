package net.tabby.florafaunarebalance.datagen.tag;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.util.FFRTags;
import org.jetbrains.annotations.Nullable;

public class FFRBlockTagGenerator extends BlockTagsProvider {
    public FFRBlockTagGenerator(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, FloraFaunaRebalance.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(FFRib.BAMBOO_PLANKS.get())
                .add(FFRib.BAMBOO_MOSAIC.get())
                        .add(FFRib.BAMBOO_LOG.get())
                        .add(FFRib.STRIPPED_BAMBOO_LOG.get())
                        .add(FFRib.BAMBOO_WOOD.get())
                        .add(FFRib.STRIPPED_BAMBOO_WOOD.get())

                                .add(FFRib.BUDDING_BAMBOO_LOG.get())
                                .add(FFRib.BUDDING_BAMBOO_WOOD.get());
        tag(BlockTags.PLANKS)
                .add(FFRib.BAMBOO_PLANKS.get())
                .add(FFRib.BAMBOO_MOSAIC.get());

        tag(FFRTags.Blocks.SPLICEABLE_LOGS)
                .add(Blocks.OAK_LOG)
                .add(Blocks.OAK_WOOD)
                .add(Blocks.BIRCH_LOG)
                .add(Blocks.BIRCH_WOOD)
                .add(Blocks.SPRUCE_LOG)
                .add(Blocks.SPRUCE_WOOD)
                .add(Blocks.JUNGLE_LOG)
                .add(Blocks.JUNGLE_WOOD)
                .add(Blocks.ACACIA_LOG)
                .add(Blocks.ACACIA_WOOD)
                .add(Blocks.DARK_OAK_LOG)
                .add(Blocks.DARK_OAK_WOOD)
                .add(Blocks.MANGROVE_LOG)
                .add(Blocks.MANGROVE_WOOD);


        tag(BlockTags.LOGS)
                .add(FFRib.BAMBOO_LOG.get())
                .add(FFRib.STRIPPED_BAMBOO_LOG.get())
                .add(FFRib.BAMBOO_WOOD.get())
                .add(FFRib.STRIPPED_BAMBOO_WOOD.get())
                .add(FFRib.BUDDING_BAMBOO_LOG.get())
                .add(FFRib.BUDDING_BAMBOO_WOOD.get());
        tag(BlockTags.LOGS_THAT_BURN)
                .add(FFRib.BAMBOO_LOG.get())
                .add(FFRib.STRIPPED_BAMBOO_LOG.get())
                .add(FFRib.BAMBOO_WOOD.get())
                .add(FFRib.STRIPPED_BAMBOO_WOOD.get())
                .add(FFRib.BUDDING_BAMBOO_LOG.get())
                .add(FFRib.BUDDING_BAMBOO_WOOD.get());

        tag(FFRTags.Blocks.BAMBOO_LOGS)
                .add(FFRib.BAMBOO_LOG.get())
                .add(FFRib.STRIPPED_BAMBOO_LOG.get())
                .add(FFRib.BAMBOO_WOOD.get())
                .add(FFRib.STRIPPED_BAMBOO_WOOD.get())
                .add(FFRib.BUDDING_BAMBOO_LOG.get())
                .add(FFRib.BUDDING_BAMBOO_WOOD.get());

        tag(BlockTags.LEAVES)
                .add(FFRib.BAMBOO_LEAVES.get());


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
