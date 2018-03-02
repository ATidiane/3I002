package pobj.partiel2012nov.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.partiel2012nov.Constante;

public class TestQ1 {

	@Test
	public void testConst() {
		testConstante(10);
		testConstante(-1);
		testConstante(0);
		testConstante(65536);
		// ctor par defaut : cree la constante 0
		Constante cZero = new Constante();
		assertEquals(0, cZero.getValue());
	}

	private void testConstante(int k) {
		Constante c = new Constante(k);
		assertEquals(k, c.getValue());
		assertEquals(Integer.toString(k), c.toString());
	}

}
