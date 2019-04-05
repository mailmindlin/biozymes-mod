package biozymes.common.config;

import javax.annotation.ParametersAreNonnullByDefault;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.config.Configuration;

@ParametersAreNonnullByDefault
public interface ConfigEntry {
	/**
	 * Read from configuration file
	 * @param config
	 */
	void load(Configuration config);
	
	void write(ByteBuf config);
	
	void read(ByteBuf config);
}
