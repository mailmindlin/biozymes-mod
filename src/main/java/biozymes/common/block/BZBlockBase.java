package biozymes.common.block;

import biozymes.common.Biozymes;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BZBlockBase extends Block implements IBZBlock {
	protected final String name;
	
	public BZBlockBase(Material materialIn, String name) {
		this(materialIn, materialIn.getMaterialMapColor(), name);
	}
	
	public BZBlockBase(Material materialIn, MapColor mapColor, String name) {
		super(materialIn, mapColor);
		this.name = name;
		this.setCreativeTab(Biozymes.instance.creativeTab);
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
