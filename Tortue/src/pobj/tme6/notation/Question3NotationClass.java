package pobj.tme6.notation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pobj.tme6.ColorTurtleAdapter;
import pobj.tme6.IColorTurtle;
import pobj.tme6.ITurtle;
import pobj.tme6.Turtle;


public class Question3NotationClass {
	
	@Test public void testClass() {
		ITurtle t = new Turtle();
		@SuppressWarnings("unused")
		IColorTurtle c = new ColorTurtleAdapter(t);
		assertTrue(ColorTurtleAdapter.class.getSuperclass() == Object.class);
	}


}