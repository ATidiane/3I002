package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.IContext;
import pobj.tme6.PrintContext;


public class Question4NotationPrint {

	// Common for all tests
	
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Before public void atBefore() {
		System.setOut(new PrintStream(out));
	}
	
	private String getOut() {
		System.out.flush();
		return out.toString();
	}
		
	private String normalize(String r) {
		return r.replaceAll("[\n ]","");
	}

	private void assertEqualsNormalized(String a, String b) {
		assertEquals(normalize(a), normalize(b));
	}
	
	
	// Specific
	
	
	// Test de PrintContext
	
	@Test public void testClass() {
		PrintContext ctx = new PrintContext();
		assertTrue(ctx instanceof IContext);
		
	}

	@Test public void test1() {		
		IContext ctx = new PrintContext();
		ctx.drawLine(10,10,100,100,Color.RED);
		ctx.drawLine(110,110,200,200,Color.BLUE);
		assertEqualsNormalized( "10 10 100 100 0xff0000ff\n110 110 200 200 0x0000ffff\n", getOut());
	}	
}
