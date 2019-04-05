package biozymes.common.genetics.bacteria;

import java.util.Objects;
import java.util.Set;

import biozymes.api.genetics.IChromosomeKey;
import biozymes.api.genetics.ISpeciesRoot;
import biozymes.api.genetics.ITrait;
import biozymes.common.genetics.ChromosomeKey;

public class BacteriaSpeciesRoot implements ISpeciesRoot {

	@Override
	public String getUID() {
		return "bacteria";
	}

	@Override
	public int getDefaultPloidy() {
		return 1;
	}

	@Override
	public Set<ITrait<?>> getTraits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValidChromosome(IChromosomeKey key) {
		return Objects.equals(key, ChromosomeKey.Named.LOOP);
	}
	
}
