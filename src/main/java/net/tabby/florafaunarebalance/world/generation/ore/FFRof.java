package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.tabby.florafaunarebalance.block.FFRib;

import java.util.List;

public class FFRof extends OreFeatures {
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_PYRITE = FeatureUtils.register("ore_pyrite", Feature.ORE,
            new OreConfiguration(List.of(
                    OreConfiguration.target(new BlockMatchTest(Blocks.TUFF), FFRib.TUFF_PYRITE_ORE.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, FFRib.DEEPSLATE_PYRITE_ORE.get().defaultBlockState())), 7));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_SAPHYRE = FeatureUtils.register("ore_saphyre", Feature.ORE,
            new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, FFRib.SAPHYRE_ORE.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, FFRib.DEEPSLATE_SAPHYRE_ORE.get().defaultBlockState())), 11));
}
