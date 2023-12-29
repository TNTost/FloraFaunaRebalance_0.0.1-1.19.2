package net.tabby.florafaunarebalance.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.datagen.tag.FFRBlockTagGenerator;
import net.tabby.florafaunarebalance.datagen.tag.FFREntityTagGenerator;
import net.tabby.florafaunarebalance.datagen.tag.FFRItemTagGenerator;

@Mod.EventBusSubscriber(modid = FloraFaunaRebalance.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent pEvent) {
        DataGenerator generator = pEvent.getGenerator();
        ExistingFileHelper existingFileHelper = pEvent.getExistingFileHelper();

        generator.addProvider(true, new FFRRecipeProvider(generator));
        generator.addProvider(true, new FFRLootTableProvider(generator));
        generator.addProvider(true, new FFRBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(true, new FFRItemModelProvider(generator, existingFileHelper));
        generator.addProvider(true, new FFRBlockTagGenerator(generator, existingFileHelper));

        FFRBlockTagGenerator blockTagGenerator = new FFRBlockTagGenerator(generator, existingFileHelper);
        generator.addProvider(true, new FFRItemTagGenerator(generator, blockTagGenerator, existingFileHelper));
        generator.addProvider(true, new FFREntityTagGenerator(generator, existingFileHelper));
    }
}
