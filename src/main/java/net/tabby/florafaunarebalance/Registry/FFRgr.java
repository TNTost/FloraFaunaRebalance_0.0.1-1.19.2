package net.tabby.florafaunarebalance.Registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.world.generation.ore.unique.replacement.ConversionDefinition;
import oshi.util.tuples.Triplet;

public class FFRgr {
    private static boolean isFrozen = false;
    public static final DeferredRegister<ConversionDefinition<?, ?>> CONVERSION_DEFINITION =
            DeferredRegister.create(new ResourceLocation(FloraFaunaRebalance.MOD_ID, "conversion_definition"), FloraFaunaRebalance.MOD_ID);


    public static final RegistryObject<ConversionDefinition<?, ?>> IRON_TO_PYRITE_WHEN_LAVA = CONVERSION_DEFINITION.register("iron_to_pyrite",
            () -> new ConversionDefinition<>(Blocks.IRON_ORE, new Triplet<>(1, Blocks.LAVA, FFRib.PYRITE_ORE.get().defaultBlockState())));
    public static final RegistryObject<ConversionDefinition<?, ?>> DEEPSLATE_IRON_TO_PYRITE_WHEN_LAVA = CONVERSION_DEFINITION.register("deepslate_iron_to_pyrite",
            () -> new ConversionDefinition<>(Blocks.DEEPSLATE_IRON_ORE, new Triplet<>(1, Blocks.LAVA, FFRib.DEEPSLATE_PYRITE_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConversionDefinition<?, ?>> COPPER_RELATIVE_STONE_TO_PYRITE_MASK = CONVERSION_DEFINITION.register("copper_relative_to_pyrite",
            () -> new ConversionDefinition<>(Blocks.COPPER_ORE, new Triplet<>(7, Blocks.STONE, FFRib.PYRITE_ORE.get().defaultBlockState())));
    public static final RegistryObject<ConversionDefinition<?, ?>> DEEPSLATE_COPPER_RELATIVE_STONE_TO_PYRITE_MASK = CONVERSION_DEFINITION.register("deepslate_copper_relative_to_pyrite",
            () -> new ConversionDefinition<>(Blocks.DEEPSLATE_COPPER_ORE, new Triplet<>(7, Blocks.STONE, FFRib.DEEPSLATE_PYRITE_ORE.get().defaultBlockState())));


    public static void register(final IEventBus eventBus) {
        if (isFrozen) throw new IllegalStateException("Ore-Conversion registry has already been frozen");
        CONVERSION_DEFINITION.makeRegistry(RegistryBuilder::new);
        CONVERSION_DEFINITION.register(eventBus);
        isFrozen = true;
    }
}
