package net.tabby.florafaunarebalance.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;

public class ModTags {
    public static class Blocks {

    }
    public static class Items {
        public static final TagKey<Item> DART_TAG = tag("dart");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(FloraFaunaRebalance.MOD_ID, name));
        }
    }

}
