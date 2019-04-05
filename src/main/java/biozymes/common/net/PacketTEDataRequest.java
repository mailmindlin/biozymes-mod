package biozymes.common.net;

import biozymes.common.Biozymes;
import biozymes.common.capabilities.BZCapabilities;
import biozymes.common.capabilities.INetworkTile;
import biozymes.common.net.PacketTEDataRequest.TEDataRequestMessage;
import biozymes.common.net.PacketTEUpdate.TEUpdateMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTEDataRequest implements IMessageHandler<TEDataRequestMessage, IMessage>{

	@Override
	public TEUpdateMessage onMessage(TEDataRequestMessage message, MessageContext ctx) {
		EntityPlayer player = Biozymes.proxy.getPlayer(ctx);
		
		Biozymes.proxy.schedulePlayerTask(player, () -> {
			World world = DimensionManager.getWorld(message.dimId);
			if (world == null)
				return;
			
			TileEntity te = world.getTileEntity(message.pos);
			if (te == null)
				return;
			
			//Send TEUpdateMessage
			if (te.hasCapability(BZCapabilities.NETWORK_TILE_CAPABILITY, null)) {
				INetworkTile net = te.getCapability(BZCapabilities.NETWORK_TILE_CAPABILITY, null);
				Biozymes.packetHandler.sendTo(new TEUpdateMessage(message.dimId, message.pos, net.getNetworkData()), (EntityPlayerMP) player);
			}
		});
		return null;
	}
	
	public static class TEDataRequestMessage implements IMessage {
		public int dimId;
		public BlockPos pos;
		
		public TEDataRequestMessage() {
		}
		
		public TEDataRequestMessage(World world, BlockPos pos) {
			this(world.provider.getDimension(), pos);
		}
		
		public TEDataRequestMessage(int dimId, BlockPos pos) {
			this.dimId = dimId;
			this.pos = pos;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.dimId = buf.readInt();
			this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		}
		
		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(dimId);
			buf.writeInt(pos.getX());
			buf.writeInt(pos.getY());
			buf.writeInt(pos.getZ());
		}
	}
}
