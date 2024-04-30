package net.tabby.florafaunarebalance.block.entity.unique.menu.unique;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.block.entity.unique.HollowLogEntity;
import net.tabby.florafaunarebalance.block.entity.unique.menu.FFRmt;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class HollowLogMenu extends AbstractContainerMenu {
    public final HollowLogEntity be;
    private final Level level;

    public HollowLogMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), Optional.empty());
    }
    public HollowLogMenu(int id, Inventory inv, BlockEntity entity, Optional<ContainerData> optlData) {
        super(FFRmt.HOLLOW_LOG_MENU.get(), id);
        be = (HollowLogEntity) entity;
        ContainerData data = optlData.orElseGet(() -> new SimpleContainerData(be.serializeNBT().getInt("size")));
        checkContainerSize(inv, 7); //# inv.getContainerSize() is somehow only 41? // 41 is inventory size, 4 armor + shield + 36 slots... ... .
        this.level = inv.player.level;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> { //# TODO: returns 0, nbt is either not correctly gathered or stored.
            System.out.println("################################################################################# - " + be.serializeNBT().getInt("size") + " - #############################################################################");
            for (int i = 0; i < be.serializeNBT().getInt("size"); i++) { //# accessing index 'i' is out of bounds as size 0... .
                this.addSlot(new SlotItemHandler(iItemHandler, i,  1 + (i % 9) * 18, 9 + 1 + Mth.floor(i / 9f) * 18 )); //# creates slots based on any N
            }
            //this.addSlot(new SlotItemHandler(iItemHandler, 0, 52 + 1, 9 + 1));
            //this.addSlot(new SlotItemHandler(iItemHandler, 1, 70 + 1, 9 + 1));
            //this.addSlot(new SlotItemHandler(iItemHandler, 2, 88 + 1, 9 + 1));
            //this.addSlot(new SlotItemHandler(iItemHandler, 3, 106 + 1, 9 + 1));
        });
        addDataSlots(data);
    }


    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < 36) { //# this.size == 7.
            if (!moveItemStackTo(sourceStack, 36, 36 + 7, false)) return ItemStack.EMPTY;
        } else if (index < 36 + 7) {
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
        return ContainerLevelAccess.create(level, be.getBlockPos()).evaluate((level, pos) -> {
            BlockState s = level.getBlockState(pos);
            return (s.is(FFRib.VALYRIAN_HOLLOW_LOG.get()) || s.is(FFRib.ELYSIAN_HOLLOW_LOG.get()) || s.is(FFRib.ENHANCED_VALYRIAN_HOLLOW_LOG.get())) && player.distanceToSqr(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d) <= 64d;
        }, true); //# checks if blockEntity came is part of one of these ^^                 ^^ AND exits out when false.
    }

    private void addPlayerInventory(Inventory inv) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int index = 9 + j + i * 9;
                this.addSlot(new Slot(inv, index, 8 + j * 18, 86 + i * 18 + Mth.floor(7 / 9f) * 18));
            }
        }
    }
    private void addPlayerHotbar(Inventory inv) {
        for (int l = 0; l < 9; l++) {
            this.addSlot(new Slot(inv, l, 8 + l * 18, 144 + Mth.floor(7 / 9f) * 18));
        }
    }
}
