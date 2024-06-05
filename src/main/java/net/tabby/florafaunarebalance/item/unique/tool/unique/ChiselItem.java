package net.tabby.florafaunarebalance.item.unique.tool.unique;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolAction;
import net.tabby.florafaunarebalance.item.unique.tool.FFRta;
import net.tabby.florafaunarebalance.util.FFR.FFRTags;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ChiselItem extends DiggerItem {
    public ChiselItem(Tier tier, float damage, float speed, Properties p_204112_) {
        super(damage, speed, tier, FFRTags.tool.MINEABLE_WITH_CHISEL, p_204112_);
    }

    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Player player = context.getPlayer();
        Optional<BlockState> opt = Optional.ofNullable(state.getToolModifiedState(context, FFRta.CHISEL_HOLLOW, false));
        ItemStack item = context.getItemInHand();
        if (opt.isPresent()) {
            level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 0.5f, 0.5f);
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, item);
            }
            level.setBlock(pos, opt.get(), 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, opt.get()));
            if (player != null) {
                item.hurtAndBreak(1, player, e -> {
                    e.broadcastBreakEvent(context.getHand());
                });
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else return InteractionResult.PASS;
    }
}
