package pobj.tme5.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.InvalidCountException;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetDecorator;

public class HashMultiSetTest {
	
	MultiSet<String> m;
	
	@Before 
	public void createMultiSet() {
		MultiSet<String> n = new HashMultiSet<String>();
		m = new MultiSetDecorator<String>(n);
	}
	
	@Test
	public void testAdd1() throws InvalidCountException {
		m.add("a");
		m.add("a", 5);
		assertEquals(m.count("a"), 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAdd2() throws InvalidCountException {
		m.add("a");
		m.add("a", -1);
	}
	
	@Test
	public void testAdd3() throws InvalidCountException {
		m.add("a", 0);
		assertEquals(m.count("a"), 0);
		m.add("a");
		m.add("a", 0);
		assertEquals(m.count("a"), 1);
	}
	
	@Test
	public void testRemove1() throws InvalidCountException {
		m.add("a", 7);
		m.remove("a");
		m.remove("a", 2);
		assertEquals(m.count("a"), 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestRemove2() throws InvalidCountException {
		m.add("a", 11);
		m.remove("a",-1);
	}
	
	public void TestRemove3() throws InvalidCountException {
		m.add("a", 3);
		m.remove("a", 0);
		assertEquals(m.count("a"), 3);
	}
	
	@Test
	public void TestCount() throws InvalidCountException {
		assertEquals(m.count("a"), 0);
	}
	
	@Test
	public void TestAddRemove1() throws InvalidCountException {
		m.add("a", 111);
		m.add("b", 121);
		assertEquals(m.size(), 232);
		m.remove("b", 11);
		assertEquals(m.count("b"), 110);
		m.remove("a", 11);
		assertEquals(m.size(), 210);
		m.add("c", 22);
		assertEquals(m.size(), 232);
	}
	
	@Test
	public void TestAddRemove2() throws InvalidCountException {
		m.add("a", 13);
		m.remove("a", 13);
		assertEquals(m.count("a"), 0);
	}
	
	
	
	@Test
	public void TestSize() throws InvalidCountException {
		m.add("a", 13);
		m.remove("a");
		m.remove("a", 3);
		m.add("a");
		assertEquals(m.size(), 10);
	}
	
	@Test
	public void TestClear() throws InvalidCountException {
		m.add("a", 13);
		m.remove("a");
		m.clear();
		assertEquals(m.count("a"), 0);
		assertEquals(m.size(), 0);
	}
	
	@Test
	public void TestToString() {
		m.add("a", 27);
		m.add("b", 132);
		m.add("c", 34);
		assertEquals(m.toString(), "[a:27; b:132; c:34]");
	}
	
	
}
