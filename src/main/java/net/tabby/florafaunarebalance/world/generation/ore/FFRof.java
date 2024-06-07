package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.util.FFR.FFRTags;

import java.util.List;

public class FFRof extends OreFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> FEATURE =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, FloraFaunaRebalance.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_PYRITE = FEATURE.register("ore_pyrite",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(
                    OreConfiguration.target(new BlockMatchTest(Blocks.TUFF), FFRib.TUFF_PYRITE_ORE.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, FFRib.DEEPSLATE_PYRITE_ORE.get().defaultBlockState()),
                    OreConfiguration.target(FFRTags.rule.BEDROCK_ORE_REPLACEABLES, FFRib.BEDROCK_PYRITE_ORE.get().defaultBlockState())), 7)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_TANZANITE = FEATURE.register("ore_tanzanite",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(
                    OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, FFRib.SAPHYRE_ORE.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, FFRib.DEEPSLATE_SAPHYRE_ORE.get().defaultBlockState())), 11, 0.4f)));


    public static void register(IEventBus eventBus) {
        FEATURE.register(eventBus);
    }
}
