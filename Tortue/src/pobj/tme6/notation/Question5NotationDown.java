package pobj.tme6.notation;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import javafx.scene.paint.Color;

import org.junit.Test;

import pobj.tme6.CommandDown;
import pobj.tme6.IColorTurtle;
import pobj.tme6.ICommand;


public class Question5NotationDown {

	private class MockTurtle implements IColorTurtle {
		public boolean ok = false;
		public void move(int length) { fail(); }
		public void turn(int angle) { fail(); }
		public void up() { fail(); }
		public void down() { assertFalse(ok); ok = true; }
		public void setColor(Color c) { fail(); } 
	};
	
	@Test public void test1() {
		ICommand c = new CommandDown();
		MockTurtle t = new MockTurtle();
		c.execute(t);
		assertTrue(t.ok);
	}

}



