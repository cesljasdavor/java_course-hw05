package hr.fer.zemris.java.hw05.demo2;

/**
 * Razred predstavlja demonstracijski program za razred
 * {@link PrimesCollection}. Razred demontrira neovisnost dvaju iteratora po
 * istom primjerku razreda {@link PrimesCollection}
 * 
 * @see PrimesCollection
 * 
 * @author Davor Češljaš
 */
public class PrimesDemo2 {

	/**
	 * Metoda od koje započinje izvođenje programa
	 *
	 * @param args
	 *            ovdje se ne koristi
	 */
	public static void main(String[] args) {
		PrimesCollection primesCollection = new PrimesCollection(2);
		for (Integer prime : primesCollection) {
			for (Integer prime2 : primesCollection) {
				System.out.println("Got prime pair: " + prime + ", " + prime2);
			}
		}

	}
}
