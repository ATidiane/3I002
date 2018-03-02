package pobj.tme6.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ColorTurtle;
import pobj.tme6.IColorTurtle;
import pobj.tme6.ICommand;
import pobj.tme6.SaveTurtle;

public class Question6Test {

	ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Before public void atBefore() {
		System.setOut(new PrintStream(out));
	}
	
	private String getOut() {
		System.out.flush();
		return out.toString();
	}
	
	@Test public void test() {
		SaveTurtle t = new SaveTurtle();
		t.move(10); t.turn(90);
		t.move(10); t.turn(90);
		t.move(10); t.turn(90);
		t.move(10); t.turn(90);
		ICommand square = t.getCommand();

		IColorTurtle out = new ColorTurtle();
		for (int i=0; i<3; i++) {
		    square.execute(out);
		    out.up();
		    out.move(30);
		    out.down();
		}
		
		assertEquals("0 0 0 10 0x000000ff\n0 10 10 10 0x000000ff\n10 10 10 0 0x000000ff\n10 0 0 0 0x000000ff\n0 30 0 40 0x000000ff\n0 40 10 40 0x000000ff\n10 40 10 30 0x000000ff\n10 30 0 30 0x000000ff\n0 60 0 70 0x000000ff\n0 70 10 70 0x000000ff\n10 70 10 60 0x000000ff\n10 60 0 60 0x000000ff\n", getOut());
	}
	
}
