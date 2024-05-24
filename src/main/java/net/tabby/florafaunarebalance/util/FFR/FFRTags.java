package net.tabby.florafaunarebalance.util.FFR;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;

import javax.swing.text.html.HTML;

public class FFRTags {
    public static class Blocks {
        public static final TagKey<Block> CUTOUT = tag("cutout");
        public static final TagKey<Block> SPLICEABLE_LOGS = tag("spliceable_logs");
        public static final TagKey<Block> BAMBOO_LOGS = tag("bamboo_logs");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(FloraFaunaRebalance.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> BAMBOO_LOGS = tag("bamboo_logs");
        public static final TagKey<Item> DART_TAG = tag("dart");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(FloraFaunaRebalance.MOD_ID, name));
        }
    }

}
