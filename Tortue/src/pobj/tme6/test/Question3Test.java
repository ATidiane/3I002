package pobj.tme6.test;

import static org.junit.Assert.assertEquals;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ColorTurtleAdapter;
import pobj.tme6.IColorTurtle;
import pobj.tme6.ITurtle;
import pobj.tme6.Turtle;

public class Question3Test {

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
		IColorTurtle ct = new ColorTurtleAdapter(t);
		ct.setColor(Color.RED);
		ct.turn(90);
		ct.move(10);
		assertEquals("0 0 10 0\n", getOut());
	}

}
