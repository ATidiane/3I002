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
			
			VisiteurEval vteval = new VisiteurEval();
			
			Constante c1 = new Constante(7);
			Constante c2 = new Constante(3);
			
			Add add = new Add(c1, c2);
			Integer r1 = 10;
			assertEquals(r1, add.accepte(vteval));
					
			Constante c3 = new Constante(5);
			Mul mul = new Mul(c3, add);
			Integer r2 = 50;
			assertEquals(r2, mul.accepte(vteval));
					
		}

}

