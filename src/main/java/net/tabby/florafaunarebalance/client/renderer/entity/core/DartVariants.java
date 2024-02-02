package net.tabby.florafaunarebalance.client.renderer.entity.core;

import net.minecraft.world.item.Item;
import net.tabby.florafaunarebalance.item.FFRii;

import static net.tabby.florafaunarebalance.util.all.FFRUtil.getRgStr;

public interface DartVariants {
    Item getEntitySourceItem();

    default String getVariant() {
        Item dart = getEntitySourceItem() == null ? FFRii.DART.get() : getEntitySourceItem();
        return getRgStr(dart);
    }
}
