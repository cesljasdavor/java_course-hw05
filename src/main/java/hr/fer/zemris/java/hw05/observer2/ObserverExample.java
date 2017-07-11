package hr.fer.zemris.java.hw05.observer2;

/**
 * Razred predstavlja demonstracijski program koji prikazuje rad sa subjektom i
 * konkretnim promatračima. Subjekt je oblikovan sa {@link IntegerStorage}, a
 * konkretni promatrači implementiraju {@link IntegerStorageObserver}. Konkretni
 * promatrači ovdje korišteni su:
 * <ul>
 * <li>{@link DoubleValue}</li>
 * <li>{@link ChangeCounter}</li>
 * <li>{@link SquareValue}</li>
 * </ul>
 * 
 * 
 * @see IntegerStorage
 * @see IntegerStorageObserver
 * @see DoubleValue
 * @see ChangeCounter
 * @see SquareValue
 * 
 * @author Davor Češljaš
 */
public class ObserverExample {

	/**
	 * Metoda od koje počinje izvođenje ovog programa
	 *
	 * @param args
	 *            ovdje se ne koristi
	 */
	public static void main(String[] args) {
		IntegerStorage istorage = new IntegerStorage(20);
		istorage.addObserver(new SquareValue());
		istorage.addObserver(new ChangeCounter());
		istorage.addObserver(new DoubleValue(3));
		
		istorage.setValue(5);
		istorage.setValue(2);
		istorage.setValue(25);
		istorage.setValue(13);
		istorage.setValue(22);
		istorage.setValue(15);
	}

}
