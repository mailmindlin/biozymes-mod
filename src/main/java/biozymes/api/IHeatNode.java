package biozymes.api;

import net.minecraft.util.EnumFacing;

public interface IHeatNode {
	/**
	 * Get temperature of side
	 * 
	 * @return Temperature (in K). Returns {@value Double#NaN} if not applicable.
	 */
	double getTemperature(EnumFacing side);
	
	/**
	 * Transfer heat to side
	 * 
	 * @param side
	 * @param heat
	 */
	void transferHeatTo(EnumFacing side, double heat);
	
	/**
	 * Get 1/conduction coefficient for the given side.
	 * @param side
	 * @return
	 */
	double getInverseConduction(EnumFacing side);
	
	double getInsulation(EnumFacing side);
	
	double applyTemperatureChange();
	
	/**
	 * If heat conductors
	 * @param side
	 * @return
	 */
	boolean canConnectHeat(EnumFacing side);
}
