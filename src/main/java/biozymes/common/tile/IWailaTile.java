package biozymes.common.tile;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Wrapper for TileEntities that wish to provide WAILA tooltips.
 * @author mailmindlin
 */
public interface IWailaTile {
	@SideOnly(Side.CLIENT)
	default List<String> getWailaHead(List<String> currentTip, ItemStack itemStack, EntityPlayer player, World world, BlockPos pos) {
		return currentTip;
	}
	
	@SideOnly(Side.CLIENT)
	default List<String> getWailaBody(List<String> currentTip, ItemStack itemStack, EntityPlayer player, World world, BlockPos pos) {
		return currentTip;
	}

	@SideOnly(Side.CLIENT)
	default List<String> getWailaTail(List<String> currentTip, ItemStack itemStack, EntityPlayer player, World world, BlockPos pos) {
		return currentTip;
	}
}
