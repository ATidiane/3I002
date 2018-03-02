package pobj.tme6.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ITurtle;
import pobj.tme6.Turtle;

public class Question1Test {

	ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Before public void atBefore() {
		System.setOut(new PrintStream(out));
	}
	
	private String getOut() {
		System.out.flush();
		return out.toString();
	}

	@Test public void test() {
		ITurtle t = new Turtle();
		t.move(10);
		t.turn(45);
		t.move(20);
		t.up();
		t.move(30);
		t.down();
		t.move(10);
		assertEquals("0 0 0 10\n0 10 14 24\n35 45 42 52\n", getOut());
	}

}
