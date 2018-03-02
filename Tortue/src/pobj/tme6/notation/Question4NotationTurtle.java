package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ContextTurtle;
import pobj.tme6.IColorTurtle;
import pobj.tme6.IContext;


public class Question4NotationTurtle {

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
	
	
	// Test de ContextTurtle en utilisant notre propre contexte
	
	private class MockContext implements IContext {
		public void drawLine(int x1, int y1, int x2, int y2, Color color) {
			System.out.println("*" + x1 + " " + y1 + " " + x2 + " " + y2 + " " + color.toString());
		}
	}
	
	@Test public void test1() {
		IColorTurtle t = new ContextTurtle(new MockContext());
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
		assertEqualsNormalized("*0 0 10 0 0x000000ff\n*10 0 24 -14 0x0000ffff\n*45 -35 52 -42 0xff0000ff\n", getOut());
	}	
}
