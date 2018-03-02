package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.BoundContext;
import pobj.tme6.ContextTurtle;
import pobj.tme6.IColorTurtle;
import pobj.tme6.IContext;
import pobj.tme6.PrintContext;


public class Question4NotationIntegration {

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
	
	@Test public void test1() {
		BoundContext c = new BoundContext();        
		IColorTurtle t = new ContextTurtle(c);
		t.turn(90);
		t.move(10);
		t.setColor(Color.BLUE);
		t.turn(45);
		t.move(20);
		t.up();
		t.move(30);
		t.down();
		t.setColor(Color.RED);
		t.move(10);
		assertEquals(0, c.getMinX());
		assertEquals(-42, c.getMinY());
		assertEquals(52, c.getMaxX());
		assertEquals(0, c.getMaxY());
	}	
	
	@Test public void test2() {
		IContext c = new PrintContext();        
		IColorTurtle t = new ContextTurtle(c);
		t.turn(90);
		t.move(10);
		t.setColor(Color.BLUE);
		t.turn(45);
		t.move(20);
		t.up();
		t.move(30);
		t.down();
		t.setColor(Color.RED);
		t.move(10);
		assertEqualsNormalized("0 0 10 0 0x000000ff\n10 0 24 -14 0x0000ffff\n45 -35 52 -42 0xff0000ff\n", getOut());
	}	
}
