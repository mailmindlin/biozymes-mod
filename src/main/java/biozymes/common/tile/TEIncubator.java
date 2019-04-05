package biozymes.common.tile;

import java.util.List;
import java.util.Set;

import biozymes.api.IHeatNode;
import biozymes.api.genetics.IIndividual;
import biozymes.common.capabilities.BZCapabilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public class TEIncubator extends TEBase implements IHeatNode, IWailaTile {
	public double temperature = 300.0;
	public double heatToAbsorb = 0;
	
	Set<IIndividual> cultureContents;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == BZCapabilities.HEAT_NODE)
			return true;
		
		return super.hasCapability(capability, facing);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == BZCapabilities.HEAT_NODE)
			return (T) this;
		
		return super.getCapability(capability, facing);
	}
	
	@Override
	public double getTemperature(EnumFacing side) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void transferHeatTo(EnumFacing side, double heat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getInverseConduction(EnumFacing side) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double applyTemperatureChange() {
		this.temperature += this.heatToAbsorb;
		this.heatToAbsorb = 0;
		
		return this.temperature;
	}

	@Override
	public boolean canConnectHeat(EnumFacing side) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public double getInsulation(EnumFacing side) {
		return 500;
	}
	
	@Override
	public List<String> getWailaBody(List<String> currentTip, ItemStack itemStack, EntityPlayer player, World world, BlockPos pos) {
		currentTip.add(String.format("%d K", this.temperature));
		
		return currentTip;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		
		compound.setDouble("temperature", this.temperature);
		
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		if (compound.hasKey("temperature"))
			this.temperature = compound.getDouble("temperature");
		
		super.readFromNBT(compound);
	}
}
