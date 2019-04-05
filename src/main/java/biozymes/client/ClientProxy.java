package biozymes.client;

import org.lwjgl.input.Keyboard;

import com.google.common.util.concurrent.ListenableFuture;

import biozymes.common.Biozymes;
import biozymes.common.CommonProxy;
import biozymes.common.config.BZConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void loadConfiguration(Configuration configuration) {
		super.loadConfiguration(configuration);
		
		BZConfig.current().client.load(configuration);
		
		if (configuration.hasChanged())
			configuration.save();
	}
	
	@Override
	public void registerCommands(FMLServerStartingEvent event) {
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Biozymes.MODID + ":" + id, "inventory"));
	}

	@Override
	public void registerKeybinds() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityPlayer getPlayer(MessageContext context) {
		if (FMLCommonHandler.instance().getEffectiveSide().isServer())
			return super.getPlayer(context);
		
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public ListenableFuture<Object> schedulePlayerTask(EntityPlayer player, Runnable task) {
		if (player == null || player.worldObj.isRemote)
			return Minecraft.getMinecraft().addScheduledTask(task);
		else if (player != null && !player.worldObj.isRemote)
			return this.scheduleServerPlayerTask(player, task);
		return null;
	}
	
	@Override
	public boolean isShiftKeyDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
	}
}
