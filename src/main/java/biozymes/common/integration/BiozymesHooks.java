package biozymes.common.integration;

import net.minecraftforge.fml.common.Loader;

public class BiozymesHooks {
	public static final String WAILA_MOD_ID = "Waila";
	public static final String MEKANISM_MOD_ID = "mekanism";
	
	public boolean wailaLoaded = false;
	public boolean mekanismLoaded = false;
	
	public void hook() {
		if (Loader.isModLoaded(MEKANISM_MOD_ID)) {
			this.mekanismLoaded = true;
		}
	}
}
