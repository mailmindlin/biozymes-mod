package biozymes.common.net;

import java.io.IOException;

import biozymes.common.Biozymes;
import biozymes.common.capabilities.BZCapabilities;
import biozymes.common.capabilities.INetworkTile;
import biozymes.common.net.PacketTEUpdate.TEUpdateMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTEUpdate implements IMessageHandler<TEUpdateMessage, TEUpdateMessage>{
	@Override
	public TEUpdateMessage onMessage(TEUpdateMessage message, MessageContext ctx) {
		EntityPlayer player = Biozymes.proxy.getPlayer(ctx);
		if (player == null)
			return null;//Why?
		
		Biozymes.proxy.schedulePlayerTask(player, () -> {
			TileEntity te = player.worldObj.getTileEntity(message.pos);
			if (te.hasCapability(BZCapabilities.NETWORK_TILE_CAPABILITY, null)) {
				INetworkTile net = te.getCapability(BZCapabilities.NETWORK_TILE_CAPABILITY, null);
				try {
					net.onPacket(message.nbt);
				} catch (Exception e) {
					System.err.println("Error in handling TEUpdateMessage");
					e.printStackTrace();
				}
			}
		});
		
		return null;
	}
	
	public static class TEUpdateMessage implements IMessage {
		int dimId;
		BlockPos pos;
		NBTTagCompound nbt;
		
		public TEUpdateMessage() {
		}
		
		public TEUpdateMessage(World world, BlockPos pos, NBTTagCompound nbt) {
			this(world.provider.getDimension(), pos, nbt);
		}
		
		public TEUpdateMessage(int dimId, BlockPos pos, NBTTagCompound nbt) {
			this.dimId = dimId;
			this.pos = pos;
			this.nbt = nbt;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.dimId = buf.readInt();
			this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
			try {
				this.nbt = new ByteBufDataIO(buf).readNBT();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(dimId);
			buf.writeInt(pos.getX());
			buf.writeInt(pos.getY());
			buf.writeInt(pos.getZ());
			try {
				new ByteBufDataIO(buf).writeNBT(this.nbt);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
