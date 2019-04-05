package biozymes.api.genetics;

import java.util.Collection;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface IAlleleRegistry {
	void registerSpeciesRoot(ISpeciesRoot root);
	
	@Nullable
	ISpeciesRoot getSpeciesRoot(String uid);
	
	<T> Collection<? extends IAllele<T>> getRegisteredAlleles(ITrait<T> type);
	
	void registerAllele(IAllele allele, ITrait...types);
	
	@Nullable
	<T> IAllele<T> getAllele(String uid);
	
	<T> Collection<? extends ITrait<T>> getChromosomeTypes(IAllele<T> allele);
	
}
