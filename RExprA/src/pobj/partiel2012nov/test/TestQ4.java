package pobj.partiel2012nov.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.partiel2012nov.Add;
import pobj.partiel2012nov.Constante;
import pobj.partiel2012nov.Expression;
import pobj.partiel2012nov.Mul;
import pobj.partiel2012nov.Var;
import pobj.partiel2012nov.VisiteurEval;
import pobj.partiel2012nov.VisiteurEvalVar;

public class TestQ4 {

	@Test
	public void testEval() {
		Expression e1 = e1() ;
		
		//assertEquals("( 2 + 3 ) * 4", e1.toString());
		
		Expression e2 = e2() ;
		
		//assertEquals("( x + 3 ) * ( x + 4 )", e2.toString());
		
		Expression e3 = e3() ;
		
		//assertEquals("( x + 10 ) * ( y + -8 )", e3.toString());
		
		VisiteurEvalVar vev1 = new VisiteurEvalVar(TestQ8.env2());
		Integer resultat = e2().accepte(vev1);
		assertEquals((Integer) 240, resultat);
		
	}

	public static Expression e1 () {
		Expression a, m;
		a = new Add(new Constante(2), new Constante(3));
		m = new Mul(a, new Constante(4));
		
		return m; 
	}
	
	public static Expression e2 () {
		Expression a, b, m;
		a = new Add(new Var("x"), new Constante(3));
		b = new Add(new Var("x"), new Constante(4));
		m = new Mul(a, b);
		
		return m; 
	}
	
	
	public static Expression e3 () {
		Expression a, b, m;
		a = new Add(new Var("x"), new Constante(10));
		b = new Add(new Var("y"), new Constante(-8));
		m = new Mul(a, b);
		
		return m;
	}
	
}
