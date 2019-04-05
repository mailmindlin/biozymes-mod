package biozymes.api.genetics;

import java.util.List;

public interface ITrait<T> {
	ISpeciesRoot getSpeciesRoot();
	
	/**
	 * @return Allele class
	 */
	Class<? extends IAllele<T>> getAlleleClass();
	
	/**
	 * @return Get (unlocalized) trait name
	 */
	String getUnlocalizedName();
	
	/**
	 * 
	 * @param alleles Alleles on organism
	 * @return Computed phenotype
	 */
	T getPhenotype(List<? extends IAllele<T>> alleles);
}
