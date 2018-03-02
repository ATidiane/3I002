package pobj.partiel2012nov.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.partiel2012nov.Add;
import pobj.partiel2012nov.Constante;
import pobj.partiel2012nov.Mul;
import pobj.partiel2012nov.Var;
import pobj.partiel2012nov.VisiteurTS;

public class TestQ6 {

	@Test
	public void testEval() {
		
		VisiteurTS vts = new VisiteurTS();
		Var var = new Var ("a");
		Constante ct = new Constante(3);
		
		Add add = new Add(var, ct);
		assertEquals("( a + 3 )", add.accepte(vts));
				
		Constante c2 = new Constante(5);
		Mul mul = new Mul(c2, add);
		assertEquals("5 * ( a + 3 )", mul.accepte(vts));
				
	}

}
