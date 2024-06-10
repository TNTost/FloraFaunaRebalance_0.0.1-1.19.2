package net.tabby.florafaunarebalance.entity.unique.core;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tabby.florafaunarebalance.entity.FFRenty;
import net.tabby.florafaunarebalance.util.Math.Mh;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PhysicsEntity extends Entity {
    private static final EntityDataAccessor<CompoundTag> THROWER = SynchedEntityData.defineId(PhysicsEntity.class, EntityDataSerializers.COMPOUND_TAG);
    protected Player thrower;

    public PhysicsEntity(EntityType<PhysicsEntity> type, Level level) {
        super(type, level);
    }
    public PhysicsEntity(Level level, Player player) {
        this(level, player, new Vec3(player.getX(), player.getEyeY(), player.getZ()));
    }

    public PhysicsEntity(Level level, Player player, Vec3 pos) {
        super(FFRenty.SLING.get(), level);
        this.setPos(pos);
        this.setDeltaMovement(-Mth.sin(Mh.rad(player.getYRot())) * 0.05, 0, Mth.cos(Mh.rad(player.getYRot())) * 0.05);
        this.configure(player);

    }

    protected void configure(Player player) {
        this.thrower = player;
        setYRot(player.getYRot());
        setXRot(player.getXRot());
    }
    private boolean hasOwner(CompoundTag tag) {
        return tag.contains("thrower");
    }
    public boolean hasOwner() {
        return hasOwner(this.entityData.get(THROWER));
    }
    public Entity getOwner() {
        CompoundTag tag = this.entityData.get(THROWER);
        return hasOwner(tag) ? this.level.getPlayerByUUID(tag.getUUID("thrower")) : null;
    }

    @Override
    public void tick() {
        super.tick();
        this.move(MoverType.SELF, this.getDeltaMovement());
    }

    protected void defineSynchedData() {
        this.entityData.define(THROWER, new CompoundTag());
    }
    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {
        UUID uuid = this.thrower == null ? UUID.randomUUID() : this.thrower.getUUID();
        tag.putUUID("thrower", uuid);
    }
    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {
        this.entityData.set(THROWER, tag.getCompound("thrower"));
    }


    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this, this.getId());
    }
}
