package biozymes.api.genetics;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public interface IAllele<T> extends IForgeRegistryEntry<IAllele<T>> {
	
	EnumInheritanceMode getInheritanceMode();
	
	/**
	 * @return Human-readable allele name
	 */
	String getAlleleName();
	
	String getUnlocalizedName();
	
	ITrait<T> getTraitType();
	
	@Override
	default Class<? super IAllele<T>> getRegistryType() {
		return IAllele.class;
	}
	
	@Override
	@Deprecated
	default IAllele<T> setRegistryName(ResourceLocation name) {
		throw new UnsupportedOperationException();
	}
}
