package net.tabby.florafaunarebalance.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.network.NetworkEvent;
import net.tabby.florafaunarebalance.entity.unique.core.PhysicsEntity;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class EntityC2SPacket {
    public EntityC2SPacket() {
    }
    public EntityC2SPacket(FriendlyByteBuf byt) {
    }

    public void toByte(FriendlyByteBuf byt) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        AtomicBoolean entitySummoned = new AtomicBoolean(false); // nuclear-powered bool >:3..
        context.enqueueWork(() -> { //# server-side..
            ServerLevel level = context.getSender().getLevel(); //# send packet defensively due yto mal-usage of packet sendage..
            entitySummoned.set(level.addFreshEntity(new PhysicsEntity(level, context.getSender())));
        });
        return entitySummoned.get();
    }
}
