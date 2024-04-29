package net.tabby.florafaunarebalance.block.entity.unique.menu.unique;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.block.core.unique.HollowLog;
import net.tabby.florafaunarebalance.block.entity.unique.HollowLogEntity;
import net.tabby.florafaunarebalance.block.entity.unique.menu.FFRmt;
import org.jetbrains.annotations.NotNull;

public class HollowLogMenu extends AbstractContainerMenu {
    public final HollowLogEntity be;
    private final Level level;
    private final ContainerData data;
    public HollowLogMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(HollowLog.SIZE));
    }
    public HollowLogMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(FFRmt.HOLLOW_LOG_MENU.get(), id);
        checkContainerSize(inv, HollowLog.SIZE);
        be = (HollowLogEntity) entity;
        this.level = inv.player.level;
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 52 + 1, 9 + 1));
            this.addSlot(new SlotItemHandler(iItemHandler, 1, 70 + 1, 9 + 1));
            this.addSlot(new SlotItemHandler(iItemHandler, 2, 88 + 1, 9 + 1));
            this.addSlot(new SlotItemHandler(iItemHandler, 3, 106 + 1, 9 + 1));
        });
        addDataSlots(data);
    }


    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < 36) {
            if (!moveItemStackTo(sourceStack, 36, 36 + HollowLog.SIZE, false)) return ItemStack.EMPTY;
        } else if (index < 36 + HollowLog.SIZE) {
            if (!moveItemStackTo(sourceStack, 0, 36, false)) return ItemStack.EMPTY;
        } else {
            System.out.println("invalid slot index" + index);
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else sourceSlot.setChanged();
        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }


    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(level, be.getBlockPos()), player, FFRib.HOLLOW_LOG.get());
    }

    private void addPlayerInventory(Inventory inv) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }
    }
    private void addPlayerHotbar(Inventory inv) {
        for (int l = 0; l < 9; l++) {
            this.addSlot(new Slot(inv, l, 8 + l * 18, 144));
        }
    }
}
