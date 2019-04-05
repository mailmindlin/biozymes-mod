package biozymes.api.genetics;

import java.util.Collection;

public interface IGenome {
	ISpeciesRoot getSpeciesRoot();
	
	boolean isGeneticallyEqual(IGenome other);
	
	Collection<? extends IChromosome> getChromosome(IChromosomeKey ordinal);
	
	Collection<? extends IChromosomeKey> getChromosomes();
}
