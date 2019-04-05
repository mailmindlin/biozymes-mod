package biozymes.api.genetics;

import java.util.Collection;
import java.util.Random;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface IHeritageHelper {
	Collection<IGenome> split(Random rng, IGenome genome);
	
	@Nullable
	IGenome cross(Random rng, IGenome parent1, IGenome parent2);
	
	IGenome mutate(Random rng, IGenome genome, Collection<? extends IMutagen> mutagens);
}
