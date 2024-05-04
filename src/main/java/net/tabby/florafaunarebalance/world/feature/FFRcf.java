package net.tabby.florafaunarebalance.world.feature;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.util.FFR.FFRTags;

import java.util.List;
import java.util.function.Supplier;

public class FFRcf {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, FloraFaunaRebalance.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> D0_PYRITE_ORES = Suppliers.memoize(
            () -> List.of(
                    OreConfiguration.target(new BlockMatchTest(Blocks.IRON_ORE), FFRib.PYRITE_ORE.get().defaultBlockState()),
                    OreConfiguration.target(new BlockMatchTest(Blocks.DEEPSLATE_IRON_ORE), FFRib.PYRITE_ORE.get().defaultBlockState())
            )
    );

    public static final RegistryObject<ConfiguredFeature<?, ?>> PYRITE_ORE = CONFIGURED_FEATURE.register("pyrite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(D0_PYRITE_ORES.get(), 3)));


    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURE.register(eventBus);
    }
}
