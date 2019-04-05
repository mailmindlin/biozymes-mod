package biozymes.api.genetics;

public interface IIndividual {
	IGenome getGenome();
	
	boolean isAlive();
	
	ISpeciesRoot getSpeciesRoot();
}
