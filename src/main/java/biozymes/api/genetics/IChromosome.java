package biozymes.api.genetics;

import java.util.List;

public interface IChromosome {
	/**
	 * Chromosome ordinal number.
	 * @return
	 */
	IChromosomeKey getKey();
	
	List<? extends IAllele<?>> getAlleles();
}
