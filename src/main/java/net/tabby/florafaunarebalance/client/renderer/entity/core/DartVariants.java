package net.tabby.florafaunarebalance.client.renderer.entity.core;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.tabby.florafaunarebalance.item.FFRii;

import java.util.Objects;

public interface DartVariants {
    Item getEntitySourceItem();

    default String getVariant() {
        Item dart = getEntitySourceItem() == null ? FFRii.DART.get() : getEntitySourceItem();
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(dart)).getPath();
    }
}
