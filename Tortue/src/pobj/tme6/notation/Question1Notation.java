package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ITurtle;
import pobj.tme6.Turtle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Question1Notation {

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
	
	@Test public void testMoveOne() {
		ITurtle t = new Turtle();
		t.move(10);
		assertEqualsNormalized("0 0 0 10\n", getOut());
	}

	@Test public void testMoveTwo() {
		ITurtle t = new Turtle();
		t.move(10);
		t.move(20);
		assertEqualsNormalized( "0 0 0 10\n0 10 0 30\n", getOut());
	}

	@Test public void testMoveNegative() {
		ITurtle t = new Turtle();
		t.move(-100);
		assertEqualsNormalized("0 0 0 -100\n", getOut());
	}

	@Test public void testUp() {
		ITurtle t = new Turtle();
		t.up();
		t.move(10);
		assertEqualsNormalized("", getOut());
	}

	@Test public void testDown() {
		ITurtle t = new Turtle();
		t.up();
		t.move(10);
		t.down();
		t.move(20);
		assertEqualsNormalized("0 10 0 30\n", getOut());
	}

	@Test public void testTurn() {
		ITurtle t = new Turtle();
		t.move(10);
		t.turn(90);
		t.move(20);
		assertEqualsNormalized("0 0 0 10\n0 10 20 10\n", getOut());
	}

	@Test public void testTurnNegative() {
		ITurtle t = new Turtle();
		t.move(10);
		t.turn(-90);
		t.move(20);
		assertEqualsNormalized("0 0 0 10\n0 10 -20 10\n", getOut());
	}

	// Test fourni à l'étudiant
	@Test public void testComplexGiven() {
		ITurtle t = new Turtle();
		t.move(10);
		t.turn(45);
		t.move(20);
		t.up();
		t.move(30);
		t.down();
		t.move(10);
		assertEqualsNormalized("0 0 0 10\n0 10 14 24\n35 45 42 52\n", getOut());
	}

	// Test d'instances multiples
	@Test public void testTwinTurtules() {
		ITurtle t1 = new Turtle();
		ITurtle t2 = new Turtle();
		t1.move(10);
		t2.move(20);
		t1.move(30);
		assertEqualsNormalized("0 0 0 10\n0 0 0 20\n0 10 0 40\n", getOut());
	}
}

