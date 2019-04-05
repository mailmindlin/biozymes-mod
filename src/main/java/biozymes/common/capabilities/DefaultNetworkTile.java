package biozymes.common.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class DefaultNetworkTile implements INetworkTile {
	public static void register() {
		CapabilityManager.INSTANCE.register(INetworkTile.class, new NullStorage<>(), DefaultNetworkTile::new);
	}
	
	@Override
	public void onPacket(NBTTagCompound data) throws Exception {
	}
	
	@Override
	public NBTTagCompound writeNetworkNBT(NBTTagCompound nbt) {
		return nbt;
	}
}
