package net.tabby.florafaunarebalance.item.core.unique;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.tabby.florafaunarebalance.entity.unique.DartProjectileEntity;
import net.tabby.florafaunarebalance.entity.unique.SandDustCloud;
import net.tabby.florafaunarebalance.item.FFRii;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;


import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;
import static net.tabby.florafaunarebalance.util.FFR.FFRTags.Items.*;
import static net.tabby.florafaunarebalance.util.FFRUtil.getItem;

//# make it so ChuteItem extends ProjectileWeaponItem instead...
public class ChuteItem extends ProjectileWeaponItem implements Vanishable {
    public static final float BREATH_DURATION_CAP = 17.0f;
    public static final Predicate<ItemStack> ALL_DARTS = itemStack -> itemStack.is(DART_TAG);
    public static final Predicate<ItemStack> CHUTE_AMMO_LIST = ALL_DARTS.or(itemStack -> itemStack.is(Items.FIREWORK_ROCKET)).or(itemStack -> itemStack.is(Items.SAND));

    public ChuteItem(Properties p_40660_) {
        super(p_40660_);
    }
    //# make it allow fireworks to be pulled only / require ignition source...
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack chuteItem = player.getItemInHand(hand);
        ItemStack ammo = player.getProjectile(chuteItem);
        boolean inf = player.getAbilities().instabuild;
        if (!ammo.isEmpty() || inf) {
            if (player.isCrouching()) {
                player.getCooldowns().addCooldown(chuteItem.getItem(), 6);
                if (!inf) player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 3, 5));
                shootProjectile(level, player, chuteItem, getAmmo(player, chuteItem), 0.85f); //# replace with fancy oxygen calculation
                return InteractionResultHolder.success(chuteItem);
            } else player.startUsingItem(hand); return InteractionResultHolder.consume(chuteItem);
        } else return InteractionResultHolder.fail(chuteItem);
        //# not sure if introduces bug due to not use forgeEvents.onArrowNock...
        //# remember setAirSupply.TEMP.
    }
    @Override
    public void inventoryTick(@NotNull ItemStack chuteItem, @NotNull Level level, @NotNull Entity entity, int i, boolean flag) {
        if (!level.isClientSide) {
            if (entity instanceof Player player) {
                if (player.getCooldowns().isOnCooldown(chuteItem.getItem())) {
                    player.setAirSupply(player.getAirSupply() - 7);
                }
            }
            CompoundTag nbt = chuteItem.getOrCreateTag();
            if (nbt.contains("queue", 11)) {
                int[] queue = nbt.getIntArray("queue");
                if (queue.length >= 1) {
                    for (int j = 0; j < queue.length; j++) {
                        queue[j]--;
                        if (queue[j] == 0 && entity instanceof Player player) {
                            shootProjectile(level, player, chuteItem, getAmmo(player, chuteItem), 0.65f);
                        }
                    }
                    nbt.putIntArray("queue", Arrays.stream(queue).filter(e -> e > 0).toArray());
                    System.out.println(Arrays.toString(nbt.getIntArray("queue")));
                } else {
                    nbt.remove("queue");
                }
            }
            // TODO: make scheduler which calls ShootProjectile() when int -in ListTag at chuteItem runs out...
        }
    }
    public static ItemStack getAmmo(Player player, ItemStack chuteItem) {
        ItemStack ammo = player.getProjectile(chuteItem);
        boolean inf = player.getAbilities().instabuild;
        if (!ammo.isEmpty() || inf) {
            ammo = ammo.isEmpty() || ammo.is(Items.ARROW) ? new ItemStack(FFRii.DART.get()) : ammo; //# set dart in case of no item present or remove defaultArrow..
            return ammo;
        }
        return ItemStack.EMPTY;
    }

    public void releaseUsing(@NotNull ItemStack chuteItem, @NotNull Level level, @NotNull LivingEntity entity, int t) {
        if (entity instanceof Player player) { //# instanceof <Type> "variableName" makes new var.
            ItemStack ammo = getAmmo(player, chuteItem);
            float pow = getPowerForTime(t = this.getUseDuration(chuteItem) - t);

            if (ammo.is(Items.FIREWORK_ROCKET)) {
                pow = (pow = getPowerForTime(t / 1.6f)) >= 0.9f ? pow : 0.0f;
                if (pow >= 0.3f && !consumePowder(player)) {
                    player.drop(ammo.split(1), false);
                }
            } //# if pow >= 0.9f same as powCalculation() / 6.0f >= 0.15f
            if (pow >= 0.15f) { //# TODO: have firing fireworks consume 1 fire-ash-power even when under-powered then it does light damage without release...
                if (ammo.is(DART_TAG)) {
                    CompoundTag tag = chuteItem.getOrCreateTag();//.put("queue", new IntArrayTag(IntArrayList.of(20, 40, 50)));
                    if (tag.contains("queue", 11)) {
                        int[] both = ArrayUtils.addAll(tag.getIntArray("queue"), 3, 7, 11);
                        tag.put("queue", new IntArrayTag(both));
                    } else {
                        tag.put("queue", new IntArrayTag(IntArrayList.of(3, 7, 11)));
                    }
                }
                shootProjectile(level, player, chuteItem, ammo, pow); //# shoot the projectile, duh.
                //level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LLAMA_SPIT, SoundSource.PLAYERS, 1.0f, (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2f); //# interesting sound <- accidental creation
            }
        }
    }
    protected boolean consumePowder(Player player) {
        ItemStack powder = getItem(player, itemStack -> itemStack.is(FFRii.FIRE_ASH_POWDER.get()));
        if (!powder.isEmpty()) reduce(powder, player);
        return !powder.isEmpty();
    }
    public static void reduce(ItemStack item, Player ply) { //# handles itemStack deletion and resize..
        if (!ply.getAbilities().instabuild) {
            item.shrink(1);
            if (item.isEmpty()) {
                ply.getInventory().removeItem(item);
            }
        }
    }
    public static float getPowerForTime(float ivt) { //# inverse of time
        return (ivt = (float) (1 - sqrt(1 - ivt / BREATH_DURATION_CAP))) >= 1.0f || (ivt != ivt) ? 1.0f : ivt; //# 1 minus sqrt of 1 minus x...
    }

    protected static void shootProjectile(Level level, Player player, ItemStack chuteItem, ItemStack ammo, float pow) {
        if (!level.isClientSide && !ammo.isEmpty()) { //# set Entity to null when firing non-dart...
            float size = 1.5f;
            Projectile projectile = switch (ammo.getItem().toString()) { //# TODO: make it fire seeds..
                case "sand" -> new SandDustCloud(level, player, size);
                case "firework_rocket" -> new FireworkRocketEntity(level, ammo, player, player.getX(), player.getEyeY() - 0.15000000596046448, player.getZ(), true);
                default -> getDart(level, player, ammo, pow);
            };
            Vector3f vec = getVec(player);
            projectile.shoot(vec.x(), vec.y(), vec.z(), pow * 2.0f, 1.0f);

            //# enchant handler /> [unbreaking], [mending], [power], [barrage], [gathering]
            chuteItem.hurtAndBreak(1, player, (breakEvent) -> breakEvent.broadcastBreakEvent(player.getUsedItemHand())); //# odd naming.
            //level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LLAMA_SPIT, SoundSource.PLAYERS, 1.0f, (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2f); //# interesting sound <- accidental creation
            level.addFreshEntity(projectile);
            reduce(ammo, player);
        }
    }
    protected static DartProjectileEntity getDart(Level level, Player player, ItemStack ammo, float pow) {
        DartProjectileEntity dart = (DartProjectileEntity) ((DartItem) ammo.getItem()).createDart(level, ammo, player); //# convert item -> dartItem /> createDart -> dartEntity /> shoot that.
        if (pow == 1.0f) { //or if insta-shot.                        //# casting is important...
            dart.setCritArrow(true);
        } if (player.getAbilities().instabuild) {
            dart.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }
        return dart;
    }
    protected static Vector3f getVec(Player player) {
        float relativeYaw = 1.0f; //a reminder>..
        Quaternion q = new Quaternion(new Vector3f(player.getUpVector(1.0f)), relativeYaw, true);
        Vector3f vec = new Vector3f(player.getViewVector(1.0f));
        vec.transform(q);
        return vec;
    }

    //# TODO: shootProjectile but-with a cooldown...


    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack p_40678_) {
        return UseAnim.SPYGLASS;
    }
    //# split this up into method call to other class to reduce clutter later...
    @Override
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                if (player.isUsingItem()) {
                    int s = arm == HumanoidArm.RIGHT ? 1 : -1;
                    poseStack.translate(s * 0.56f, -0.52f, -0.72f);
                }
                return IClientItemExtensions.super.applyForgeHandTransform(poseStack, player, arm, itemInHand, partialTick, equipProcess, swingProcess);
            }
        });
    }

    public int getUseDuration(@NotNull ItemStack p_41454_) {
        return 270; //# method required to make projectile weapon, <releaseUsing.i> takes "return value - useTime"
    }
    public boolean useOnRelease(ItemStack itemStack) {
        return itemStack.is(this);
    }

    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return CHUTE_AMMO_LIST;
    }
    public int getDefaultProjectileRange() {
        return 12;
    }
}
