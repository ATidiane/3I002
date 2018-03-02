package pobj.tme6.notation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pobj.tme6.ColorTurtle;
import pobj.tme6.IColorTurtle;
import pobj.tme6.ITurtle;
import pobj.tme6.Turtle;


public class Question2NotationClass {

	@Test public void testClass() {
		IColorTurtle t = new ColorTurtle();
		assertTrue(t instanceof ITurtle);
		assertTrue(t instanceof Turtle);
		assertTrue(ColorTurtle.class.getSuperclass() == Turtle.class);
		assertTrue(IColorTurtle.class.isInterface());
	}
}



