package net.tabby.florafaunarebalance.datagen.tag;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.entity.FFRet;
import org.jetbrains.annotations.Nullable;

public class FFREntityTagGenerator extends EntityTypeTagsProvider {
    public FFREntityTagGenerator(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, FloraFaunaRebalance.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(EntityTypeTags.ARROWS)
                .add(FFRet.DART.get());
    }
}
