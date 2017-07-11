package hr.fer.zemris.java.hw05.demo4;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentRecordTest {

	@Test
	public void testiranjeKomparatora() throws Exception {
		StudentRecord record1 = new StudentRecord("0000000001", "Prezime001", "Ime001", 23.45f, 29.75f, 7.28f, 2);
		StudentRecord record2 = new StudentRecord("0000000062", "Prezime062", "Ime062", 33.89f, 33.52f, 14.75f, 4);
		//isti kao jedan ali čisto da se vidi da nije jednakost po identitetu
		StudentRecord record3 = new StudentRecord("0000000001", "Prezime001", "Ime001", 23.45f, 29.75f, 7.28f, 2);
		
		assertEquals(StudentRecord.POINTS.compare(record1, record2), -1);
		assertEquals(StudentRecord.POINTS.compare(record2, record1), 1);
		assertEquals(StudentRecord.POINTS.compare(record1, record3), 0);
	}
}
