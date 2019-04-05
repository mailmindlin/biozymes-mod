package biozymes.common.genetics.mammal;

import java.util.Objects;
import java.util.Set;

import biozymes.api.genetics.IChromosomeKey;
import biozymes.api.genetics.ISpeciesRoot;
import biozymes.api.genetics.ITrait;
import biozymes.common.genetics.ChromosomeKey;

public class MammalRoot implements ISpeciesRoot {

	@Override
	public String getUID() {
		return "mammal";
	}

	@Override
	public int getDefaultPloidy() {
		return 2;
	}

	@Override
	public boolean isValidChromosome(IChromosomeKey key) {
		if (key instanceof ChromosomeKey.Named)
			return Objects.equals(key, ChromosomeKey.Named.X)
					|| Objects.equals(key, ChromosomeKey.Named.Y);
		
		if (key instanceof ChromosomeKey.Autosomal)
			return true;
		
		return false;
	}

	@Override
	public Set<ITrait<?>> getTraits() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
