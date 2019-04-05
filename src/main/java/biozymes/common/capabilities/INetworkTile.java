package biozymes.common.capabilities;

import net.minecraft.nbt.NBTTagCompound;

public interface INetworkTile {
	/**
	 * Called when receiving a packet
	 * 
	 * @param data
	 *            Data contained in packet
	 * @throws Exception 
	 */
	void onPacket(NBTTagCompound data) throws Exception;
	
	default NBTTagCompound getNetworkData() {
		return writeNetworkNBT(new NBTTagCompound());
	}
	
	NBTTagCompound writeNetworkNBT(NBTTagCompound nbt);
}
