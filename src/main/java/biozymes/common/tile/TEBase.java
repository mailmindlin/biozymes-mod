package biozymes.common.tile;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import biozymes.common.Biozymes;
import biozymes.common.capabilities.BZCapabilities;
import biozymes.common.capabilities.INetworkTile;
import biozymes.common.net.PacketTEDataRequest.TEDataRequestMessage;
import biozymes.common.net.PacketTEUpdate.TEUpdateMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class TEBase extends TileEntity implements INetworkTile {
	Set<EntityPlayer> playersUsing = new HashSet<>();
	
	@Override
	public void markDirty() {
		super.markDirty();
		
		// Broadcast update packet(s)
		if (this.worldObj != null && !this.worldObj.isRemote && !this.playersUsing.isEmpty())
			Biozymes.packetHandler.sendTo(new TEUpdateMessage(this.worldObj, this.pos, this.getNetworkData()), this.playersUsing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		if (capability == BZCapabilities.NETWORK_TILE_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
	
	@Nullable
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == BZCapabilities.NETWORK_TILE_CAPABILITY)
			return (T) this;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void validate() {
		super.validate();
		
		// Ask server for updated information
		if (worldObj.isRemote)
			Biozymes.packetHandler.sendToServer(new TEDataRequestMessage(this.worldObj, this.pos));
	}
	
	/**
	 * Call when a player starts using this TE, and should start receiving
	 * network updates (e.g., when they open its GUI).
	 * 
	 * @param player Player subscribing to update events
	 */
	public void open(EntityPlayer player) {
		playersUsing.add(player);
	}
	
	/**
	 * Call when a player should no longer receive network updates (e.g., they close a GUI).
	 * @param player Player to unsubscribe
	 */
	public void close(EntityPlayer player) {
		playersUsing.remove(player);
	}
	
	@Override
	public void onPacket(NBTTagCompound data) throws Exception {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
			this.readFromNBT(data);
	}
	
	@Override
	public NBTTagCompound writeNetworkNBT(NBTTagCompound nbt) {
		return this.writeToNBT(nbt);
	}
}
