package net.tabby.florafaunarebalance.world.generation.ore;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class FFRop extends OrePlacements {
    public static final Holder<PlacedFeature> ORE_PYRITE = PlacementUtils.register("ore_pyrite", FFRof.ORE_PYRITE, rareOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-120), VerticalAnchor.aboveBottom(60))));
    public static final Holder<PlacedFeature> ORE_SAPHYRE = PlacementUtils.register("ore_saphyre", FFRof.ORE_SAPHYRE, rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-90), VerticalAnchor.aboveBottom(70))));


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
