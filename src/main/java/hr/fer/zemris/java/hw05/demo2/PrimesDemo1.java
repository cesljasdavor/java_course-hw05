package hr.fer.zemris.java.hw05.demo2;

/**
 * Razred predstavlja demonstracijski program za razred
 * {@link PrimesCollection}.
 * 
 * @see PrimesCollection
 * 
 * @author Davor Češljaš
 */
public class PrimesDemo1 {

	/**
	 * Metoda od koje započinje izvođenje programa
	 *
	 * @param args
	 *            ovdje se ne koristi
	 */
	public static void main(String[] args) {
		PrimesCollection primesCollection = new PrimesCollection(35);
		for (Integer prime : primesCollection) {
			System.out.println("Got prime: " + prime);
		}
	}
}
