package net.tabby.florafaunarebalance.util.FFR;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class FFRTags {
    public static class tool {
        public static List<Tier> NST = List.of(Tiers.WOOD, Tiers.STONE, Tiers.IRON);
        public static TagKey<Block> MINEABLE_WITH_CHISEL = BTag("mineable_with_chisel");


        private static TagKey<Item> ITag(String name) {
            return ItemTags.create(F.l(name));
        }
        private static TagKey<Block> BTag(String name) {
            return BlockTags.create(F.l(name));
        }
    }

    public static class rule {
        public static RuleTest BEDROCK_ORE_REPLACEABLES = new TagMatchTest(Blocks.BEDROCK_ORE_REPLACEABLES);
    }

    public static class Blocks {
        public static final TagKey<Block> NEEDS_COPPER_TOOL = tag("needs_copper_tool");
        public static final TagKey<Block> NEEDS_MYTHRIL_TOOL = tag("needs_mythril_tool");

        public static final TagKey<Block> CUTOUT = tag("cutout");
        public static final TagKey<Block> SPLICEABLE_LOGS = tag("spliceable_logs");
        public static final TagKey<Block> BAMBOO_LOGS = tag("bamboo_logs");

        public static final TagKey<Block> BEDROCK_BLOCKS = tag("bedrock_blocks");
        public static final TagKey<Block> BEDROCK_ORE_REPLACEABLES = tag("bedrock_ore_replaceables");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(F.l(name));
        }
    }
    public static class Items {
        public static final TagKey<Item> BAMBOO_LOGS = tag("bamboo_logs");
        public static final TagKey<Item> DART_TAG = tag("dart");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(F.l(name));
        }
    }

}
