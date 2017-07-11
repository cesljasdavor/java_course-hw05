package hr.fer.zemris.java.hw05.observer2;

import hr.fer.zemris.java.hw05.observer1.IntegerStorage;

/**
 * Sučelje koje predstavlja abstraktnog promatrača (engl. Abstract Observer) nad
 * primjerkom razreda {@link IntegerStorage}. Sučelje nudi jednu metodu koju će
 * "subjekt" pozvati za svaki promatrač koji se registrira nad njim
 * {@link #valueChanged(IntegerStorageChange)}. Promjena je ovdje oblikovana
 * razredom {@link IntegerStorageChange}
 * 
 * @see IntegerStorage
 * @see IntegerStorageChange
 * 
 * @author Davor Češljaš
 */
public interface IntegerStorageObserver {

	/**
	 * Metoda koja svaki razred koji implementira ovo sučelje mora
	 * implementirati. Metodu će zvati "subjekt" nad kojim se registrira
	 * implementacija ovog sučelja, te obrada ovisi upravo o ovoj metodi.
	 * Subjekt će sigurno poslati primjerak razreda {@link IntegerStorageChange}
	 *
	 * @param integerStorageChange
	 *            primjerak razreda {@link IntegerStorageChange} koji
	 *            predstavlja detaljnu promjenu stanja primjerka razreda
	 *            {@link IntegerStorage}
	 */
	public void valueChanged(IntegerStorageChange integerStorageChange);
}
