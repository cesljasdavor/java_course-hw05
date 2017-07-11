package hr.fer.zemris.java.hw05.observer1;

/**
 * Sučelje koje predstavlja abstraktnog promatrača (engl. Abstract Observer) nad
 * primjerkom razreda {@link IntegerStorage}. Sučelje nudi jednu metodu koju će
 * "subjekt" pozvati za svaki promatrač koji se registrira nad njim
 * {@link #valueChanged(IntegerStorage)}
 * 
 * @see IntegerStorage
 * 
 * @author Davor Češljaš
 */
public interface IntegerStorageObserver {

	/**
	 * Metoda koja svaki razred koji implementira ovo sučelje mora
	 * implementirati. Metodu će zvati "subjekt" nad kojim se registrira
	 * implementacija ovog sučelja, te obrada ovisi upravo o ovoj metodi
	 *
	 * @param istorage
	 *            subjekt unutar oblikovnog obrasca Promatrač
	 */
	public void valueChanged(IntegerStorage istorage);
}
