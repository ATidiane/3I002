package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ColorTurtle;
import pobj.tme6.IColorTurtle;


public class Question2NotationMethods {

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
	
	@Test public void testMove() {
		IColorTurtle t = new ColorTurtle();
		t.setColor(Color.RED);
		t.move(10);
		t.setColor(Color.BLUE);
		t.move(20);
		assertEqualsNormalized("0 0 0 10 0xff0000ff\n0 10 0 30 0x0000ffff\n", getOut());
	}

	@Test public void testInit() {
		IColorTurtle t = new ColorTurtle();
		t.move(10);
		t.setColor(Color.BLUE);
		t.move(20);
		assertEqualsNormalized("0 0 0 10 0x000000ff\n0 10 0 30 0x0000ffff\n", getOut());
	}

	// Variante du test fourni à l'étudiant
	@Test public void testComplex() {
		IColorTurtle t = new ColorTurtle();
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
