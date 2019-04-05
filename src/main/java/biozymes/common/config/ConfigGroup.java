package biozymes.common.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.config.Configuration;

public class ConfigGroup implements ConfigEntry {
	private List<ConfigEntry> children;
	
	public ConfigGroup() {
		this.children = new ArrayList<>();
		
		for (Field field : this.getClass().getDeclaredFields()) {
			//TODO: fix ordering
			if (field.getType().isInstance(ConfigEntry.class)) {
				try {
					children.add((ConfigEntry) field.get(this));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				continue;
			}
			
			ConfigField config = field.getAnnotation(ConfigField.class);
			if (config == null)
				continue;
			
			if (field.getType().isInstance(Integer.TYPE)) {
				//TODO: finish
			}
		}
	}
	
	public ConfigGroup(ConfigEntry...children) {
		this.children = Arrays.asList(children);
	}

	@Override
	public void load(Configuration config) {
		for (ConfigEntry child : this.children)
			child.load(config);
	}

	@Override
	public void write(ByteBuf config) {
		for (ConfigEntry child : this.children)
			child.write(config);
	}

	@Override
	public void read(ByteBuf config) {
		for (ConfigEntry child : this.children)
			child.read(config);
	}
	
}
