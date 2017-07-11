package hr.fer.zemris.java.hw05.demo4;

import java.util.Comparator;

/**
 * Razred koji predstavlja jedan zapis studenta u memoriji. Jedan zapis
 * predstavlja studentov uspjeh na predmetu. Članske varijable svakog primjerka
 * ovog razreda moguće je samo čitati. Razred nudi metode:
 * <ul>
 * <li>{@link #getJmbag()}</li>
 * <li>{@link #getFirstName()}</li>
 * <li>{@link #getLastName()}</li>
 * <li>{@link #getPointsMidterm()}</li>
 * <li>{@link #getPointsFinal()}</li>
 * <li>{@link #getPointsLab()}</li>
 * <li>{@link #getGrade()}</li>
 * </ul>
 * 
 * Primjerci ovog razreda mogu odrediti jesu li međusobno
 * jednaki({@link #equals(Object)} i {@link #hashCode()}) te se znaju
 * ispisati({@link #toString()})
 * 
 * 
 * @author Davor Češljaš
 */
public class StudentRecord {

	/** Članska varijabla koja predstavlja JMBAG studenta */
	private String jmbag;

	/** Članska varijabla koja predstavlja prezime studenta */
	private String lastName;

	/** Članska varijabla koja predstavlja ime studenta */
	private String firstName;

	/** Članska varijabla koja predstavlja bodove ostvarene na međuispitu */
	private float pointsMidterm;

	/** Članska varijabla koja predstavlja bodove ostvarene na završnom */
	private float pointsFinal;

	/**
	 * Članska varijabla koja predstavlja bodove ostvarene kroz laboratorijske
	 * vježbe
	 */
	private float pointsLab;

	/**
	 * Članska varijabla koja predstavlja konačnu ocjenu studenta na predmetu
	 */
	private int grade;

	/**
	 * Konstanta koja implementira sučelje {@link Comparator}. Ovaj primjerak
	 * razreda komparator uspoređuje studente prema ukupno ostvarenom broju
	 * bodova na predmetu. (Međuispit + Laboratorijske vježbe + Završni ispit)
	 */
	public static final Comparator<StudentRecord> POINTS = (o1, o2) -> Float.compare(
			o1.getPointsMidterm() + o1.getPointsFinal() + o1.getPointsLab(),
			o2.getPointsMidterm() + o2.getPointsFinal() + o2.getPointsLab());

	/**
	 * Konstruktor koji inicijalizira ovaj primjerak razreda student na predane
	 * vrijednosti.
	 *
	 * @param jmbag
	 *            JMBAG studenta
	 * @param lastName
	 *            prezime studenta
	 * @param firstName
	 *            ime studenta
	 * @param pointsMidterm
	 *            broj bodova ostvarenih na međuispitu
	 * @param pointsFinal
	 *            broj bodova ostvarenih na završnom ispitu
	 * @param pointsLab
	 *            broj bodova ostvarenih kroz laboratorijske vježbe
	 * @param grade
	 *            konačna ocjena na predmetu
	 */
	public StudentRecord(String jmbag, String lastName, String firstName, float pointsMidterm, float pointsFinal,
			float pointsLab, int grade) {
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.pointsMidterm = pointsMidterm;
		this.pointsFinal = pointsFinal;
		this.pointsLab = pointsLab;
		this.grade = grade;
	}

	/**
	 * Metoda koja dohvaća JMBAG studenta
	 *
	 * @return JMBAG studenta
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * Metoda koja dohvaća prezime studenta
	 *
	 * @return prezime studenta
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Metoda koja dohvaća ime studenta
	 *
	 * @return ime studenta
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Metoda koja dohvaća bodove koje je student ostvario na međuispitu
	 *
	 * @return bodove koje je student ostvario na međuispitu
	 */
	public float getPointsMidterm() {
		return pointsMidterm;
	}

	/**
	 * Metoda koja dohvaća bodove koje je student ostvario na završnom ispitu
	 *
	 * @return bodove koje je student ostvario na završnom ispitu
	 */
	public float getPointsFinal() {
		return pointsFinal;
	}

	/**
	 * Metoda koja dohvaća bodove koje je student ostvario kroz laboratorijske
	 * vježbe
	 *
	 * @return bodove koje je student ostvario kroz laboratorijske vježbe
	 */
	public float getPointsLab() {
		return pointsLab;
	}

	/**
	 * Metoda koja dohvaća konačnu ocjenu studenta na predmetu
	 *
	 * @return konačnu ocjenu studenta na predmetu
	 */
	public int getGrade() {
		return grade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentRecord other = (StudentRecord) obj;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("(%s) , %s %s bodovi: %f", jmbag, firstName, lastName,
				pointsFinal + pointsMidterm + pointsLab);
	}

}
