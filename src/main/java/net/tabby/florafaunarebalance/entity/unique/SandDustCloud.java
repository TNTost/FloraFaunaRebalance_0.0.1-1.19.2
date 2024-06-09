package net.tabby.florafaunarebalance.entity.unique;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tabby.florafaunarebalance.entity.FFRenty;
import org.jetbrains.annotations.NotNull;

public class SandDustCloud extends Projectile {
    private static final EntityDataAccessor<Float> CLOUD_SIZE = SynchedEntityData.defineId(SandDustCloud.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> OPACITY_MODIFIER = SynchedEntityData.defineId(SandDustCloud.class, EntityDataSerializers.FLOAT);
    private int lifetime;
    private int life;

    public SandDustCloud(EntityType<? extends SandDustCloud> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }
    public SandDustCloud(Level level, float size, Vec3 vec) {
        super(FFRenty.DUST_CLOUD.get(), level);
        this.lifetime = 105;
        this.entityData.set(CLOUD_SIZE, size);
        this.setPos(vec);
    }
    public SandDustCloud(Level level, LivingEntity entity, float size) {
        this(level, size, entity.position());
        this.setOwner(entity);
    }
    protected void defineSynchedData() {
        this.entityData.define(CLOUD_SIZE, 1f);
        this.entityData.define(OPACITY_MODIFIER, 1f);
    }



    public void tick() {
        super.tick();

        Vec3 vec = this.getDeltaMovement();
        this.move(MoverType.SELF, vec);
        this.setDeltaMovement(vec.scale(-0.004));

        ++life;
        if (!this.level.isClientSide && this.life > this.lifetime - 9) {
            this.entityData.set(OPACITY_MODIFIER, Math.max(this.entityData.get(OPACITY_MODIFIER) - 0.125f, 0.0f));
            if (this.life > this.lifetime) {
                this.discard();
            }
        }
    }


    public double getOpacity() {
        return this.entityData.get(OPACITY_MODIFIER);
    }
    public float getSize() {
        return this.entityData.get(CLOUD_SIZE);
    }

    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("size", this.entityData.get(CLOUD_SIZE));
        tag.putDouble("opacity", this.entityData.get(OPACITY_MODIFIER));
        tag.putInt("lifetime", this.lifetime);
        tag.putInt("life", this.life);
    }
    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(CLOUD_SIZE, tag.getFloat("size"));
        this.entityData.set(OPACITY_MODIFIER, tag.getFloat("opacity"));
        this.lifetime = tag.getInt("lifetime");
        this.life = tag.getInt("life");
    }



    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
    //TODO: model...
}
