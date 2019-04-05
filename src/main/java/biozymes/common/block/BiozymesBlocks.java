package biozymes.common.block;

import java.util.Arrays;
import java.util.List;

import biozymes.common.Biozymes;
import biozymes.common.item.BiozymesItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

//@ObjectHolder(Biozymes.MODID)
public class BiozymesBlocks {
	public static BlockCropCorn blockCorn = new BlockCropCorn();
	
	static List<IBZBlock> getBlocks() {
		return Arrays.asList(blockCorn);
	}
	
	public static void registerBlocks(IForgeRegistry<Block> registry) {
		for (IBZBlock block : getBlocks()) {
			registry.register(init((Block) block, block.getName()));
		}
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for (IBZBlock block : getBlocks()) {
			Item item = BiozymesItems.init(new ItemBlock((Block) block), block.getName());
			registry.register(item);
		}
	}
	
	public static Block init(Block block, String name) {
		block.setUnlocalizedName(name);
		block.setRegistryName(new ResourceLocation(Biozymes.MODID, name));
		return block;
	}
	
	
}
