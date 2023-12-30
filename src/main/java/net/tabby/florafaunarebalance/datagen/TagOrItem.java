package net.tabby.florafaunarebalance.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class TagOrItem {
    private final Ingredient ingredient;
    private final ItemPredicate predicate;

    static TagOrItem of(ItemLike item){
        return new TagOrItem(Ingredient.of(item), ItemPredicate.Builder.item().of(item).build());
    }
    static TagOrItem of(TagKey<Item> tag) {
        return new TagOrItem(Ingredient.of(tag), ItemPredicate.Builder.item().of(tag).build());
    }
    private TagOrItem(Ingredient pIngredient, ItemPredicate pPredicate) {
        this.ingredient = pIngredient;
        this.predicate = pPredicate;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    } public ItemPredicate getPredicate() {
        return this.predicate;
    }
}
