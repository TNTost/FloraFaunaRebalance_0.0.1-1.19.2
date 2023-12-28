package net.tabby.florafaunarebalance.datagen;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.item.FFRii;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;


public class FFRRecipeProvider extends RecipeProvider implements IConditionBuilder {
    protected static final ImmutableList<ItemLike> SAPPHIRE_SMELTABLES;
    protected static final ImmutableList<ItemLike> STONE_TOOL_SET;

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

        toolSetRecipes(pConsumer, Items.BONE, Items.COBBLESTONE, STONE_TOOL_SET);
        //sapphire toolset here

        nineBlockStorageRecipes(pConsumer, FFRii.SAPPHIRE.get(), FFRib.SAPPHIRE_BLOCK.get());
        oreSmelting(pConsumer, SAPPHIRE_SMELTABLES, FFRii.SAPPHIRE.get(), 0.85f, 200, "sapphire");
        oreBlasting(pConsumer, SAPPHIRE_SMELTABLES, FFRii.SAPPHIRE.get(), 0.85f, 100, "sapphire");

        simpleCookingRecipe(pConsumer, "sticks", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 150, Items.STICK, Items.TORCH, 0.1f);
    }


    protected static void toolSetRecipes(Consumer<FinishedRecipe> pConsumer, ItemLike pHandle, ItemLike pMaterial, List<ItemLike> pItems) {
        Iterator<ItemLike> itemList = pItems.iterator();
        ShapedRecipeBuilder.shaped(itemList.next()).define('I', pHandle).define('#', pMaterial)
                .pattern("##").pattern("I#").pattern("I ").unlockedBy("has_" + pMaterial, inventoryTrigger(ItemPredicate.Builder.item().of(pMaterial).build())).save(pConsumer);
        //axe
        ShapedRecipeBuilder.shaped(itemList.next()).define('I', pHandle).define('#', pMaterial)
                .pattern("##").pattern("I ").pattern("I ").unlockedBy("has_" + pMaterial, inventoryTrigger(ItemPredicate.Builder.item().of(pMaterial).build())).save(pConsumer);
        //hoe
        ShapedRecipeBuilder.shaped(itemList.next()).define('I', pHandle).define('#', pMaterial)
                .pattern("###").pattern(" I ").pattern(" I ").unlockedBy("has_" + pMaterial, inventoryTrigger(ItemPredicate.Builder.item().of(pMaterial).build())).save(pConsumer);
        //pickaxe
        ShapedRecipeBuilder.shaped(itemList.next()).define('I', pHandle).define('#', pMaterial)
                .pattern("#").pattern("I").pattern("I").unlockedBy("has_" + pMaterial, inventoryTrigger(ItemPredicate.Builder.item().of(pMaterial).build())).save(pConsumer);
        //shovel
        ShapedRecipeBuilder.shaped(itemList.next()).define('I', pHandle).define('#', pMaterial)
                .pattern("#").pattern("#").pattern("I").unlockedBy("has_" + pMaterial, inventoryTrigger(ItemPredicate.Builder.item().of(pMaterial).build())).save(pConsumer);
        //sword

    }

    static {
        SAPPHIRE_SMELTABLES = ImmutableList.of(FFRib.SAPPHIRE_ORE.get().asItem(), FFRib.DEEPSLATE_SAPPHIRE_ORE.get().asItem());
        STONE_TOOL_SET = ImmutableList.of(Items.STONE_AXE, Items.STONE_HOE, Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_SWORD);
    }
}
