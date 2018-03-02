package pobj.tme6.test;

import static org.junit.Assert.assertEquals;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ColorTurtle;
import pobj.tme6.IColorTurtle;

public class Question2Test {

	ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Before public void atBefore() {
		System.setOut(new PrintStream(out));
	}
	
	private String getOut() {
		System.out.flush();
		return out.toString();
	}
	
	@Test public void test() {
		IColorTurtle t = new ColorTurtle();
		t.setColor(Color.RED);
		t.move(10);
		t.turn(90);
		t.setColor(Color.BLUE);
		t.move(20);
		assertEquals("0 0 0 10 0xff0000ff\n0 10 20 10 0x0000ffff\n", getOut());
	}

}
