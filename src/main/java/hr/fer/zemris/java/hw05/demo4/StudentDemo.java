package hr.fer.zemris.java.hw05.demo4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Razred koji predstavlja demonstracijski primjer za rad sa razredom
 * {@link StudentRecord}. Razred također demostrira rad sa Javinim tokovnim
 * APIjem.
 *
 * @author Davor Češljaš
 * @see StudentRecord
 * @see Stream
 */
public class StudentDemo {

	/**
	 * Konstanta koja predstavlja poziciju u polju podataka o studentu na kojoj
	 * se nalazi JMBAG studenta.
	 */
	private static final int JMBAG_INDEX = 0;

	/**
	 * Konstanta koja predstavlja poziciju u polju podataka o studentu na kojoj
	 * se nalazi prezime studenta.
	 */
	private static final int LAST_NAME_INDEX = 1;

	/**
	 * Konstanta koja predstavlja poziciju u polju podataka o studentu na kojoj
	 * se nalazi ime studenta.
	 */
	private static final int FIRST_NAME_INDEX = 2;

	/**
	 * Konstanta koja predstavlja poziciju u polju podataka o studentu na kojoj
	 * se nalazi broj ostvarenih bodova na međuispitu.
	 */
	private static final int POINTS_MIDTERM_INDEX = 3;

	/**
	 * Konstanta koja predstavlja poziciju u polju podataka o studentu na kojoj
	 * se nalazi broj ostvarenih bodova na završnom ispitu.
	 */
	private static final int POINTS_FINAL_INDEX = 4;

	/**
	 * Konstanta koja predstavlja poziciju u polju podataka o studentu na kojoj
	 * se nalazi broj ostvarenih bodova kroz laboratorijske vježbe.
	 */
	private static final int POINTS_LABS_INDEX = 5;

	/**
	 * Konstanta koja predstavlja poziciju u polju podataka o studentu na kojoj
	 * se nalazi konačna ocjena studenta na predmetu.
	 */
	private static final int GRADE_INDEX = 6;

	/**
	 * Konstanta koja predstavlja broj elemenata u polju potrebnih za stvaranje
	 * primjerka razreda {@link StudentRecord}
	 */
	private static final int STUDENT_DATA_LENGTH = 7;

