package net.tabby.florafaunarebalance.datagen;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.item.FFRii;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.function.Consumer;


public class FFRRecipeProvider extends RecipeProvider implements IConditionBuilder {
    protected static final ImmutableList<String> BUDDING_TYPES;
    protected static final ImmutableList<ItemLike> SAPPHIRE_SMELTABLES;
    protected static final ImmutableList<ItemLike> STONE_TOOL_SET;
    protected static final ImmutableList<String> SET_STRINGS;

    public FFRRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }


    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> pConsumer) {
        ShapedRecipeBuilder.shaped(FFRib.BAMBOO_PLANKS.get()).define('#', Items.BAMBOO)
                .pattern("##")
                .pattern("##").unlockedBy("has_bamboo",
                        inventoryTrigger(ItemPredicate.Builder.item().of(FFRib.BAMBOO_PLANKS.get()).build()))
                        .save(pConsumer);

        for (String type : BUDDING_TYPES) {
            fourBlockPackingRecipe(pConsumer, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(FloraFaunaRebalance.MOD_ID, "budding_" + type + "_log")), ForgeRegistries.BLOCKS.getValue(new ResourceLocation(FloraFaunaRebalance.MOD_ID, "budding_" + type + "_wood")));
        }

        toolSetRecipes(pConsumer, Items.BONE, TagOrItem.of(ItemTags.STONE_TOOL_MATERIALS));
        //sapphire toolset here

        nineBlockStorageRecipes(pConsumer, FFRii.SAPPHIRE.get(), FFRib.SAPPHIRE_BLOCK.get());
        oreSmelting(pConsumer, SAPPHIRE_SMELTABLES, FFRii.SAPPHIRE.get(), 0.85f, 200, "sapphire");
        oreBlasting(pConsumer, SAPPHIRE_SMELTABLES, FFRii.SAPPHIRE.get(), 0.85f, 100, "sapphire");

        simpleCookingRecipe(pConsumer, "sticks", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 150, Items.STICK, Items.TORCH, 0.1f);
    }


    protected static void toolSetRecipes(Consumer<FinishedRecipe> pConsumer, ItemLike pHandle, TagOrItem pMaterial) {
        Iterator<ItemLike> pTools = STONE_TOOL_SET.iterator();
        Iterator<String> pGrid = SET_STRINGS.iterator();
        while (pTools.hasNext()) {
            ShapedRecipeBuilder.shaped(pTools.next()).define('I', pHandle).define('#', pMaterial.getIngredient())
                    .pattern(pGrid.next()).pattern(pGrid.next()).pattern(pGrid.next()).unlockedBy("has_" + pMaterial, inventoryTrigger(pMaterial.getPredicate())).save(pConsumer);
        } //tools
    }
    protected static void nineBlockStorageRecipes(@NotNull Consumer<FinishedRecipe> pConsumer, ItemLike pItem, ItemLike pBlock) {
        ShapelessRecipeBuilder.shapeless(pItem, 9).requires(pBlock).group(null).unlockedBy(getHasName(pBlock), has(pBlock)).save(pConsumer, new ResourceLocation(FloraFaunaRebalance.MOD_ID, getSimpleRecipeName(pItem)));
        ShapedRecipeBuilder.shaped(pBlock).define('#', pItem).pattern("###").pattern("###").pattern("###").group(null).unlockedBy(getHasName(pItem), has(pItem)).save(pConsumer, new ResourceLocation(FloraFaunaRebalance.MOD_ID, getSimpleRecipeName(pBlock)));
    }
    protected static void fourBlockPackingRecipe(Consumer<FinishedRecipe> pConsumer, ItemLike pEntry, ItemLike pResult) {
        ShapedRecipeBuilder.shaped(pResult).define('#', pEntry).pattern("##").pattern("##").unlockedBy(getHasName(pEntry), has(pEntry)).save(pConsumer, new ResourceLocation(FloraFaunaRebalance.MOD_ID, getSimpleRecipeName(pResult)));
    }

    static {
        BUDDING_TYPES = ImmutableList.of("oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "mangrove", "bamboo");
        SAPPHIRE_SMELTABLES = ImmutableList.of(FFRib.SAPHYRE_ORE.get().asItem(), FFRib.DEEPSLATE_SAPHYRE_ORE.get().asItem());
        STONE_TOOL_SET = ImmutableList.of(Items.STONE_AXE, Items.STONE_HOE, Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_SWORD);
        SET_STRINGS = ImmutableList.of("##", "I#", "I ", "##", "I ", "I ", "###", " I ", " I ", "#", "I", "I", "#", "#", "I");
    }
}
