package biozymes.common.genetics;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import biozymes.api.genetics.IChromosomeKey;

public final class ChromosomeKey {
	
	public static final class Named implements IChromosomeKey {
		public static final Named X = new Named("X");
		public static final Named Y = new Named("Y");
		public static final Named U = new Named("U");
		public static final Named V = new Named("V");
		public static final Named MITOCHONDRIA = new Named("mitochondria");
		public static final Named LOOP = new Named("loop");
		
		final String name;
		
		Named(String name) {
			this.name = Objects.requireNonNull(name);
		}

		@Override
		public int compareTo(IChromosomeKey o) {
			if (o instanceof Named)
				return this.getName().compareTo(o.getName());
			if (o instanceof Autosomal)
				return -1;
			return o.compareTo(this);
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		@Override
		public int hashCode() {
			return this.name.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			return (obj instanceof Named) && Objects.equals(this.name, ((IChromosomeKey) obj).getName());
		}
	}
	
	public static final class Autosomal implements IChromosomeKey {
		private static ConcurrentMap<Integer, Autosomal> cache = new ConcurrentHashMap<>();
		
		public static Autosomal get(int value) {
			return cache.computeIfAbsent(value, Autosomal::new);
		}
		
		protected final int value;
		
		private Autosomal(int value) {
			this.value = value;
		}

		@Override
		public String getName() {
			return String.format("AC%d", this.value);
		}
		
		@Override
		public int hashCode() {
			return this.value;
		}
		
		@Override
		public boolean equals(Object obj) {
			return (obj instanceof Autosomal) && ((Autosomal) obj).value == this.value;
		}
		
		public int compareTo(Autosomal other) {
			return Integer.compare(this.value, other.value);
		}
		
		@Override
		public int compareTo(IChromosomeKey o) {
			if (o instanceof Autosomal)
				return this.compareTo((Autosomal) o);
			
			if (o instanceof Named)
				return 1;
			
			return o.compareTo(this);
		}
	}
}
