package net.tabby.florafaunarebalance.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.block.core.unique.BuddingLog;
import net.tabby.florafaunarebalance.item.FFRii;
import net.tabby.florafaunarebalance.item.core.unique.ChuteItem;
import net.tabby.florafaunarebalance.util.FFRTags;
import net.tabby.florafaunarebalance.util.all.Mh;


public class FFRForgeEvents {
    @Mod.EventBusSubscriber(modid = FloraFaunaRebalance.MOD_ID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onPlaceLilyAtWater(PlayerInteractEvent.RightClickItem event) {
            if (!event.getLevel().isClientSide && event.getItemStack().is(FFRib.NYMPHAEACEAE.get().asItem())) {
                // TODO: how much x &y &z it moves for each step forward...
                Player ply = event.getEntity();

                float xRot = ply.getXRot(); //upwards rotation
                float yRot = ply.getYRot(); //sideways angle

                double pY = ply.getY() + ply.getEyeHeight(); //height of eyes/ray start
                double dy = pY - Mth.floor(pY);

                float tan1 = 1 / Mh.tan(xRot);
                double IntcVecY = -dy * tan1;
                double xIntcYp = IntcVecY * -Mth.sin(Mh.rad(yRot));
                double zIntcYp = IntcVecY * Mth.cos(Mh.rad(yRot));
                double deltaX = tan1 * -Mth.sin(Mh.rad(yRot));
                double deltaZ = tan1 * Mth.cos(Mh.rad(yRot));
                for (int i = 0; i < 5; i++) {
                    event.getLevel().setBlockAndUpdate(new BlockPos(ply.getX() + xIntcYp, pY, ply.getZ() + zIntcYp).above(), Blocks.DIRT.defaultBlockState());
                    xIntcYp -= deltaX;
                    zIntcYp -= deltaZ;
                    pY += 1;
                }
                System.out.println(IntcVecY);

                //BlockPos waterAt = getWaterHitPos(event.getLevel(), event.getEntity());
                //System.out.println(waterAt);
                 //when marching forward by dx ORdy ORdz, check if hit block in direction of axis [if checking next Y, check block above intersect..]
            }
        }

        public static BlockPos getWaterHitPos(Level lvl, Player ply) {
            Vec3 hajime = new Vec3(ply.getX(), ply.getY() + ply.getEyeHeight(), ply.getZ());
            Vec3 mato = hajime.add(ply.getViewVector(1.0f).scale(5));

            if (!Double.isNaN(hajime.x) && !Double.isNaN(hajime.y) && !Double.isNaN(hajime.z) && !Double.isNaN(mato.x) && !Double.isNaN(mato.y) && !Double.isNaN(mato.z)) {
                double dx = hajime.x - Mth.floor(hajime.x);
                double dy = hajime.y - Mth.floor(hajime.y);
                double dz = hajime.z - Mth.floor(hajime.z);
                //TODO: for 2d, Y-intercept at -dy, -dy / tan(theta) of x; theta being upwards view angle;. further points are 1 of y, 1/tan(theta) of x apart...
                //TODO: first X-intercept at -dx * tan(theta) of y, dx; further points -tan(theta) of y, 1 of x apart...
                //TODO negate based on quadrant / adjust sign data..
            }
            return null;
        }

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
