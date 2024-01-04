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
import net.tabby.florafaunarebalance.block.custom.BuddingLog;
import net.tabby.florafaunarebalance.block.custom.LogRotatedPillarBlock;
import net.tabby.florafaunarebalance.block.custom.ExtendBonemealableLog;
import net.tabby.florafaunarebalance.item.FFRCreativeTab;
import net.tabby.florafaunarebalance.item.FFRii;

import java.util.function.Supplier;

public class FFRib {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FloraFaunaRebalance.MOD_ID);


    public static final RegistryObject<Block> BAMBOO_PLANKS = registerBlock("bamboo_planks",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.BAMBOO)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            }, FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<Block> BAMBOO_MOSAIC = registerBlock("bamboo_mosaic",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.BAMBOO)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            }, FFRCreativeTab.FFR_TAB);

    public static final RegistryObject<RotatedPillarBlock> BAMBOO_LOG = registerBlock("bamboo_log",
            () -> new ExtendBonemealableLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO)), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<RotatedPillarBlock> BAMBOO_WOOD = registerBlock("bamboo_wood",
            () -> new ExtendBonemealableLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO)), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_BAMBOO_LOG = registerBlock("stripped_bamboo_log",
            () -> new LogRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO)), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_BAMBOO_WOOD = registerBlock("stripped_bamboo_wood",
            () -> new LogRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO)), FFRCreativeTab.FFR_TAB);

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
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO).randomTicks()), FFRCreativeTab.FFR_TAB);
    public static final RegistryObject<RotatedPillarBlock> BUDDING_BAMBOO_WOOD = registerBlock("budding_bamboo_wood",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO).randomTicks()), FFRCreativeTab.FFR_TAB);


    public static final RegistryObject<RotatedPillarBlock> BUDDING_OAK_LOG = registerBlock("budding_oak_log",
            () -> new BuddingLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).randomTicks()), FFRCreativeTab.FFR_TAB);





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
