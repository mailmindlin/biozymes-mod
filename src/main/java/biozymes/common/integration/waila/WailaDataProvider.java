package biozymes.common.integration.waila;

import java.util.List;

import biozymes.common.integration.BiozymesHooks;
import biozymes.common.tile.IWailaTile;
import biozymes.common.tile.TECrop;
import biozymes.common.tile.TEIncubator;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional.Interface;
import net.minecraftforge.fml.common.Optional.Method;

@Interface(iface = "mcp.mobius.waila.api.IWailaDataProvider", modid = BiozymesHooks.WAILA_MOD_ID)
public class WailaDataProvider implements IWailaDataProvider {
	
	@Method(modid = BiozymesHooks.WAILA_MOD_ID)
	public static void register(IWailaRegistrar registrar) {
		WailaDataProvider provider = new WailaDataProvider();
		
		registrar.registerHeadProvider(provider, TECrop.class);
		registrar.registerBodyProvider(provider, TECrop.class);
		
		registrar.registerHeadProvider(provider, TEIncubator.class);
		registrar.registerBodyProvider(provider, TEIncubator.class);
	}
	
	@Override
	@Method(modid = BiozymesHooks.WAILA_MOD_ID)
	public ItemStack getWailaStack(IWailaDataAccessor itemStack, IWailaConfigHandler arg1) {
		return null;
	}
	
	@Override
	@Method(modid = BiozymesHooks.WAILA_MOD_ID)
	public List<String> getWailaHead(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		TileEntity tile = accessor.getTileEntity();
		
		if (tile instanceof IWailaTile) {
			IWailaTile te = (IWailaTile) tile;
			te.getWailaHead(currentTip, itemStack, accessor.getPlayer(), accessor.getWorld(), accessor.getPosition());
		}
		
		return currentTip;
	}
	
	@Override
	@Method(modid = BiozymesHooks.WAILA_MOD_ID)
	public List<String> getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		TileEntity tile = accessor.getTileEntity();
		
		if (tile instanceof IWailaTile) {
			IWailaTile te = (IWailaTile) tile;
			te.getWailaBody(currentTip, itemStack, accessor.getPlayer(), accessor.getWorld(), accessor.getPosition());
		}
		
		return currentTip;
	}
	
	@Override
	@Method(modid = BiozymesHooks.WAILA_MOD_ID)
	public List<String> getWailaTail(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		return currentTip;
	}
	
	@Override
	@Method(modid = BiozymesHooks.WAILA_MOD_ID)
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos) {
		return tag;
	}
}
