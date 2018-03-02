package pobj.tme6.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ColorTurtle;
import pobj.tme6.CommandMove;
import pobj.tme6.IColorTurtle;
import pobj.tme6.ICommand;

public class Question5Test {

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
		ICommand cmd = new CommandMove(10);
		cmd.execute(t);
		cmd.execute(t);
		assertEquals("0 0 0 10 0x000000ff\n0 10 0 20 0x000000ff\n", getOut());
	}

}
