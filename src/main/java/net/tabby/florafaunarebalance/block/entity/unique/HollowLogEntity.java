package net.tabby.florafaunarebalance.block.entity.unique;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tabby.florafaunarebalance.block.core.unique.HollowLog;
import net.tabby.florafaunarebalance.block.entity.FFRbe;
import net.tabby.florafaunarebalance.block.entity.unique.menu.unique.HollowLogMenu;
import org.jetbrains.annotations.NotNull;

public class HollowLogEntity extends RandomizableContainerBlockEntity implements MenuProvider {

    private NonNullList<ItemStack> items = NonNullList.withSize(HollowLog.SIZE, ItemStack.EMPTY);
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
            HollowLogEntity.this.playSound(state, SoundEvents.AZALEA_LEAVES_HIT);
        }
        @Override
        protected void onClose(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
            HollowLogEntity.this.playSound(state, SoundEvents.AZALEA_FALL);
        }
        protected void openerCountChanged(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, int p_155466_, int p_155467_) {
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof ChestMenu) {
                Container container = (((ChestMenu) player.containerMenu).getContainer());
                return container == HollowLogEntity.this;
            } else return false;
        }
    };

    protected final ContainerData data;
    public HollowLogEntity(BlockPos pos, BlockState state) {
        super(FFRbe.HOLLOW_LOG_BE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int p_39284_) {
                return 0;
            }
            @Override
            public void set(int p_39285_, int p_39286_) {

            }
            @Override
            public int getCount() {
                return 0;
            }
        };
    }
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items);
        }
    }
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items);
        }
    }

    public int getContainerSize() {
        return this.items.size();
    }
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }
    protected void setItems(@NotNull NonNullList<ItemStack> itemStacks) {
        this.items = itemStacks;
    }

    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.hollow_log");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory inv) {
        return new HollowLogMenu(id, inv, this, this.data);
    }

    public void startOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }
    public void stopOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }
    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }



    void updateBlockState(BlockState state, boolean cnd) {
        //this.level.setBlock(this.getBlockPos(), state.setValue())
    }

    void playSound(BlockState state, SoundEvent sound) {
        Vec3i vec3i = state.getValue(HollowLog.FACING).getNormal();
        double d0 = (double)this.worldPosition.getX() + 0.5D + (double)vec3i.getX() / 2.0D;
        double d1 = (double)this.worldPosition.getY() + 0.5D + (double)vec3i.getY() / 2.0D;
        double d2 = (double)this.worldPosition.getZ() + 0.5D + (double)vec3i.getZ() / 2.0D;
        this.level.playSound(null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, HollowLogEntity hollowLogEntity) {
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(HollowLog.SIZE);
        for (ItemStack itemStack : this.items) {
            inv.addItem(itemStack);
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }
}
