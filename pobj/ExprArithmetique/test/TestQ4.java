package pobj.partiel2012nov.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.partiel2012nov.Expression;

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
		return /* TODO*/ ;
	}
	
	public static Expression e2 () {
		return /* TODO*/ ;
	}
	
	
	public static Expression e3 () {
		return /* TODO*/ ;
	}
	
}
