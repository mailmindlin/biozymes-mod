package biozymes.common.item;

import biozymes.common.Biozymes;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

@ObjectHolder("biozymes")
public class BiozymesItems {
	ItemCornCob corn = new ItemCornCob();
	
	public static void registerItems(IForgeRegistry<Item> registry) {
		
	}
	
	public static Item init(Item item, String name) {
		item.setUnlocalizedName(name);
		item.setRegistryName(new ResourceLocation(Biozymes.MODID, name));
		return item;
	}
}
