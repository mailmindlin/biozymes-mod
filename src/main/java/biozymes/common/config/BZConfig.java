package biozymes.common.config;

import java.nio.file.Path;

import net.minecraftforge.common.config.Configuration;

public class BZConfig {
	private static BZConfig LOCAL = new BZConfig();
	private static BZConfig SERVER;
	
	public static boolean SHOW_ASPECT_VECTOR;
	public static boolean LOG_ASPECT_CALCS;
	public static float ENTROPY_STDDEV;
	public static float HALFLIFE_MODIFIER;
	
	public static void init(Path configFile) {
		Configuration config = new Configuration(configFile.toFile());
		
		try {
			config.load();
			
			SHOW_ASPECT_VECTOR = config.getBoolean("showAspectVector", "misc", false, "Enable showing aspect vector on tooltip");
			
			LOG_ASPECT_CALCS = config.getBoolean("verboseAspectLogging", "general", false, "Log all aspect calculations");
			
			ENTROPY_STDDEV = config.getFloat("entropyStandardDeviation", "worldgen", 2.5f, 0.0001f, 1000.0f, "Standard deviation for generated entropy values");
			
			HALFLIFE_MODIFIER = config.getFloat("halflifeModifier", "destructionator", 1.0f, 0.0f, Float.POSITIVE_INFINITY, "Multiplier for destructinator halflife");
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}
	
	public static BZConfig current() {
		return BZConfig.SERVER == null ? BZConfig.LOCAL : BZConfig.SERVER;
	}
	
	public static BZConfig local() {
		return BZConfig.LOCAL;
	}
	
	public final GeneralConfig general = new GeneralConfig();
	public final ClientConfig client = new ClientConfig();
}
