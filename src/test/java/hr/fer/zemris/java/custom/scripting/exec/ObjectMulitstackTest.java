package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ObjectMulitstackTest {
	private ObjectMultistack multistack;

	@Before
	public void inicijalizacija() {
		multistack = new ObjectMultistack();
	}

	@Test
	public void testiranjeDodavanja() {
		multistack.push("test", new ValueWrapper(1));
		assertEquals(multistack.peek("test"), new ValueWrapper(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testiranjeDodavanjaKljucNull() {
		multistack.push(null, new ValueWrapper(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testiranjeDodavanjaVrijednostNull() {
		multistack.push("test", null);
	}

	@Test
	public void testiranjePogledaNaStog() {
		multistack.push("test", new ValueWrapper(1));
		multistack.push("test", new ValueWrapper("test"));
		assertEquals(multistack.peek("test"), new ValueWrapper("test"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testiranjePogledaNaStogKljucNull() {
		multistack.push("test", new ValueWrapper(1));
		multistack.push("test", new ValueWrapper("test"));
		multistack.peek(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testiranjePogledaNaStogKljucNePostoji() {
		multistack.push("test", new ValueWrapper(1));
		multistack.push("test", new ValueWrapper("test"));
		multistack.peek("test5t");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testiranjePogledaNaStogStogStogPrazan() {
		multistack.push("test", new ValueWrapper(1));
		multistack.pop("test");
		multistack.peek("test");
	}

	@Test
	public void testiranjeSkidanjaSaStoga() {
		multistack.push("test", new ValueWrapper(1));
		multistack.push("test", new ValueWrapper("test"));
		assertEquals(multistack.pop("test"), new ValueWrapper("test"));
		assertEquals(multistack.pop("test"), new ValueWrapper(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testiranjeSkidanjaSaStogaKljucNull() {
		multistack.push("test", new ValueWrapper(1));
		multistack.pop(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testiranjeSkidanjaSaStogaKljucNePostoji() {
		multistack.push("test", new ValueWrapper(1));
		multistack.pop("test3");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testiranjeSkidanjaSaStogaStogPrazan() {
		multistack.push("test", new ValueWrapper(1));
		multistack.pop("test");
		multistack.pop("test");
	}

	@Test
	public void testiranjeSlozeniPrimjer() {
		multistack.push("test", new ValueWrapper(1986));
		multistack.push("test2", new ValueWrapper(1983));
		multistack.push("test", new ValueWrapper("Stog"));
		multistack.push("test3", new ValueWrapper("Stog25"));

		multistack.pop("test");
		assertEquals(multistack.peek("test"), new ValueWrapper(1986));
		multistack.pop("test");
		assertTrue(multistack.isEmpty("test"));
		multistack.push("test", new ValueWrapper("ponovo sam pun"));
		assertFalse((multistack.isEmpty("test")));
		assertEquals(multistack.pop("test"), new ValueWrapper("ponovo sam pun"));
		assertEquals(multistack.peek("test3"), new ValueWrapper("Stog25"));
	}

}
