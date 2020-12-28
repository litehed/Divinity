package divine.packets;

import divine.packets.client.ClassPacket;
import divine.packets.client.RacePacket;
import divine.packets.server.ClassServerPacket;
import divine.packets.server.RaceServerPacket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketDispatcher {
	
	private static byte packetId = 0;
	
	private static final SimpleNetworkWrapper CHANNELINSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("divine");
	
	public static final void registerPackets() {
		registerMessage(RacePacket.class);
		registerMessage(RaceServerPacket.class);
		
		registerMessage(ClassPacket.class);
		registerMessage(ClassServerPacket.class);
	}
	
	private static final <T extends AbstractMessage<T> & IMessageHandler<T, IMessage>> void registerMessage(Class<T> clazz) {
		if(AbstractMessage.AbstractClientMessage.class.isAssignableFrom(clazz)) {
			PacketDispatcher.CHANNELINSTANCE.registerMessage(clazz, clazz, packetId++, Side.CLIENT);
		}else if(AbstractMessage.AbstractServerMessage.class.isAssignableFrom(clazz)) {
			PacketDispatcher.CHANNELINSTANCE.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		}else {
			PacketDispatcher.CHANNELINSTANCE.registerMessage(clazz, clazz, packetId, Side.CLIENT);
			PacketDispatcher.CHANNELINSTANCE.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		}
	}
	
	public static final void sendTo(IMessage message, EntityPlayerMP player) {
		PacketDispatcher.CHANNELINSTANCE.sendTo(message, player);
	}
	public static void sendToAll(IMessage message) {
		PacketDispatcher.CHANNELINSTANCE.sendToAll(message);
	}
	public static final void sendToDimension(IMessage message, int dimensionId) {
		PacketDispatcher.CHANNELINSTANCE.sendToDimension(message, dimensionId);
	}
	public static final void sendToServer(IMessage message) {
		PacketDispatcher.CHANNELINSTANCE.sendToServer(message);
	}
}
