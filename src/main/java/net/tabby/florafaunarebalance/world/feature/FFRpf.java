package net.tabby.florafaunarebalance.world.feature;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;

import java.util.List;

public class FFRpf {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, FloraFaunaRebalance.MOD_ID);

    public static final RegistryObject<PlacedFeature> PYRITE_ORES_PLACED = PLACED_FEATURE.register("pyrite_ores_placed",
            () -> new PlacedFeature(FFRcf.PYRITE_ORE.getHolder().get(),
                    commonOrePlacement(5,
                            HeightRangePlacement.triangle(VerticalAnchor.TOP, VerticalAnchor.BOTTOM))));

    public static List<PlacementModifier> orePlacement(PlacementModifier modifierA, PlacementModifier modifierB) {
        return List.of(modifierA, InSquarePlacement.spread(), modifierB, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(count), modifier);
    }


    public static void register(IEventBus eventBus) {
        PLACED_FEATURE.register(eventBus);
    }
}
