package hr.fer.zemris.java.hw05.observer2;

/**
 * Razred koji predstavlja jedan događaj promjene vrijednosti unutar primjerka
 * razreda {@link IntegerStorage}. Razred je nepromijenjiv i nudi sljedeće
 * metode:
 * <ul>
 * <li>{@link #getIntegerStorage()}</li>
 * <li>{@link #getNewValue()}</li>
 * <li>{@link #getOldValue()}</li>
 * </ul>
 * 
 * Razredu se sve vrijednosti predaju kroz konstruktor
 * {@link #IntegerStorageChange(IntegerStorage, Integer, Integer)}
 * 
 * @see IntegerStorage
 * 
 * @author Davor Češljaš
 */
public class IntegerStorageChange {

	/**
	 * Članska varijabla koja sprema referencu na primjerak razreda
	 * {@link IntegerStorage} nad kojim se dogodila promjena
	 */
	private IntegerStorage integerStorage;

	/** Članska varijabla koja predstavlja vrijednost prije promjene */
	private Integer oldValue;

	/** Članska varijabla koja predstavlja vrijednost nakon promjene */
	private Integer newValue;

	/**
	 * Konstruktor koji inicijalizira primjerak ovog razreda na predane
	 * vrijednosti
	 *
	 * @param integerStorage
	 *            referenca na primjerak razreda {@link IntegerStorage} nad
	 *            kojim se dogodila promjena
	 * @param oldValue
	 *            vrijednost prije promjene
	 * @param newValue
	 *            vrijednost nakon promjene
	 */
	public IntegerStorageChange(IntegerStorage integerStorage, Integer oldValue, Integer newValue) {
		this.integerStorage = integerStorage;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	/**
	 * Metoda koja dohvaća referencu na primjerak razreda {@link IntegerStorage}
	 * nad kojim se dogodila promjena
	 *
	 *
	 * @return referencu na primjerak razreda {@link IntegerStorage} nad kojim
	 *         se dogodila promjena
	 */
	public IntegerStorage getIntegerStorage() {
		return integerStorage;
	}

	/**
	 * Metoda koja dohvaća vrijednost prije promjene
	 *
	 * @return vrijednost prije promjene
	 */
	public Integer getOldValue() {
		return oldValue;
	}

	/**
	 * Metoda koja dohvaća vrijednost nakon promjene
	 *
	 * @return vrijednost nakon promjene
	 */
	public Integer getNewValue() {
		return newValue;
	}

}
