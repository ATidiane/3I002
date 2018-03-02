package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import javafx.scene.paint.Color;

import org.junit.Test;

import pobj.tme6.CommandSetColor;
import pobj.tme6.IColorTurtle;
import pobj.tme6.ICommand;


public class Question5NotationSetColor {

	private class MockTurtle implements IColorTurtle {
		public boolean ok = false;
		public void move(int length) {fail(); }
		public void turn(int angle) { fail(); }
		public void up() { fail(); }
		public void down() { fail(); }
		public void setColor(Color c) { assertFalse(ok); assertEquals(Color.RED, c); ok = true; } 
	};
	
	@Test public void test1() {
		ICommand c = new CommandSetColor(Color.RED);
		MockTurtle t = new MockTurtle();
		c.execute(t);
		assertTrue(t.ok);
	}

}



