package biozymes.common;

import com.google.common.util.concurrent.ListenableFuture;

import biozymes.common.config.BZConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {
	
	public void loadConfiguration(Configuration configuration) {
		BZConfig config = BZConfig.local();
		config.general.load(configuration);
		
		if (configuration.hasChanged())
			configuration.save();
	}
	
	/**
	 * Triggered when config is changed
	 * @param fromPacket
	 */
	public void onConfigSync(boolean fromPacket) {
		if (fromPacket)
			Biozymes.logger.info("Got config from server");
	}
	
	public void registerItemRenderer(Item item, int meta, String id) {
	}

	public void registerKeybinds() {
	}

	public void registerCommands(FMLServerStartingEvent event) {
	}
	
	public EntityPlayer getPlayer(MessageContext context) {
		return context.getServerHandler().playerEntity;
	}
	
	protected ListenableFuture<Object> scheduleServerPlayerTask(EntityPlayer player, Runnable task) {
		return ((WorldServer) player.worldObj).addScheduledTask(task);
	}

	public ListenableFuture<Object> schedulePlayerTask(EntityPlayer player, Runnable task) {
		if (player != null && player instanceof EntityPlayerMP)
			return this.scheduleServerPlayerTask(player, task);
		return null;
	}
	
	public boolean isShiftKeyDown() {
		return false;
	}
}
