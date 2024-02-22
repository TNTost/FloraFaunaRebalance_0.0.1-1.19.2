package net.tabby.florafaunarebalance.entity.unique;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SandDustCloud extends Entity {
    public SandDustCloud(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }
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
    //TODO: model...
}
