package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.Assert.*;

import java.net.Socket;

import javax.swing.JFrame;

import org.junit.Test;

public class ValueWrapperTest {

	@Test
	public void testiranjePredanNull() {
		assertEquals(new ValueWrapper(null).getValue(), null);
	}

	@Test
	public void testiranjePredanaBiloKojaVrijednost() {
		assertEquals((String) new ValueWrapper("Bilo koja vrijednost").getValue(), "Bilo koja vrijednost");
	}

	@Test
	public void testiranjeSumaVrijednostiInt() {
		ValueWrapper valueWrapper = new ValueWrapper(Integer.valueOf(100));
		valueWrapper.add("5");
		assertEquals(valueWrapper.getValue(), 105);
	}

	@Test
	public void testiranjeSumaVrijednostiDouble() {
		ValueWrapper valueWrapper = new ValueWrapper(Double.valueOf(100.0));
		valueWrapper.add("5.0");
		assertEquals(valueWrapper.getValue().getClass(), Double.class);
		assertEquals(valueWrapper.getValue(), 105.0);
	}

	@Test
	public void testiranjeSumaVrijednostiDoubleIInt() {
		ValueWrapper valueWrapper = new ValueWrapper(Double.valueOf(100.0));
		valueWrapper.add(Integer.valueOf(5));
		assertEquals(valueWrapper.getValue().getClass(), Double.class);
		assertEquals(valueWrapper.getValue(), 105.0);
	}

	@Test
	public void testiranjeSumaVrijednostiIntIDouble() {
		ValueWrapper valueWrapper = new ValueWrapper(Integer.valueOf(5));
		valueWrapper.add(Double.valueOf(100.0));
		assertEquals(valueWrapper.getValue().getClass(), Double.class);
		assertEquals(valueWrapper.getValue(), 105.0);
	}

	@Test
	public void testiranjeParsiranjeStringSaEksponentom() {
		ValueWrapper valueWrapper = new ValueWrapper("0.5E1");
		valueWrapper.add("1E2");
		assertEquals(valueWrapper.getValue().getClass(), Double.class);
		assertEquals(valueWrapper.getValue(), 105.0);
	}

	@Test
	public void testiranjeOduzimanje() {
		ValueWrapper valueWrapper = new ValueWrapper(5);
		valueWrapper.subtract(10);
		assertEquals((Integer) valueWrapper.getValue(), Integer.valueOf(-5));
	}

	@Test
	public void testiranjeMnozenje() {
		ValueWrapper valueWrapper = new ValueWrapper(5);
		valueWrapper.multiply(10);
		assertEquals((Integer) valueWrapper.getValue(), Integer.valueOf(50));
	}

	@Test
	public void testiranjeDijeljenje() {
		ValueWrapper valueWrapper = new ValueWrapper(5);
		valueWrapper.divide(10);
		assertEquals((Integer) valueWrapper.getValue(), Integer.valueOf(0));
	}

	@Test(expected = RuntimeException.class)
	public void testiranjeDijeljenjeSNulom() {
		ValueWrapper valueWrapper = new ValueWrapper(5);
		valueWrapper.divide(0);
	}

	@Test
	public void testiranjeZbrajanjeOduzimanjeIMnozenjeNull() {
		ValueWrapper valueWrapper1 = new ValueWrapper(null);
		ValueWrapper valueWrapper2 = new ValueWrapper(null);
		valueWrapper1.add(valueWrapper2.getValue());
		assertEquals(valueWrapper1.getValue(), 0);
		valueWrapper1.subtract(valueWrapper2.getValue());
		assertEquals(valueWrapper1.getValue(), 0);
		valueWrapper1.multiply(valueWrapper2.getValue());
		assertEquals(valueWrapper1.getValue(), 0);
	}

	@Test(expected = RuntimeException.class)
	public void testiranjeDijeljenjeNull() {
		ValueWrapper valueWrapper1 = new ValueWrapper(null);
		ValueWrapper valueWrapper2 = new ValueWrapper(null);
		valueWrapper1.divide(valueWrapper2.getValue());
	}

	@Test
	public void testiranjeStringDoubleIInt() {
		ValueWrapper v3 = new ValueWrapper("1.2E1");
		ValueWrapper v4 = new ValueWrapper(Integer.valueOf(1));
		v3.add(v4.getValue());
		assertEquals(v3.getValue(), Double.valueOf(13));
	}

	@Test
	public void testiranjeStringIntIInt() {
		ValueWrapper v5 = new ValueWrapper("12");
		ValueWrapper v6 = new ValueWrapper(Integer.valueOf(1));
		v5.add(v6.getValue());
		assertEquals(v5.getValue(), Integer.valueOf(13));
	}

	@Test(expected = RuntimeException.class)
	public void testiranjeStringNijeMoguceParsirati() {
		ValueWrapper v7 = new ValueWrapper("Ankica");
		ValueWrapper v8 = new ValueWrapper(Integer.valueOf(1));
		v7.add(v8.getValue());
	}

	/**
	 * Objekti drugih razreda su svi objekti koji nisu primjerci razreda
	 * {@link String}, {@link Integer} ili {@link Double} i nisu <code>null</code>
	 */
	@Test(expected = RuntimeException.class)
	public void testiranjeOperacijaNadObjektimaDrugihRazreda() {
		ValueWrapper v7 = new ValueWrapper(new JFrame());
		ValueWrapper v8 = new ValueWrapper(new Socket());
		v7.add(v8.getValue());
	}

	@Test
	public void testiranjeJeLiSeDrugaVrijednostPromijenila() {
		ValueWrapper v7 = new ValueWrapper(1986);
		ValueWrapper v8 = new ValueWrapper(Integer.valueOf(1));
		v7.add(v8.getValue());
		assertEquals(v8.getValue(), Integer.valueOf(1));
		v7.subtract(v8.getValue());
		assertEquals(v8.getValue(), Integer.valueOf(1));
		v7.divide(v8.getValue());
		assertEquals(v8.getValue(), Integer.valueOf(1));
		v7.multiply(v8.getValue());
		assertEquals(v8.getValue(), Integer.valueOf(1));
	}

	@Test
	public void testiranjeKomparatora() {
		ValueWrapper v7 = new ValueWrapper(1986);
		ValueWrapper v8 = new ValueWrapper(Integer.valueOf(1));
		ValueWrapper nullWrapper = new ValueWrapper(null);
		assertEquals(v7.numCompare(v8.getValue()), 1);
		assertEquals(v8.numCompare(v7.getValue()), -1);
		assertEquals(v8.numCompare(v8.getValue()), 0);
		assertEquals(nullWrapper.numCompare(nullWrapper.getValue()), 0);
		assertEquals(nullWrapper.numCompare(Integer.valueOf(0)), 0);
		assertEquals(nullWrapper.numCompare(Integer.valueOf(1)), -1);
	}

}
