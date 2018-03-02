package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ColorTurtleAdapter;
import pobj.tme6.IColorTurtle;
import pobj.tme6.ITurtle;
import pobj.tme6.Turtle;


public class Question3NotationMethods {

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

	// Vérification que l'adapter délègue bien à son argument, sans tricher

	private class MockTurtle implements ITurtle {
		public boolean moveCalled = false;
		public boolean turnCalled = false;
		public boolean upCalled = false;
		public boolean downCalled = false;
		public void move(int length) { 
			assertEquals(999, length);
			moveCalled = true;
		}
		public void turn(int angle) { 
			assertEquals(888, angle);
			System.out.println("blabla");
			turnCalled = true; 
		}
		public void up() {
			upCalled = true;
		}
		public void down() {
			downCalled = true;			
		}
	};
	
	@Test public void test1() {
		MockTurtle t = new MockTurtle();
		IColorTurtle c = new ColorTurtleAdapter(t);
		c.up();
		c.down();
		c.move(999);
		c.turn(888);
		assertTrue(t.moveCalled);
		assertTrue(t.turnCalled);
		assertTrue(t.upCalled);
		assertTrue(t.downCalled);
		assertEquals("blabla\n", getOut());
	}
	
	
	@Test public void test2() {
		ITurtle a = new Turtle();
		IColorTurtle t = new ColorTurtleAdapter(a);		
		t.move(50);
		t.turn(90);
		t.setColor(Color.WHITE);
		t.move(40);
		t.turn(90);
		t.setColor(Color.RED);
		t.move(80);
		t.turn(90);
		t.up();
		t.move(10);
		t.down();
		t.move(10);
		assertEqualsNormalized("0 0 0 50\n0 50 40 50\n40 50 40 -30\n30 -30 20 -30\n", getOut());
	}

	// test d'alias
	@Test public void test3() {
		ITurtle a = new Turtle();
		IColorTurtle t1 = new ColorTurtleAdapter(a);		
		IColorTurtle t2 = new ColorTurtleAdapter(a);		
		t1.up();
		t2.move(10);
		t2.down();
		t1.move(20);
		assertEqualsNormalized("0 10 0 30\n", getOut());
	}
}
