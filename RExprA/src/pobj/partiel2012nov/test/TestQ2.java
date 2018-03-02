package pobj.partiel2012nov.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.partiel2012nov.Var;

public class TestQ2 {
	
	
	@Test
	public void testVar() {
		Var var = new Var("a");
		Var var2 = new Var("a");
		Var var3 = new Var("b");
		
		// test definition equals
		assertTrue(var2.equals( var) );
		assertTrue(var2.equals( (Object) var) );
		
		assertFalse(var3.equals( var) );
		assertFalse(var3.equals( var2) );
		
		assertEquals("a", var.getNom() );
		assertEquals("b", var3.getNom() );
	
		assertEquals("a", var.toString() );
		assertEquals("a", var2.toString() );
		assertEquals("b", var3.toString() );
	}
	
}

