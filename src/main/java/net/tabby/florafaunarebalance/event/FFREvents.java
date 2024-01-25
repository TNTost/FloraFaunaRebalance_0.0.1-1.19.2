package net.tabby.florafaunarebalance.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.core.custom.BuddingLog;
import net.tabby.florafaunarebalance.item.FFRii;
import net.tabby.florafaunarebalance.item.core.custom.ChuteItem;
import net.tabby.florafaunarebalance.util.FFRTags;



public class FFREvents {
    @Mod.EventBusSubscriber(modid = FloraFaunaRebalance.MOD_ID)
    public static class ForgeEvents {
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
                float drawTimeInTicks = 12.0f;
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
