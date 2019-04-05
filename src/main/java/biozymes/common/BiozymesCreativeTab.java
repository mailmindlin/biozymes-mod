package biozymes.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BiozymesCreativeTab extends CreativeTabs {
	public BiozymesCreativeTab() {
		super(Biozymes.MODID);
	}

	@Override
	public Item getTabIconItem() {
		return Items.RABBIT_FOOT;
	}
}
