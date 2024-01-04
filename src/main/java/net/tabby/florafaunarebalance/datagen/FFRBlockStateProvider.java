package net.tabby.florafaunarebalance.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;

public class FFRBlockStateProvider extends BlockStateProvider {

    public FFRBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, FloraFaunaRebalance.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(FFRib.BAMBOO_PLANKS);
        simpleBlockWithItem(FFRib.BAMBOO_MOSAIC);

        logWithItem(FFRib.BAMBOO_LOG);
        axisBlock(FFRib.BAMBOO_WOOD.get(), blockTexture(FFRib.BAMBOO_LOG.get()), blockTexture(FFRib.BAMBOO_LOG.get()));
            simpleBlockItem(FFRib.BAMBOO_WOOD.get(), cubeColumnOffset(FFRib.BAMBOO_WOOD.get(), FFRib.BAMBOO_LOG.get()));
        logWithItem(FFRib.STRIPPED_BAMBOO_LOG);
        axisBlock(FFRib.STRIPPED_BAMBOO_WOOD.get(), blockTexture(FFRib.STRIPPED_BAMBOO_LOG.get()), blockTexture(FFRib.STRIPPED_BAMBOO_LOG.get()));
            simpleBlockItem(FFRib.STRIPPED_BAMBOO_WOOD.get(), cubeColumnOffset(FFRib.STRIPPED_BAMBOO_WOOD.get(), FFRib.STRIPPED_BAMBOO_LOG.get()));
        simpleBlockWithRenderType(FFRib.BAMBOO_LEAVES, "cutout");

        logWithItem(FFRib.BUDDING_BAMBOO_LOG);
        axisBlock(FFRib.BUDDING_BAMBOO_WOOD.get(), blockTexture(FFRib.BUDDING_BAMBOO_LOG.get()), blockTexture(FFRib.BUDDING_BAMBOO_LOG.get()));
            simpleBlockItem(FFRib.BUDDING_BAMBOO_WOOD.get(), cubeColumnOffset(FFRib.BUDDING_BAMBOO_WOOD.get(), FFRib.BUDDING_BAMBOO_LOG.get()));

        simpleBlockWithItem(FFRib.SAPPHIRE_BLOCK);
        simpleBlockWithItem(FFRib.SAPPHIRE_ORE);
        simpleBlockWithItem(FFRib.DEEPSLATE_SAPPHIRE_ORE);
    }

    public void logWithItem (RegistryObject<RotatedPillarBlock> pLog) {
        axisBlock(pLog.get(), this.blockTexture(pLog.get()), this.extend(this.blockTexture(pLog.get()), "_top"));
        simpleBlockItem(pLog.get(), cubeColumn(pLog.get()));
    }
    public void simpleBlockWithItem(RegistryObject<Block> pBlock) {
        simpleBlock(pBlock.get(), cubeAll(pBlock.get()));
        simpleBlockItem(pBlock.get(), cubeAll(pBlock.get()));
    }


    public void simpleBlockWithRenderType (RegistryObject<Block> pBlock, String renderType) {
        simpleBlockWithRenderType(pBlock.get(), this.blockTexture(pBlock.get()), renderType);
        simpleBlockItem(pBlock.get(), cubeAll(pBlock.get()));
    }
    public void simpleBlockWithRenderType (Block pBlock, ResourceLocation pTexture, String renderType) {
        simpleBlock(pBlock, this.models().cubeAll(this.name(pBlock), pTexture).renderType(renderType));
    }



    public ModelFile cubeColumnOffset(Block pBlock, Block pTextureBlock) {
        return models().cubeColumn(name(pBlock), blockTexture(pTextureBlock), (blockTexture(pTextureBlock)));
    }
    public ModelFile cubeColumn (Block pBlock) {
        return models().cubeColumn(name(pBlock), blockTexture(pBlock), extend(blockTexture(pBlock), "_top"));
    }






    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
    private String name(Block block) {
        return this.key(block).getPath();
    }
    public ResourceLocation blockTexture(Block block) {
        ResourceLocation name = this.key(block);
        return new ResourceLocation(name.getNamespace(), "block/" + name.getPath());
    }
    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        String var10002 = rl.getNamespace();
        String var10003 = rl.getPath();
        return new ResourceLocation(var10002, var10003 + suffix);
    }
}
