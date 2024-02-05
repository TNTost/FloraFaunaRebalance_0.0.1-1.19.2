package net.tabby.florafaunarebalance.client.renderer.entity.core;

import net.minecraft.world.item.Item;
import net.tabby.florafaunarebalance.item.FFRii;

import static net.tabby.florafaunarebalance.util.all.FFRUtil.getRgStr;

public interface DartVariants {
    Item getEntitySourceItem();

    default String getVariant() {
        Item s = getEntitySourceItem();
        System.out.println(s);
        Item dart = s == null ? FFRii.DART.get() : s;
        System.out.println(getRgStr(dart));
        return getRgStr(dart);
    }
}
