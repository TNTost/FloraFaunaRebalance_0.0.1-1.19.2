package net.tabby.florafaunarebalance.item.unique;

import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class SlingItem extends ProjectileWeaponItem {
    public static final Predicate<ItemStack> STONES = (i) -> i.is(ItemTags.STONE_CRAFTING_MATERIALS);
    protected double velocity;

    public SlingItem(Properties p) {
        super(p);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack sling = player.getItemInHand(hand);
        ItemStack pebble = ChuteItem.getAmmo(player, sling, Items.COBBLESTONE);
        if (isSet(sling)) {
            if (this.velocity < 0.05) {
                clearStone(sling, level, player); //# TODO: consume item here and add unload check if velocity == 0f.
                return InteractionResultHolder.consume(sling);
            } else {
                //yeet here
            }
        } else {
            //# TODO: rabbet ammo
            if (!pebble.isEmpty() || player.getAbilities().instabuild) {
                doSet(sling, player, true);
                return InteractionResultHolder.consume(sling);
            }
        }
        return InteractionResultHolder.pass(sling);
    }

    public boolean isSet(ItemStack sling) {
        CompoundTag tag = sling.getTag();
        return tag != null && tag.getBoolean("Set");
    }
    public static void doSet(ItemStack sling, Player player, boolean value) {
        sling.getOrCreateTag().putBoolean("Set", value);
        if (value) { //# if should load.
            addStone(sling, ChuteItem.getAmmo(player, sling, Items.COBBLESTONE));
        }
    }

    protected static void addStone(ItemStack sling, ItemStack pebble) {
        CompoundTag tag = sling.getOrCreateTag();
        ListTag lt = tag.contains("loadenProjectiles") ? tag.getList("loadenProjectiles", 10) : new ListTag();
        lt.add(pebble.split(1).save(new CompoundTag()));
        tag.put("loadenProjectiles", lt);
    }
    protected static void clearStone(ItemStack sling, Level level, Player player) {
        CompoundTag tag = sling.getTag();
        if (tag != null && tag.contains("loadenProjectiles", 9)) {
            ListTag lt = tag.getList("loadenProjectiles", 10);
            for (var nbt : lt) {
                level.addFreshEntity(new ItemEntity(level, player.getX(), player.getY() + 0.4d, player.getZ(),
                        new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(nbt.getAsString().replaceAll(".+id:\"([^\"]*)\".", "$1"))),
                                Integer.parseInt(nbt.getAsString().replaceAll(".+?Count:(.*?)b,.+", "$1"))))); //positive look-behind (?<=N), positive look-ahead (?=N)...

            }
            lt.clear();
            tag.put("loadenProjectiles", lt);
            doSet(sling, player, false);
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
