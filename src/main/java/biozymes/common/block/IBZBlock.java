package biozymes.common.block;

import biozymes.common.Biozymes;
import net.minecraft.item.ItemBlock;

public interface IBZBlock {
	String getName();
	
	default void registerItemModel(ItemBlock itemBlock) {
		Biozymes.proxy.registerItemRenderer(itemBlock, 0, this.getName());
	}
}
