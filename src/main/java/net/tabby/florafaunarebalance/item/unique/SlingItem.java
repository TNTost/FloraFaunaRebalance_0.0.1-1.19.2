package net.tabby.florafaunarebalance.item.unique;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class SlingItem extends ProjectileWeaponItem {
    public static final Predicate<ItemStack> STONES = (i) -> i.is(ItemTags.STONE_CRAFTING_MATERIALS);
    private static Player PLYR;
    public SlingItem(Properties p) {
        super(p);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        PLYR = player;
        ItemStack sling = player.getItemInHand(hand);
        if (!isSet(sling)) {
            doSet(sling, true);
            return InteractionResultHolder.consume(sling);
        } else {
            clearStone(sling);
            return InteractionResultHolder.consume(sling);
        }
        //return InteractionResultHolder.fail(sling);
    }

    public boolean isSet(ItemStack sling) {
        CompoundTag tag = sling.getTag();
        return tag != null && tag.getBoolean("Set");
    }
    public static void doSet(ItemStack sling, boolean value) {
        CompoundTag tag = sling.getOrCreateTag();
        tag.putBoolean("Set", value);
        addStone(sling, ChuteItem.getAmmo(PLYR, sling, Items.COBBLESTONE));
    }

    protected static void addStone(ItemStack sling, ItemStack pebble) {
        CompoundTag tag = sling.getOrCreateTag();
        ListTag lt = tag.contains("loadenProjectiles") ? tag.getList("loadenProjectiles", 10) : new ListTag();
        lt.add(pebble.save(new CompoundTag()));
        tag.put("loadenProjectiles", lt);
    }
    protected static void clearStone(ItemStack sling) {
        CompoundTag tag = sling.getTag();
        if (tag != null) {
            ListTag lt = tag.getList("loadenProjectiles", 9);
            lt.clear();
            tag.put("loadenProjectiles", lt);
            doSet(sling, false);
        }
    }



    // initial force, this force is furthered when a click is timed at the crest of the rotation..
    //velocity, drag &friction, and force are calculated each frame.
    //# animation starts by left-clicking and advances <n>frames based on speed...
    //# right-clicking releases the projectile..

    //# enchant: scattering.

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return STONES;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 0;
    }
}
