package pobj.partiel2012nov.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.partiel2012nov.Add;
import pobj.partiel2012nov.Constante;
import pobj.partiel2012nov.Expression;
import pobj.partiel2012nov.Mul;
import pobj.partiel2012nov.Var;

public class TestQ4 {

	@Test
	public void testEval() {
		Expression e1 = e1() ;
		
		assertEquals("( 2 + 3 ) * 4", e1.toString());
		
		Expression e2 = e2() ;
		
		assertEquals("( x + 3 ) * ( x + 4 )", e2.toString());
		
		Expression e3 = e3() ;
		
		assertEquals("( x + 10 ) * ( y + -8 )", e3.toString());
		
	}

	public static Expression e1 () {
		Constante ct1 = new Constante(2);
		Constante ct2 = new Constante(3);
		Constante ct3 = new Constante(4);
		
		Add add = new Add(ct1, ct2);
		Mul mul = new Mul(add, ct3);
		
		return mul;
	}
	
	public static Expression e2 () {
		Var v1 = new Var("x");
		Constante ct1 = new Constante(3);
		Constante ct2 = new Constante(4);
		
		Add add1 = new Add(v1, ct1);
		Add add2 = new Add(v1, ct2);
		Mul mul = new Mul(add1, add2);
		
		return mul;
	}
	
	public static Expression e3 () {
		Var v1 = new Var("x");
		Constante ct1 = new Constante(10);
		Var v2 = new Var("y");
		Constante ct2 = new Constante(-8);
		
		Add add1 = new Add(v1, ct1);
		Add add2 = new Add(v2, ct2);
		
		Mul mul = new Mul(add1, add2);
		
		return mul;
	}
	
}
