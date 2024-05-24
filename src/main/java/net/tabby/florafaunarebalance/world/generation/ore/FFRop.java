package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;

import java.util.List;

public class FFRop extends OrePlacements {
    public static final DeferredRegister<PlacedFeature> PLACEMENT =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, FloraFaunaRebalance.MOD_ID);

    public static final RegistryObject<PlacedFeature> ORE_PYRITE = PLACEMENT.register("ore_pyrite",
            () -> new PlacedFeature(FFRof.ORE_PYRITE.getHolder().get(), commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-120), VerticalAnchor.aboveBottom(60)))));
    public static final RegistryObject<PlacedFeature> ORE_SAPHYRE = PLACEMENT.register("ore_saphyre",
            () -> new PlacedFeature(FFRof.ORE_SAPHYRE.getHolder().get(), commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-90), VerticalAnchor.aboveBottom(70)))));



    public static void register(IEventBus eventBus) {
        PLACEMENT.register(eventBus);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }
    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }
    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }
}
