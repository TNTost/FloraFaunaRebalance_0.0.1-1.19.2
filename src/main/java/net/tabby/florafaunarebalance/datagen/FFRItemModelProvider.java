package net.tabby.florafaunarebalance.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.item.FFRii;

public class FFRItemModelProvider extends ItemModelProvider {
    public FFRItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, FloraFaunaRebalance.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(FFRii.UNTIPPED_DART);
        simpleItem(FFRii.POISON_DART);

        simpleItem(FFRii.SAPPHIRE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> pItem) {
        return withExistingParent(pItem.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FloraFaunaRebalance.MOD_ID, "item/" + pItem.getId().getPath()));
    }
}
