package biozymes.common.capabilities;

import biozymes.api.IHeatNode;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class BZCapabilities {
	@CapabilityInject(IHeatNode.class)
	public static Capability<IHeatNode> HEAT_NODE = null;
	
	@CapabilityInject(INetworkTile.class)
	public static Capability<INetworkTile> NETWORK_TILE_CAPABILITY = null;
	
	public static void register() {
		DefaultHeatNode.register();
		DefaultNetworkTile.register();
	}
}
