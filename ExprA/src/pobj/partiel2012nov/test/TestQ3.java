package pobj.partiel2012nov.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.partiel2012nov.Add;
import pobj.partiel2012nov.Constante;
import pobj.partiel2012nov.Expression;
import pobj.partiel2012nov.Mul;
import pobj.partiel2012nov.Var;

public class TestQ3 {

	@Test
	public void testOperators() {
		Var var = new Var ("a");
		Constante ct = new Constante(3);
		
		assertTrue(var instanceof Expression);
		assertTrue(ct instanceof Expression);
		
		Add add = new Add(var, ct);
		assertEquals("( a + 3 )", add.toString());
		assertTrue(add instanceof Expression);
		assertEquals(add.getFg(), var);
		assertEquals(add.getFd(), ct);
				
		Constante c2 = new Constante(5);
		Mul mul = new Mul(c2, add);
		assertTrue(mul instanceof Expression);
		assertEquals("5 * ( a + 3 )", mul.toString());
		assertEquals(mul.getFg(), c2);
		assertEquals(mul.getFd(), add);
	}

}
