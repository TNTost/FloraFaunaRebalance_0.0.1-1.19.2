package net.tabby.florafaunarebalance.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.core.Planks;
import net.tabby.florafaunarebalance.block.core.RotatedLogCore;
import net.tabby.florafaunarebalance.block.core.unique.*;
import net.tabby.florafaunarebalance.item.FFRCreativeTab;
import net.tabby.florafaunarebalance.item.FFRii;

import java.util.function.Supplier;

public class FFRib {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FloraFaunaRebalance.MOD_ID);


    public static final RegistryObject<Block> PYRITE_ORE = registerBlock("pyrite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<Block> DEEPSLATE_PYRITE_ORE = registerBlock("deepslate_pyrite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)), FFRCreativeTab.FFR_TAB);

    public static final RegistryObject<Block> RHYOLITIC_PUMICE = registerBlock("igneous_rock",
            () -> new IgneousRock(BlockBehaviour.Properties.of(Material.STONE).randomTicks()), FFRCreativeTab.FFR_TAB); //# when lava source meets stone or smthn.

    public static final RegistryObject<RotatedLogCore> VALYRIAN_HOLLOW_LOG = registerBlock("valyrian_hollow_log",
            () -> new HollowLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG), 7), FFRCreativeTab.FFR_TAB); //#TODO: maybe change to size 7, or include 3 special slots for something
    public static final RegistryObject<RotatedLogCore> ENHANCED_VALYRIAN_HOLLOW_LOG = registerBlock("enhanced_valyrian_hollow_log",
            () -> new HollowLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG), 45), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<RotatedLogCore> VALYRIAN_LOG = registerBlock("valyrian_log",
            () -> new RotatedLogCore(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<RotatedLogCore> VALYRIAN_WOOD = registerBlock("valyrian_wood",
            () -> new RotatedLogCore(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), FFRCreativeTab.FFR_TAB);

    public static final RegistryObject<Block> VALYRIAN_PLANKS = registerBlock("valyrian_planks",
            () -> new Planks(BlockBehaviour.Properties.of(Material.WOOD)), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<Block> VIBRANT_VALYRIAN_PLANKS = registerBlock("vibrant_valyrian_planks",
            () -> new Planks(BlockBehaviour.Properties.of(Material.WOOD)), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<Block> VALYRIAN_LEAVES = registerBlock("valyrian_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            }, FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<Block> VALYRIAN_SAPLING = registerBlock("valyrian_sapling",
            () -> new SaplingBlock(null, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), FFRCreativeTab.FFR_TAB);


    public static final RegistryObject<RotatedLogCore> ELYSIAN_HOLLOW_LOG = registerBlock("elysian_hollow_log",
            () -> new HollowLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG), 4), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<RotatedLogCore> ELYSIAN_LOG = registerBlock("elysian_log", //#TODO: grows sprouts faster and has differing effects as opposed to valyrian.
            () -> new RotatedLogCore(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<RotatedLogCore> ELYSIAN_WOOD = registerBlock("elysian_wood",
            () -> new RotatedLogCore(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), FFRCreativeTab.FFR_TAB);

    public static final RegistryObject<Block> ELYSIAN_PLANKS = registerBlock("elysian_planks",
            () -> new Planks(BlockBehaviour.Properties.of(Material.WOOD)), FFRCreativeTab.FFR_TAB);
                //# bug, cannot copy OAK_LOG as axis property interferes.
    public static final RegistryObject<Block> ELYSIAN_LEAVES = registerBlock("elysian_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            }, FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<Block> ELYSIAN_SAPLING = registerBlock("elysian_sapling",
            () -> new SaplingBlock(null, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), FFRCreativeTab.FFR_TAB);


    //# nymphaeaceae -> /nɪmfiːˈeɪsiː/
    public static final RegistryObject<Block> NYMPHAEACEAE = BLOCKS.register("nymphaeaceae",
            () -> new WaterLilyFlower(BlockBehaviour.Properties.copy(Blocks.LILY_PAD).randomTicks()));
    public static final RegistryObject<HangingRootsBlock> WATER_LILY_ROOTS = registerBlock("water_lily_roots",
            () -> new HangingRootsBlock(BlockBehaviour.Properties.copy(Blocks.HANGING_ROOTS)), FFRCreativeTab.FFR_TAB);




    public static final RegistryObject<Block> BAMBOO_PLANKS = registerBlock("bamboo_planks",
            () -> new Planks(BlockBehaviour.Properties.of(Material.WOOD)), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<Block> BAMBOO_MOSAIC = registerBlock("bamboo_mosaic",
            () -> new Planks(BlockBehaviour.Properties.of(Material.WOOD)), FFRCreativeTab.FFR_VANILLA);

    public static final RegistryObject<RotatedPillarBlock> BAMBOO_LOG = registerBlock("bamboo_log",
            () -> new ExtendBonemealableLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO)), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<RotatedPillarBlock> BAMBOO_WOOD = registerBlock("bamboo_wood",
            () -> new ExtendBonemealableLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO)), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_BAMBOO_LOG = registerBlock("stripped_bamboo_log",
            () -> new RotatedLogCore(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO)), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_BAMBOO_WOOD = registerBlock("stripped_bamboo_wood",
            () -> new RotatedLogCore(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO)), FFRCreativeTab.FFR_VANILLA);

    public static final RegistryObject<Block> BAMBOO_LEAVES = registerBlock("bamboo_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            }, FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<RotatedPillarBlock> BUDDING_BAMBOO_LOG = registerBlock("budding_bamboo_log",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO).randomTicks()), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<RotatedPillarBlock> BUDDING_BAMBOO_WOOD = registerBlock("budding_bamboo_wood",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO).randomTicks()), FFRCreativeTab.FFR_VANILLA);


    public static final RegistryObject<RotatedPillarBlock> BUDDING_OAK_LOG = registerBlock("budding_oak_log",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<RotatedPillarBlock> BUDDING_OAK_WOOD = registerBlock("budding_oak_wood",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);

    public static final RegistryObject<RotatedPillarBlock> BUDDING_BIRCH_LOG = registerBlock("budding_birch_log",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.BIRCH_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<RotatedPillarBlock> BUDDING_BIRCH_WOOD = registerBlock("budding_birch_wood",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.BIRCH_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);

    public static final RegistryObject<RotatedPillarBlock> BUDDING_SPRUCE_LOG = registerBlock("budding_spruce_log",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<RotatedPillarBlock> BUDDING_SPRUCE_WOOD = registerBlock("budding_spruce_wood",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);

    public static final RegistryObject<RotatedPillarBlock> BUDDING_JUNGLE_LOG = registerBlock("budding_jungle_log",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.JUNGLE_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<RotatedPillarBlock> BUDDING_JUNGLE_WOOD = registerBlock("budding_jungle_wood",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.JUNGLE_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);

    public static final RegistryObject<RotatedPillarBlock> BUDDING_ACACIA_LOG = registerBlock("budding_acacia_log",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.ACACIA_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<RotatedPillarBlock> BUDDING_ACACIA_WOOD = registerBlock("budding_acacia_wood",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.ACACIA_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);

    public static final RegistryObject<RotatedPillarBlock> BUDDING_DARK_OAK_LOG = registerBlock("budding_dark_oak_log",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<RotatedPillarBlock> BUDDING_DARK_OAK_WOOD = registerBlock("budding_dark_oak_wood",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);

    public static final RegistryObject<RotatedPillarBlock> BUDDING_MANGROVE_LOG = registerBlock("budding_mangrove_log",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.MANGROVE_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);
    public static final RegistryObject<RotatedPillarBlock> BUDDING_MANGROVE_WOOD = registerBlock("budding_mangrove_wood",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.MANGROVE_LOG).randomTicks()), FFRCreativeTab.FFR_VANILLA);










    public static final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), FFRCreativeTab.FFR_TAB);

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.METAL).strength(3f)), FFRCreativeTab.FFR_TAB);








    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return FFRii.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
