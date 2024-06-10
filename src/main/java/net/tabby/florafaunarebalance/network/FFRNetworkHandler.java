package net.tabby.florafaunarebalance.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.tabby.florafaunarebalance.network.packet.EntityC2SPacket;
import net.tabby.florafaunarebalance.util.FFR.F;

public class FFRNetworkHandler {
    private static SimpleChannel INSTANCE;

    private static int pktId = 0;
    private static int id() {
        return pktId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(F.lr("packets")).networkProtocolVersion(() -> "9.0")
                .clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true).simpleChannel();
        INSTANCE = net;
        net.messageBuilder(EntityC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER).decoder(EntityC2SPacket::new).encoder(EntityC2SPacket::toByte)
                .consumerMainThread(EntityC2SPacket::handle).add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
