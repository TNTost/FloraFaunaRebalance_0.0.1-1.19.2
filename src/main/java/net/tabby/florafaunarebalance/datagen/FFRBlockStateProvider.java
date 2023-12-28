package net.tabby.florafaunarebalance.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;

public class FFRBlockStateProvider extends BlockStateProvider {

    public FFRBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, FloraFaunaRebalance.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockSameItem(FFRib.BAMBOO_PLANKS);
        blockSameItem(FFRib.BAMBOO_MOSAIC);

        blockSameItem(FFRib.SAPPHIRE_BLOCK);
        blockSameItem(FFRib.SAPPHIRE_ORE);
        blockSameItem(FFRib.DEEPSLATE_SAPPHIRE_ORE);
    }

    private void blockSameItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void simpleBlockWithItem(Block pBlock, ModelFile pModel) {
        simpleBlock(pBlock, pModel);
        simpleBlockItem(pBlock, pModel);
    }
}
