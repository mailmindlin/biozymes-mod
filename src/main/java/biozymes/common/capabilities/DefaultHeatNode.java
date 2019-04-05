package biozymes.common.capabilities;

import biozymes.api.IHeatNode;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class DefaultHeatNode implements IHeatNode {
	public static void register() {
		CapabilityManager.INSTANCE.register(IHeatNode.class, new NullStorage<>(), DefaultHeatNode::new);
	}

	@Override
	public double getTemperature(EnumFacing side) {
		return 0;
	}

	@Override
	public double getInverseConduction(EnumFacing side) {
		return 0;
	}

	@Override
	public double getInsulation(EnumFacing side) {
		return 0;
	}

	@Override
	public void transferHeatTo(EnumFacing side, double heat) {
		
	}

	@Override
	public double applyTemperatureChange() {
		return 0;
	}

	@Override
	public boolean canConnectHeat(EnumFacing side) {
		return false;
	}
	
}
