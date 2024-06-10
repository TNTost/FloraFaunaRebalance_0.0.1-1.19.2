package net.tabby.florafaunarebalance.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.core.unique.BuddingLog;
import net.tabby.florafaunarebalance.item.FFRii;
import net.tabby.florafaunarebalance.item.unique.ChuteItem;
import net.tabby.florafaunarebalance.item.unique.SlingItem;
import net.tabby.florafaunarebalance.network.FFRNetworkHandler;
import net.tabby.florafaunarebalance.network.packet.EntityC2SPacket;
import net.tabby.florafaunarebalance.util.FFR.FFRTags;

@SuppressWarnings("unused")
public class FFRForgeEvents {
    @Mod.EventBusSubscriber(modid = FloraFaunaRebalance.MOD_ID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onWindSlingBlock(PlayerInteractEvent.LeftClickBlock blockEvent) {
            if (blockEvent.getLevel().isClientSide) {
                blockEvent.setCanceled(trySling(blockEvent.getEntity()));
            }
        }
        @SubscribeEvent
        public static void onWindSlingEmpty(PlayerInteractEvent.LeftClickEmpty emptyEvent) {
            trySling(emptyEvent.getEntity());
        }
        protected static boolean trySling(Player player) {
            ItemStack itm = player.getItemInHand(player.getUsedItemHand());
            if (itm.getItem() instanceof SlingItem si) {
                if (si.isSet(itm)) {
                    FFRNetworkHandler.sendToServer(new EntityC2SPacket()); // client only packet.
                    return true;
                }
            }
            return false;
        }

        @SubscribeEvent
        public static void onChangeMiningSpeed(PlayerEvent.BreakSpeed event) {
            Player player = event.getEntity();
            if (player.getItemInHand(player.getUsedItemHand()).getItem() instanceof TieredItem ti) {
                if (FFRTags.tool.NST.contains(ti.getTier()) || player.getUseItem().isEmpty()) {
                    System.out.println("##&=- reached! -=&##");
                    event.setNewSpeed(event.getOriginalSpeed() * 0.65f);
                }
            }
        }

        @SubscribeEvent
        public static void onOpenHollowLog(PlayerInteractEvent.RightClickBlock event) {
            //net.minecraftforge.event.ForgeEventFactory.onBlockPlace(event.getEntity(), BlockSnapshot.create(event.getLevel().dimension(), event.getLevel(), pos), event.getHitVec().getDirection());
            //                            event.getLevel().setBlockAndUpdate(pos.relative(Objects.requireNonNull(event.getFace())), Objects.requireNonNull(Block.byItem(itm.getItem()).getStateForPlacement(new BlockPlaceContext(event.getEntity(), event.getHand(), event.getItemStack(), event.getHitVec()))));
            //                            event.getLevel().playSound(null, pos, Block.byItem(itm.getItem()).getSoundType(Block.byItem(itm.getItem()).defaultBlockState(), event.getLevel(), pos.relative(event.getFace()), event.getEntity()).getPlaceSound(), SoundSource.BLOCKS, 1.0f, 0.8f);
            //                            event.getEntity().swing(event.getHand());
        }
        public static BlockState getAt(Level level, BlockPos pos) {
            return level.getBlockState(pos);
        }

        //@SubscribeEvent
        //public static void onPlaceLilyAimWater(PlayerInteractEvent.RightClickItem event) {
        //    if (event.getItemStack().is(FFRib.NYMPHAEACEAE.get().asItem())) {
        //        if (ray(event));
        //    }
        //}
        //@SubscribeEvent
        //public static void onPlaceLilyAimBottom(PlayerInteractEvent.RightClickBlock event) {
        //    if (event.getItemStack().is(FFRib.NYMPHAEACEAE.get().asItem())) {
        //        if (ray(event));
        //    }
        //}
        //private static boolean ray (PlayerInteractEvent event) {
        //    Level lvl = event.getLevel();
        //    if (true) {
        //        Player ply = event.getEntity();
//
        //        HitResult result = ply.pick(5, 1.0f, true); //# cast ray at view angle...
        //        BlockPos pos = new BlockPos(result.getLocation());
        //        Pair<BlockState, BlockState> state = new Pair<>(lvl.getBlockState(pos.above()), lvl.getBlockState(pos));
        //        if (state.getA().getFluidState().getType() == Fluids.EMPTY && state.getB().is(Blocks.WATER) && state.getB().getFluidState().getAmount() == 8) { //# check if water + air above.
        //            // TODO check player collision...
        //            if (lvl.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, ply, new AABB(pos)).isEmpty() &&) {
        //                lvl.setBlockAndUpdate(pos.above(), FFRib.NYMPHAEACEAE.get().defaultBlockState());
        //                ply.swing(InteractionHand.MAIN_HAND, true);
        //                lvl.playSound(null, pos, new SoundEvent(new ResourceLocation("minecraft", "block.lily_pad.place")), SoundSource.BLOCKS, 1.0f, 1.0f);
        //                ItemStack itm = event.getItemStack();
        //                if (!ply.getAbilities().instabuild) {
        //                    itm.shrink(1);
        //                    if (itm.isEmpty()) {
        //                        ply.getInventory().removeItem(itm);
        //                    }
        //                }
        //                return true;
        //            }
        //        }
        //    }
        //    return false;
        //}

        @SubscribeEvent
        public static void onBoneMealLog (PlayerInteractEvent.RightClickBlock event) {
            Level level = event.getLevel();
            if (event.getItemStack().is(Items.BONE_MEAL)) {
                BlockState state;
                BlockPos pos = event.getHitVec().getBlockPos();
                if ((state = level.getBlockState(pos)).is(FFRTags.Blocks.SPLICEABLE_LOGS)) {
                    level.setBlock(pos, BuddingLog.createNewBuddingLog(state), 2);
                    event.getItemStack().shrink(1);
                    level.playSound(null, pos, new SoundEvent(new ResourceLocation("minecraft", "item.bone_meal.use")), SoundSource.BLOCKS, 1.0f, 1.0f);
                    double scale = 3.5;
                    RandomSource random = RandomSource.create();
                    for (int i = 0; i < 11; i++) {
                        level.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + random.nextDouble() * scale - 2.25, pos.getY() + 0.5, pos.getZ() + random.nextDouble() * scale - 2.25, 0.0d + random.nextDouble() * 0.04, 0.05d, 0.0d + random.nextDouble() * 0.04);
                    }
                }
            }
        }
        @SubscribeEvent
        public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
            if (event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() instanceof ChuteItem) {
                float drawTimeInTicks = 14.0f; //# 12 -> 14...
                float dT = event.getPlayer().getTicksUsingItem() / drawTimeInTicks;
                dT = dT > 1.0f ? 1.0f : dT*dT;
                event.setNewFovModifier(1.0f - dT * 0.175f);
            }
        }

        @SubscribeEvent
        public static void onSpentBreath(TickEvent.PlayerTickEvent event) {
            Player p1 = event.player;
            if (!p1.isUnderWater() && p1.isCrouching()) { //# on land, sneaking...event.side == LogicalSide.SERVER &&
                if (p1.getCooldowns().isOnCooldown(FFRii.DART_CHUTE.get())) {
                    int Oxy = p1.getAirSupply();
                    p1.setAirSupply(Oxy < 10 ? 0 : Math.max(Oxy - 5, 0)); //# meh, good math but features
                }
            }
        }
    }
}
