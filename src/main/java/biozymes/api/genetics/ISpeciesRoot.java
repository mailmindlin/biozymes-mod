package biozymes.api.genetics;

import java.util.Set;

public interface ISpeciesRoot {
	String getUID();
	
	int getDefaultPloidy();
	
	boolean isValidChromosome(IChromosomeKey key);
	
	Set<ITrait<?>> getTraits();
}
