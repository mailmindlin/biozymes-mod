package biozymes.common.genetics;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import biozymes.api.genetics.IAllele;
import biozymes.api.genetics.IAlleleRegistry;
import biozymes.api.genetics.ITrait;
import biozymes.api.genetics.ISpeciesRoot;

public class AlleleRegistry implements IAlleleRegistry {
	
	protected final Map<String, ISpeciesRoot> roots = new ConcurrentHashMap<>();
	protected final Map<String, IAllele> alleles = new ConcurrentHashMap<>();

	@Override
	public void registerSpeciesRoot(ISpeciesRoot root) {
		if (this.roots.putIfAbsent(root.getUID(), root) != null)
			throw new IllegalArgumentException("Root with id=" + root.getUID() + " already registered");
	}

	@Override
	public ISpeciesRoot getSpeciesRoot(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends IAllele> getRegisteredAlleles(ITrait type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerAllele(IAllele allele, ITrait... types) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IAllele getAllele(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends ITrait> getChromosomeTypes(IAllele allele) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
