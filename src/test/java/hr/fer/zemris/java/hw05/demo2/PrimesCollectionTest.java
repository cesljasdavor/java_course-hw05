package hr.fer.zemris.java.hw05.demo2;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class PrimesCollectionTest {

	@Test(expected = IllegalArgumentException.class)
	public void testiranjePredanBrojManjiOd1() {
		new PrimesCollection(-5);
	}
	
	@Test
	public void testiranjeRadaIteratora() {
		PrimesCollection collection = new PrimesCollection(5);
		Iterator<Integer> iter = collection.iterator();
		assertEquals(iter.next().intValue(), 2);
		assertEquals(iter.next().intValue(), 3);
		assertEquals(iter.next().intValue(), 5);
		assertEquals(iter.next().intValue(), 7);
		assertEquals(iter.next().intValue(), 11);
	}
	
	@Test
	public void testiranjeNeovisnostiIteratora() {
		PrimesCollection collection = new PrimesCollection(5);
		Iterator<Integer> iter1 = collection.iterator();
		Iterator<Integer> iter2 = collection.iterator();
		assertEquals(iter1.next().intValue(), 2);
		assertEquals(iter2.next().intValue(), 2);
		assertEquals(iter1.next().intValue(), 3);
		assertEquals(iter2.next().intValue(), 3);
		assertEquals(iter1.next().intValue(), 5);
		assertEquals(iter2.next().intValue(), 5);
		assertEquals(iter1.next().intValue(), 7);
		assertEquals(iter2.next().intValue(), 7);
		assertEquals(iter1.next().intValue(), 11);
		assertEquals(iter2.next().intValue(), 11);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testiranjeNemaViseElemenata() throws Exception {
		Iterator<Integer> iterator = new PrimesCollection(1).iterator();
		iterator.next();
		iterator.next();
	}
	
	@Test
	public void testiranjeNemaViseElemenataHasNext() throws Exception {
		Iterator<Integer> iterator = new PrimesCollection(1).iterator();
		assertTrue(iterator.hasNext());
		iterator.next();
		assertFalse(iterator.hasNext());
	}
	
}
