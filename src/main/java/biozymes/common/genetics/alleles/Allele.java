package biozymes.common.genetics.alleles;

import biozymes.api.genetics.IAllele;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Allele<T> implements IAllele<T> {
	protected final ResourceLocation name;
	protected final String unlocalizedName;
	protected final boolean isDominant;
	
	public Allele(ResourceLocation name, String unlocalizedName, boolean isDominant) {
		this.name = name;
		this.unlocalizedName = unlocalizedName;
		this.isDominant = isDominant;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return this.name;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getAlleleName() {
		return I18n.format(this.unlocalizedName);
	}

	@Override
	public String getUnlocalizedName() {
		return this.unlocalizedName;
	}
}
