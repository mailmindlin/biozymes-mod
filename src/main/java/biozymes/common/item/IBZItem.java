package biozymes.common.item;

import biozymes.common.Biozymes;
import net.minecraft.item.Item;

public interface IBZItem {
	String getName();
	
	default void registerItemModel() {
		Biozymes.proxy.registerItemRenderer((Item) this, 0, getName());
	}
}
