package biozymes.common;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import biozymes.common.block.BiozymesBlocks;
import biozymes.common.capabilities.BZCapabilities;
import biozymes.common.gui.BZGuiHandler;
import biozymes.common.integration.BiozymesHooks;
import biozymes.common.item.BiozymesItems;
import biozymes.common.net.UEPacketHandler;
import biozymes.common.tile.BiozymesTileEntities;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Base mod class
 * 
 * @author mailmindlin
 */
@Mod(
		modid = Biozymes.MODID,
		version = Biozymes.VERSION,
		dependencies = "after:mekanism;"
						//+ "after:ic2;"
						+ "after:jei")
@Mod.EventBusSubscriber()
public class Biozymes {
	public static final String MODID = "biozymes";
	public static final String NAME = "Biozymes";
	public static final String VERSION = "0.0";
	
	public static Logger logger = LogManager.getLogger(Biozymes.NAME);
	
	
	//Proxy
	@SidedProxy(serverSide="biozymes.common.CommonProxy", clientSide="biozymes.client.ClientProxy")
	public static CommonProxy proxy;
	
	@Instance(MODID)
	public static Biozymes instance;
	
	public final BiozymesCreativeTab creativeTab = new BiozymesCreativeTab();
	
	public static final BiozymesHooks hooks = new BiozymesHooks();
	
	public static UEPacketHandler packetHandler = new UEPacketHandler();
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		BiozymesBlocks.registerBlocks(event.getRegistry());
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		BiozymesBlocks.registerItemBlocks(event.getRegistry());
		BiozymesItems.registerItems(event.getRegistry());
		//TODO: oredict?
	}
	
	@SubscribeEvent
	public static void createRegistries(RegistryEvent.NewRegistry event) {
		
	}
	
	public static void registerOreDictionary() {
		
	}
	
	public Configuration config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		this.config = new Configuration(event.getSuggestedConfigurationFile());
		proxy.loadConfiguration(this.config);
		
		//Register GUI handler
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new BZGuiHandler());
		
		BZCapabilities.register();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		// some example code
		BZRecipes.init();
		
		BiozymesTileEntities.register();
		
		// Waila integration
		FMLInterModComms.sendMessage(BiozymesHooks.WAILA_MOD_ID, "register", "biozymes.common.integration.WailaDataProvider.register");
		
		Biozymes.packetHandler.register();
		
		logger.info("%s loaded", NAME);
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		proxy.registerCommands(event);
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (Objects.equals(Biozymes.MODID, event.getModID())) {
			logger.info("Reloading config");
			proxy.loadConfiguration(this.config);
			proxy.onConfigSync(false);
		}
	}
}
