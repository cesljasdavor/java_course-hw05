package hr.fer.zemris.java.hw05.demo2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Razred predstavlja kolekciju prim brojeva. Kao prvi prim broj uzima se
 * {@value #FIRST_PRIME}. Razred implementira sučelje {@link Iterable} te ga se
 * time može koristiti u kraćoj (foreach) for petlji. Razred nudi jedan
 * konstruktor {@link #PrimesCollection(int)} te implementaciju metode
 * {@link #iterator()} koja vraća implementaciju sučelja {@link Iterator}
 * 
 * @see Iterable
 * @see Iterator
 * 
 * @author Davor Češljaš
 */
public class PrimesCollection implements Iterable<Integer> {

	/** Konstanta koja predstavlja prvi prim broj */
	private static final int FIRST_PRIME = 2;

	/** Broj prim brojeva koje ova kolekcija sadrži */
	private int numberOfPrimes;

	/**
	 * Konstruktor koji inicijalizira primjerak ovog razreda. Kao parametar
	 * <b>numberOfPrimes</b> predaje se koliko ovaj razred mora sadržavati prim
	 * brojeva
	 *
	 * @param numberOfPrimes
	 *            koliko ovaj razred mora sadržavati prim brojeva
	 */
	public PrimesCollection(int numberOfPrimes) {
		if (numberOfPrimes < 1) {
			throw new IllegalArgumentException(
					String.format("Ne mogu stvoriti kolekciju prim brojeva sa %d članova%n", numberOfPrimes));
		}
		this.numberOfPrimes = numberOfPrimes;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new PrimesIterator();
	}

	/**
	 * Ugnježđeni razred razreda {@link PrimesCollection} koji implementira
	 * sučelje {@link Iterator}. Razred implementira metode:
	 * <ul>
	 * <li>{@link #hasNext()}</li>
	 * <li>{@link #next()}</li>
	 * </ul>
	 * Ostale metode sučelja {@link Iterator} imaju defaultne implementacije.
	 * 
	 * @see Iterator
	 * 
	 * @author Davor Češljaš
	 */
	private class PrimesIterator implements Iterator<Integer> {

		/** Predstavlja trenutni prim broj koji se treba vratiti */
		private Integer currentPrime;

		/**
		 * Predstavlja preostalu količinu prim brojeva koje ovaj primjerak
		 * razreda {@link PrimesIterator} vraća
		 */
		private int primesLeft;

		/**
		 * Konstruktor koji inicijalizira primjerak ovog razreda na početne
		 * vrijednosti, te kopira vrijednost
		 * {@link PrimesCollection#numberOfPrimes}.
		 */
		public PrimesIterator() {
			primesLeft = numberOfPrimes;
			// prvi prim broj
			currentPrime = FIRST_PRIME;
		}

		@Override
		public boolean hasNext() {
			return primesLeft > 0;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Nema više elemenata!");
			}
			Integer toReturn = currentPrime;
			calculatePrime();
			primesLeft--;
			return toReturn;
		}

		/**
		 * Pomoćna metoda koja se koristi za izračun sljedećeg prim broja.
		 * Metoda traži prim brojeve veće od {@link #currentPrime} i postavlja
		 * novu vrijednost {@link #currentPrime} na sljedeći prim broj
		 */
		private void calculatePrime() {
			int currentNumber = currentPrime + 1;
			while (true) {
				boolean isPrime = true;

				for (int i = 2; i <= (int) Math.sqrt(currentNumber); i++) {
					if (currentNumber % i == 0) {
						isPrime = false;
						break;
					}
				}

				if (isPrime) {
					currentPrime = currentNumber;
					break;
				}

				currentNumber++;
			}
		}
	}

}
