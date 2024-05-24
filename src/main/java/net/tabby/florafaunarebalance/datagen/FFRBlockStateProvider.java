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
        simpleBlock(FFRib.WATER_LILY_ROOTS.get());
            //simpleBlockItem(FFRib.WATER_LILY_ROOTS.get(), models().singleTexture("water_lily_roots.png", mcLoc("block"), blockItemTexture(FFRib.WATER_LILY_ROOTS.get())));

        simpleBlockWithItem(FFRib.BAMBOO_PLANKS);
        simpleBlockWithItem(FFRib.BAMBOO_MOSAIC);

        axisBlock(FFRib.VALYRIAN_HOLLOW_LOG.get(), this.blockTexture(FFRib.VALYRIAN_HOLLOW_LOG.get()), this.extend(this.blockTexture(FFRib.VALYRIAN_HOLLOW_LOG.get()), "_top"));

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


        logWithItem(FFRib.BUDDING_OAK_LOG);
        axisBlock(FFRib.BUDDING_OAK_WOOD.get(), blockTexture(FFRib.BUDDING_OAK_LOG.get()), blockTexture(FFRib.BUDDING_OAK_LOG.get()));
            simpleBlockItem(FFRib.BUDDING_OAK_WOOD.get(), cubeColumnOffset(FFRib.BUDDING_OAK_WOOD.get(), FFRib.BUDDING_OAK_LOG.get()));
        logWithItem(FFRib.BUDDING_BIRCH_LOG);
        axisBlock(FFRib.BUDDING_BIRCH_WOOD.get(), blockTexture(FFRib.BUDDING_BIRCH_LOG.get()), blockTexture(FFRib.BUDDING_BIRCH_LOG.get()));
            simpleBlockItem(FFRib.BUDDING_BIRCH_WOOD.get(), cubeColumnOffset(FFRib.BUDDING_BIRCH_WOOD.get(), FFRib.BUDDING_BIRCH_LOG.get()));
        logWithItem(FFRib.BUDDING_SPRUCE_LOG);
        axisBlock(FFRib.BUDDING_SPRUCE_WOOD.get(), blockTexture(FFRib.BUDDING_SPRUCE_LOG.get()), blockTexture(FFRib.BUDDING_SPRUCE_LOG.get()));
            simpleBlockItem(FFRib.BUDDING_SPRUCE_WOOD.get(), cubeColumnOffset(FFRib.BUDDING_SPRUCE_WOOD.get(), FFRib.BUDDING_SPRUCE_LOG.get()));
        logWithItem(FFRib.BUDDING_JUNGLE_LOG);
        axisBlock(FFRib.BUDDING_JUNGLE_WOOD.get(), blockTexture(FFRib.BUDDING_JUNGLE_LOG.get()), blockTexture(FFRib.BUDDING_JUNGLE_LOG.get()));
            simpleBlockItem(FFRib.BUDDING_JUNGLE_WOOD.get(), cubeColumnOffset(FFRib.BUDDING_JUNGLE_WOOD.get(), FFRib.BUDDING_JUNGLE_LOG.get()));
        logWithItem(FFRib.BUDDING_ACACIA_LOG);
        axisBlock(FFRib.BUDDING_ACACIA_WOOD.get(), blockTexture(FFRib.BUDDING_ACACIA_LOG.get()), blockTexture(FFRib.BUDDING_ACACIA_LOG.get()));
            simpleBlockItem(FFRib.BUDDING_ACACIA_WOOD.get(), cubeColumnOffset(FFRib.BUDDING_ACACIA_WOOD.get(), FFRib.BUDDING_ACACIA_LOG.get()));
        logWithItem(FFRib.BUDDING_DARK_OAK_LOG);
        axisBlock(FFRib.BUDDING_DARK_OAK_WOOD.get(), blockTexture(FFRib.BUDDING_DARK_OAK_LOG.get()), blockTexture(FFRib.BUDDING_DARK_OAK_LOG.get()));
            simpleBlockItem(FFRib.BUDDING_DARK_OAK_WOOD.get(), cubeColumnOffset(FFRib.BUDDING_DARK_OAK_WOOD.get(), FFRib.BUDDING_DARK_OAK_LOG.get()));
        logWithItem(FFRib.BUDDING_MANGROVE_LOG);
        axisBlock(FFRib.BUDDING_MANGROVE_WOOD.get(), blockTexture(FFRib.BUDDING_MANGROVE_LOG.get()), blockTexture(FFRib.BUDDING_MANGROVE_LOG.get()));
            simpleBlockItem(FFRib.BUDDING_MANGROVE_WOOD.get(), cubeColumnOffset(FFRib.BUDDING_MANGROVE_WOOD.get(), FFRib.BUDDING_MANGROVE_LOG.get()));


        simpleBlockWithItem(FFRib.SAPPHIRE_BLOCK);
        simpleBlockWithItem(FFRib.SAPHYRE_ORE);
        simpleBlockWithItem(FFRib.DEEPSLATE_SAPHYRE_ORE);
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
    public ResourceLocation blockItemTexture(Block block) {
        ResourceLocation name = this.key(block);
        return new ResourceLocation(name.getNamespace(), "item/" + name.getPath());
    }
    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        String var10002 = rl.getNamespace();
        String var10003 = rl.getPath();
        return new ResourceLocation(var10002, var10003 + suffix);
    }
}
