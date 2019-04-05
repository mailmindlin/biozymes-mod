package biozymes.common.config;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public abstract class ConfigOption<T> implements ConfigEntry {
	protected final String key;
	protected final String comment;
	protected final String category;
	protected final boolean requiresMcRestart = false;
	protected final boolean requiresWorldRestart = false;
	
	public ConfigOption(String key, String comment, String category) {
		super();
		this.key = key;
		this.comment = comment;
		this.category = category;
	}
	
	protected abstract Property getProperty(Configuration config);
	
	protected abstract void doLoad(Property property);
	
	@Override
	public void load(Configuration config) {
		Property prop = this.getProperty(config);
		
		prop.setRequiresMcRestart(this.requiresMcRestart);
		prop.setRequiresWorldRestart(this.requiresWorldRestart);
		
		this.doLoad(prop);
	}
	
	public static class IntOption extends ConfigOption<Integer> {
		protected final int defaultValue;
		protected final int min;
		protected final int max;
		protected int value;
		
		public IntOption(String key, String comment, String category, int defaultValue, int min, int max) {
			super(key, comment, category);
			
			this.defaultValue = defaultValue;
			this.min = min;
			this.max = max;
		}
		
		public int get() {
			return this.value;
		}
		
		public void set(int value) {
			this.value = value;
		}

		@Override
		protected void doLoad(Property property) {
			this.value = property.getInt();
		}

		@Override
		protected Property getProperty(Configuration config) {
			return config.get(this.category, this.key, this.defaultValue, this.comment, this.min, this.max);
		}

		@Override
		public void write(ByteBuf config) {
			config.writeInt(this.value);
		}

		@Override
		public void read(ByteBuf config) {
			this.value = config.readInt();
		}
	}
}