	/**
	 * Metoda od koje započinje izođenje program.
	 *
	 * @param args
	 *            ovdje se ne koristi
	 * @throws IOException
	 *             ukoliko nije moguće pročitati (ili pronaći) datoteku sa
	 *             zapisima o studentima
	 */
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("./studenti.txt"));
		List<StudentRecord> records = convert(lines);
		// 1
		long number = getPointsGreaterThen25(records);
		System.out.println("Studenata sa bodovima većim od 25: " + number);

		System.out.println();
		// 2
		long number5 = getNumberOfAStudents(records);
		System.out.println("Studenata sa ocjenom 5: " + number5);

		System.out.println();
		// 3
		List<StudentRecord> aStudents = getListOfAStudents(records);
		System.out.println("Studenti koji su prošli s 5: ");
		aStudents.forEach(System.out::println);

		System.out.println();
		// 4
		List<StudentRecord> sortedAStudents = getSortedListOfAStudents(records);
		System.out.println("Studenti koji su prošli s 5 sortirani po bodovima: ");
		sortedAStudents.forEach(System.out::println);

		System.out.println();
		// 5
		List<String> notPassedJMBAGs = getListOfNotPassed(records);
		System.out.println("JMBAGovi studenata koji nisu prošli: ");
		notPassedJMBAGs.forEach(System.out::println);

		System.out.println();
		// 6
		Map<Integer, List<StudentRecord>> mapByGrades = classifyStudentsByGrades(records);
		System.out.println("Studenti po ocjenama: ");
		for (Map.Entry<Integer, List<StudentRecord>> entry : mapByGrades.entrySet()) {
			System.out.println(entry.getKey());
			entry.getValue().forEach(r -> System.out.println("\t" + r));
		}

		System.out.println();
		// 7
		System.out.println("Broj pojedinih ocjena: ");
		Map<Integer, Integer> mapByGrades2 = getNumberOfStudentsByGrades(records);

		for (Map.Entry<Integer, Integer> entry : mapByGrades2.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}

		// 8
		Map<Boolean, List<StudentRecord>> passedNotPassed = classifyPassNotPassed(records);

		System.out.println();
		for (Map.Entry<Boolean, List<StudentRecord>> entry : passedNotPassed.entrySet()) {
			System.out.printf("Studenti koji %s prošli: %n", entry.getKey() ? "su" : "nisu");
			entry.getValue().forEach(r -> System.out.println("\t" + r));
		}
	}

	/**
	 * Pomoćna metoda koja iz {@link List} primjeraka razreda
	 * {@link StudentRecord} <b>records</b> dohvaća broj studenta koji su ukupno
	 * ostvarili više od 25 bodova.
	 *
	 * @param records
	 *            {@link List} primjeraka razreda {@link StudentRecord} koji su
	 *            polagali predmet
	 * @return broj studenta koji su ukupno ostavrili više od 25 bodova
	 */
	private static long getPointsGreaterThen25(List<StudentRecord> records) {
		return records.stream()
				.filter(record -> record.getPointsMidterm() + record.getPointsFinal() + record.getPointsLab() > 25)
				.count();
	}

	/**
	 * Pomoćna metoda koja iz {@link List} primjeraka razreda
	 * {@link StudentRecord} <b>records</b> dohvaća broj studenta kojima je
	 * konačna ocjena na predmetu 5.
	 *
	 * @param records
	 *            {@link List} primjeraka razreda {@link StudentRecord} koji su
	 *            polagali predmet
	 * @return broj studenta kojima je konačna ocjena na predmetu 5.
	 */
	private static long getNumberOfAStudents(List<StudentRecord> records) {
		return records.stream().filter(record -> record.getGrade() == 5).count();
	}

	/**
	 * Pomoćna metoda koja iz {@link List} primjeraka razreda
	 * {@link StudentRecord} <b>records</b> dohvaća {@link List} svih primjeraka
	 * razreda {@link StudentRecord} kojima je konačna ocjena na predmetu 5.
	 *
	 * @param records
	 *            {@link List} primjeraka razreda {@link StudentRecord} koji su
	 *            polagali predmet
	 * @return {@link List} svih primjeraka razreda {@link StudentRecord} kojima
	 *         je konačna ocjena na predmetu 5
	 */
	private static List<StudentRecord> getListOfAStudents(List<StudentRecord> records) {
		return records.stream().filter(record -> record.getGrade() == 5).collect(Collectors.toList());
	}

	/**
	 * Pomoćna metoda koja iz {@link List} primjeraka razreda
	 * {@link StudentRecord} <b>records</b> dohvaća {@link List} svih primjeraka
	 * razreda {@link StudentRecord} kojima je konačna ocjena na predmetu 5.
	 * Lista je sortirana silazno po ukupnom broju bodova koristeći
	 * {@link StudentRecord#POINTS} komparator
	 *
	 * @param records
	 *            {@link List} primjeraka razreda {@link StudentRecord} koji su
	 *            polagali predmet
	 * @return {@link List} svih primjeraka razreda {@link StudentRecord} kojima
	 *         je konačna ocjena na predmetu 5 sortiranih silazno po ukupnom
	 *         broju bodova
	 * 
	 * @see StudentRecord#POINTS
	 */
	private static List<StudentRecord> getSortedListOfAStudents(List<StudentRecord> records) {
		return records.stream().filter(record -> record.getGrade() == 5).sorted(StudentRecord.POINTS.reversed())
				.collect(Collectors.toList());
	}

	/**
	 * Pomoćna metoda koja iz {@link List} primjeraka razreda
	 * {@link StudentRecord} <b>records</b> dohvaća {@link List} svih JMBAGova
	 * ({@link String}) od primjeraka razreda {@link StudentRecord} koji nisu
	 * prošli predmet (konačna ocjena im je 1).
	 *
	 * @param records
	 *            {@link List} primjeraka razreda {@link StudentRecord} koji su
	 *            polagali predmet
	 * @return {@link List} svih JMBAGova od primjeraka razreda
	 *         {@link StudentRecord} koji nisu prošli predmet (konačna ocjena im
	 *         je 1)
	 */
	private static List<String> getListOfNotPassed(List<StudentRecord> records) {
		return records.stream().filter(record -> record.getGrade() == 1).map(StudentRecord::getJmbag)
				.sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());
	}

	/**
	 * Pomoćna metoda koja iz {@link List} primjeraka razreda
	 * {@link StudentRecord} <b>records</b> dohvaća {@link Map} kojoj su
	 * ključevi konačne ocjene na predmetu, a vrijednosti {@link List}
	 * primjeraka razreda {@link StudentRecord} koji su na predmetu ostvarili tu
	 * ocjenu.
	 *
	 * @param records
	 *            {@link List} primjeraka razreda {@link StudentRecord} koji su
	 *            polagali predmet
	 * @return {@link Map} kojoj su ključevi konačne ocjene na predmetu, a
	 *         vrijednosti {@link List} primjeraka razreda {@link StudentRecord}
	 *         koji su na predmetu ostvarili tu ocjenu
	 */
	private static Map<Integer, List<StudentRecord>> classifyStudentsByGrades(List<StudentRecord> records) {
		return records.stream().collect(Collectors.groupingBy(StudentRecord::getGrade));
	}

	/**
	 * Pomoćna metoda koja iz {@link List} primjeraka razreda
	 * {@link StudentRecord} <b>records</b> dohvaća {@link Map} kojoj su
	 * ključevi konačne ocjene na predmetu, a vrijednosti broj primjeraka
	 * razreda {@link StudentRecord} koji su ostvarili tu ocjenu na predmetu
	 * ({@link Integer}).
	 *
	 * @param records
	 *            {@link List} primjeraka razreda {@link StudentRecord} koji su
	 *            polagali predmet
	 * @return {@link Map} kojoj su ključevi konačne ocjene na predmetu, a
	 *         vrijednosti broj primjeraka razreda {@link StudentRecord} koji su
	 *         ostavrili tu ocjenu na predmetu ({@link Integer})
	 */
	private static Map<Integer, Integer> getNumberOfStudentsByGrades(List<StudentRecord> records) {
		return records.stream().collect(Collectors.toMap(StudentRecord::getGrade, record -> 1, Integer::sum));
	}

	/**
	 * Pomoćna metoda koja iz {@link List} primjeraka razreda
	 * {@link StudentRecord} <b>records</b> dohvaća {@link Map} kojoj su
	 * ključevi {@link Boolean#TRUE} za sve primjerke razreda
	 * {@link StudentRecord} koji su prošli predmet(konačna ocjena >= 2),
	 * odnosno {@link Boolean#FALSE} za sve one koji nisu. Popis studenta koji
	 * su prošli ,odnosno nisu prošli vraća se kroz {@link List}e primjeraka
	 * razreda {@link StudentRecord}
	 * 
	 * @param records
	 *            {@link List} primjeraka razreda {@link StudentRecord} koji su
	 *            polagali predmet
	 * @return {@link Map} kojoj su ključevi {@link Boolean#TRUE} za sve
	 *         primjerke razreda {@link StudentRecord} koji su prošli
	 *         predmet(konačna ocjena >= 2), odnosno {@link Boolean#FALSE} za
	 *         sve one koji nisu.
	 */
	private static Map<Boolean, List<StudentRecord>> classifyPassNotPassed(List<StudentRecord> records) {
		return records.stream().collect(Collectors.partitioningBy(record -> record.getGrade() != 1));
	}

	/**
	 * Pomoćna metoda koja parsira predanu {@link List} primjeraka razreda
	 * {@link String} <b>lines</b> u {@link List} primjeraka razreda
	 * {@link StudentRecord}.
	 *
	 * @param lines
	 *            {@link List} primjeraka razreda {@link String} koji se parsira
	 * @return {@link List} primjeraka razreda {@link StudentRecord} koji su
	 *         dobiveni parsiranjem <b>lines</b>
	 */
	private static List<StudentRecord> convert(List<String> lines) {
		List<StudentRecord> records = new ArrayList<>();

		for (String line : lines) {
			line = line.trim();
			if (line.isEmpty()) {
				continue;
			}

			String[] studentData = line.split("\t");
			StudentRecord record = parseStudent(studentData);

			if (records.contains(record)) {
				throw new IllegalArgumentException("Datoteka sadrži jednake studente!");
			}

			records.add(record);
		}

		return records;
	}

	/**
	 * Pomoćna metoda koja iz predanog polja podataka o studentu
	 * <b>studentData</b> stvara primjerak razreda {@link StudentRecord} i vraća
	 * ga kroz povratnu vrijednost.
	 *
	 * @param studentData
	 *            polje podataka o studentu
	 * @return primjerak razreda {@link StudentRecord} koji je dobiven iz
	 *         <b>studentData</b>
	 * @throws IllegalArgumentException
	 *             ukoliko je veličina polja <b>studentData</b> različita broju
	 *             atributa u razredu {@link StudentRecord}
	 */
	private static StudentRecord parseStudent(String[] studentData) {
		if (studentData.length != STUDENT_DATA_LENGTH) {
			throw new IllegalArgumentException(
					"Predali ste krivi broj argumenata. Broj argumenata je: " + studentData.length);
		}
		return new StudentRecord(studentData[JMBAG_INDEX], studentData[LAST_NAME_INDEX], studentData[FIRST_NAME_INDEX],
				Float.parseFloat(studentData[POINTS_MIDTERM_INDEX]), Float.parseFloat(studentData[POINTS_FINAL_INDEX]),
				Float.parseFloat(studentData[POINTS_LABS_INDEX]), Integer.parseInt(studentData[GRADE_INDEX]));
	}
}
