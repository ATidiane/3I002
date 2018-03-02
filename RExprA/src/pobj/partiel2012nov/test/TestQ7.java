package pobj.partiel2012nov.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.partiel2012nov.Add;
import pobj.partiel2012nov.Constante;
import pobj.partiel2012nov.Mul;
import pobj.partiel2012nov.VisiteurEval;

public class TestQ7 {

	@Test
	public void testEval() {
		
		VisiteurEval vts = new VisiteurEval();
		Constante ct0 = new Constante(5);
		Constante ct = new Constante(3);
		
		Add add = new Add(ct0, ct);
		assertEquals(new Integer(8), add.accepte(vts));
				
		Constante c2 = new Constante(5);
		Mul mul = new Mul(c2, add);
		assertEquals(new Integer(40), mul.accepte(vts));
				
	}

}
