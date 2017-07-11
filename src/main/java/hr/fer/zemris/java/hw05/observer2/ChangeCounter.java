package hr.fer.zemris.java.hw05.observer2;

/**
 * Razred predstavlja konkretnog promatrača(engl Concrete Observer) nad
 * subjektom koji je primjerak razreda {@link IntegerStorage}. Ovaj razred
 * implementira
 * {@link IntegerStorageObserver#valueChanged(IntegerStorageChange)}. Svaki puta
 * kada se pozove ta metoda podići će se brojač koji broji koliko se puta
 * dogodila promjena od registracije ovog promatrača
 * 
 * @see IntegerStorageObserver
 * @see IntegerStorage
 * @see IntegerStorageChange
 * 
 * @author Davor Češljaš
 */
public class ChangeCounter implements IntegerStorageObserver {
	/**
	 * Članska varijabla u koju se sprema broj promjena od registracije ovog
	 * promatrača
	 */
	private long numberOfChanges;

	@Override
	public void valueChanged(IntegerStorageChange integerStorageChange) {
		numberOfChanges++;
		System.out.println("Broj promjena od početka praćenja: " + numberOfChanges);
	}
}
