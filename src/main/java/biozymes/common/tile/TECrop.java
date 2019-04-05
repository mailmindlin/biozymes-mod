package biozymes.common.tile;

import java.util.List;

import biozymes.api.genetics.IGenome;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TECrop extends TileEntity implements IWailaTile {
	IGenome genome;
	
	public int getMaxAge() {
		return 7;
	}
	
	public float getGrowthChance() {
		return 0.5f;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public List<String> getWailaHead(List<String> currentTip, ItemStack itemStack, EntityPlayer player, World world, BlockPos pos) {
		// TODO Auto-generated method stub
		return currentTip;
	}
	
	
	public String getName() {
		return null;
	}
}
