package hr.fer.zemris.java.hw05.observer2;

/**
 * Razred predstavlja konkretnog promatrača(engl Concrete Observer) nad
 * subjektom koji je primjerak razreda {@link IntegerStorage}. Ovaj razred
 * implementira
 * {@link IntegerStorageObserver#valueChanged(IntegerStorageChange)}. Metoda
 * kada se pozove ispisuje kvadrat vrijednost od one koja se promijenila
 * 
 * @see IntegerStorageObserver
 * @see IntegerStorage
 * @see IntegerStorageChange
 * 
 * @author Davor Češljaš
 */
public class SquareValue implements IntegerStorageObserver {
	@Override
	public void valueChanged(IntegerStorageChange integerStorageChange) {
		Integer value = integerStorageChange.getNewValue();
		System.out.printf("Dobivena nova vrijednost: %d, kvadrat broja je: %d%n", value, (int) Math.pow(value, 2));
	}
}
