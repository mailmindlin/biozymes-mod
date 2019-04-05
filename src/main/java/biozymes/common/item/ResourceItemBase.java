package biozymes.common.item;

import biozymes.common.Biozymes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ResourceItemBase extends Item {
	protected final String name;
	
	public ResourceItemBase(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	public void registerItemModel() {
		Biozymes.proxy.registerItemRenderer(this, 0, name);
	}
}
