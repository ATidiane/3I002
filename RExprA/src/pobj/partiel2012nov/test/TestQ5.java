package pobj.partiel2012nov.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.partiel2012nov.Add;
import pobj.partiel2012nov.Constante;
import pobj.partiel2012nov.Expression;
import pobj.partiel2012nov.Mul;
import pobj.partiel2012nov.Var;

public class TestQ5 {

	@Test
	public void testEval() {
		Constante ct = new Constante(3);
		Constante ct2 = new Constante(5);
		Constante ct3 = new Constante(17);
		assertEquals(3, ct.eval());;
		assertEquals(5, ct2.eval());;
		assertEquals(17, ct3.eval());;
		
		Expression var = new Var("a");
		try {
			var.eval();
			// should raise an exception
			fail();
		} catch (UnsupportedOperationException e) {
			// happy
			assertTrue(true);
		}
		
		Expression add = new Add(ct, ct2);
		assertEquals((5+3), add.eval());

		Expression mul = new Mul(ct3, add);
		assertEquals(17 * (5+3), mul.eval());
		
		Expression tot = new Mul(mul, var);
		
		try {
			tot.eval();
			// should raise an exception
			fail();
		} catch (UnsupportedOperationException e) {
			// happy
			assertTrue(true);
		}
	}

}
