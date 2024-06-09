package net.tabby.florafaunarebalance.entity.unique.core;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tabby.florafaunarebalance.entity.FFRenty;
import org.jetbrains.annotations.NotNull;

public class PhysicsEntity extends Entity {
    protected Player player;

    public PhysicsEntity(EntityType<PhysicsEntity> type, Level level) {
        super(type, level);
    }
    public PhysicsEntity(Level level, Player player) {
        super(FFRenty.SLING.get(), level);
        this.player = player;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
