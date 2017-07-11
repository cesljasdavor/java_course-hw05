package hr.fer.zemris.java.hw05.observer1;

import java.util.ArrayList;
import java.util.List;

/**
 * Razred koji predstavlja "subjekt" unutar oblikovnog obrasca Promatrač(engl.
 * Observer). Razred omata jedan primjerak razreda {@link Integer} koji se može
 * mijenjati. Zadatak razreda je da obavijesti sve registrirane promatrače (engl
 * Concrete Observers) da se dogodila promjena omotane vrijednosti .
 * Registrirani promatrač ovdje mora implementirati sučelje
 * {@link IntegerStorageObserver}. Razred nudi sljedeće metode:
 * <ul>
 * <li>{@link #addObserver(IntegerStorageObserver)}</li>
 * <li>{@link #removeObserver(IntegerStorageObserver)}</li>
 * <li>{@link #clearObservers()}</li>
 * <li>{@link #getValue()}</li>
 * <li>{@link #setValue(int)}</li>
 * </ul>
 * 
 * @see IntegerStorageObserver
 * 
 * @author Davor Češljaš
 */
public class IntegerStorage {

	/**
	 * Članska varijabla koja predstavlja primitivni int koji primjerak ovog
	 * razreda omata i mijenja
	 */
	private int value;

	/**
	 * Članska varijabla koja predstavlja {@link List} svih registriranih
	 * promatrača koji implementiraju {@link IntegerStorageObserver}
	 */
	private List<IntegerStorageObserver> observers;

	/**
	 * Konstruktor koji inicijalizira vrijednost varijable koju ovaj razred
	 * omata na <b>initialValue</b>
	 *
	 * @param initialValue
	 *            vrijednost na koju se inicijalizira članska varijabla
	 */
	public IntegerStorage(int initialValue) {
		this.value = initialValue;
		this.observers = new ArrayList<>();
	}

	/**
	 * Metoda koja služi za dodavanje promatrača nad promjenom članske
	 * varijable. Promatrač se neće dodati ako se isti već nalazi u listi
	 * promatrača
	 *
	 * @param observer
	 *            promatrač koji implementira {@link IntegerStorageObserver}
	 *            sučelje ,kojeg treba dodati u listu promatrača
	 */
	public void addObserver(IntegerStorageObserver observer) {
		if (!observers.contains(observer)) {
			// zbog concurent modification exception
			observers = new ArrayList<>(observers);
			observers.add(observer);
		}
	}

	/**
	 * Metoda koja služi za uklanjanje promatrača iz liste promatrača nad
	 * spremljenom vrijednosti. Promatrač se neće ukloniti ako se isti ne nalazi
	 * u listi promatrača
	 *
	 * @param observer
	 *            promatrač koji implementira {@link IntegerStorageObserver}
	 *            sučelje, kojeg treba ukloniti iz liste promatrača
	 */
	public void removeObserver(IntegerStorageObserver observer) {
		if (observers.contains(observer)) {
			// zbog concurent modification exception
			observers = new ArrayList<>(observers);
			observers.remove(observer);
		}
	}

	/**
	 * Metoda koja miče sve promatrača iz liste promatrača
	 */
	public void clearObservers() {
		observers.clear();
	}

	/**
	 * Metoda koja dohvaća vrijednost koju omata ovaj razred
	 *
	 * @return vrijednost koju omata ovaj razred
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Metoda koja postavlja vrijednost koju omata ovaj razred na <b>value</b>.
	 * Metoda također obavještava sve promatrače pozivom njihovih
	 * {@link IntegerStorageObserver#valueChanged(IntegerStorage)}
	 *
	 * @param value
	 *            nova vrijednost koju omata primjerak ovog razreda
	 */
	public void setValue(int value) {
		if (this.value != value) {
			this.value = value;
			if (observers != null) {
				// ako koristimo ArrayList operacija get je složenosti O(1)
				for (IntegerStorageObserver observer : observers) {
					observer.valueChanged(this);
				}
			}
		}
	}

}
