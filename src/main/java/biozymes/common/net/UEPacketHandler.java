package biozymes.common.net;

import biozymes.common.net.PacketTEDataRequest.TEDataRequestMessage;
import biozymes.common.net.PacketTEUpdate.TEUpdateMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class UEPacketHandler {
	public SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel("UE0");
	public void register() {
		channel.registerMessage(PacketTEUpdate.class, TEUpdateMessage.class, 0, Side.CLIENT);
		channel.registerMessage(PacketTEUpdate.class, TEUpdateMessage.class, 0, Side.SERVER);
		
		channel.registerMessage(PacketTEDataRequest.class, TEDataRequestMessage.class, 1, Side.SERVER);
		//netHandler.registerMessage(PacketAnalyzerGui.class, AnalyzerGuiMessage.class, 0, Side.CLIENT);
	}
	
	public void sendTo(IMessage message, EntityPlayerMP player) {
		channel.sendTo(message, player);
	}
	
	public void sendTo(IMessage message, Iterable<EntityPlayer> players) {
		for(EntityPlayer player : players)
			sendTo(message, (EntityPlayerMP)player);
	}
	
	public void broadcast(IMessage message) {
		channel.sendToAll(message);
	}
	
	public void sendToServer(IMessage message) {
		channel.sendToServer(message);
	}
	
}
