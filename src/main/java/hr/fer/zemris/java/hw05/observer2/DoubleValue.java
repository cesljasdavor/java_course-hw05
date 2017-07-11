package hr.fer.zemris.java.hw05.observer2;

/**
 * Razred predstavlja konkretnog promatrača(engl Concrete Observer) nad
 * subjektom koji je primjerak razreda {@link IntegerStorage}. Ovaj razred
 * implementira {@link IntegerStorageObserver#valueChanged(IntegerStorage)}.
 * Koliko će se puta metoda pozvati ovisi o vrijednosti poslanoj kroz
 * konstruktor {@link #DoubleValue(long)}. Kada ta vrijednost postane 0 u metodi
 * {@link DoubleValue#valueChanged(IntegerStorageChange)} poziva se
 * {@link IntegerStorage#removeObserver(IntegerStorageObserver)}. Metoda kada se
 * pozove ispisuje dvostruko veću vrijednost od one koja se promijenila
 * 
 * @see IntegerStorageObserver
 * @see IntegerStorage
 * @see IntegerStorageChange
 * 
 * @author Davor Češljaš
 */
public class DoubleValue implements IntegerStorageObserver {
	/**
	 * Članska varijabla koja ograničava koliko će se puta pozvati metoda
	 * {@link #valueChanged(IntegerStorageChange)}
	 */
	private long changesLeft;

	/**
	 * Konstruktor koji inicijalizira koliko će se puta smjeti pozvati metoda
	 * {@link #valueChanged(IntegerStorage)} prije poziva
	 * {@link IntegerStorage#removeObserver(IntegerStorageObserver)}
	 *
	 * @param numberOfChanges
	 *            koliko će se puta smjeti pozvati metoda
	 *            {@link #valueChanged(IntegerStorageChange)} prije poziva
	 *            {@link IntegerStorage#removeObserver(IntegerStorageObserver)}
	 */
	public DoubleValue(long numberOfChanges) {
		if (numberOfChanges < 1) {
			throw new IllegalArgumentException(
					"Broj promjena od registracije mora biti minimalno 1. Vi ste predali: " + numberOfChanges);
		}
		changesLeft = numberOfChanges;
	}

	@Override
	public void valueChanged(IntegerStorageChange integerStorageChange) {
		changesLeft--;
		System.out.println("Dvostruka vrijednost: " + integerStorageChange.getNewValue() * 2);
		if(changesLeft == 0) {
			integerStorageChange.getIntegerStorage().removeObserver(this);
		}
	}
}
