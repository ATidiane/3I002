package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javafx.scene.paint.Color;

import org.junit.Test;

import pobj.tme6.IColorTurtle;
import pobj.tme6.SaveTurtle;


public class Question6NotationSave {

	
	// Test de classe
	
	@Test public void testClass() {
		SaveTurtle t = new SaveTurtle();
		assertTrue(t instanceof IColorTurtle);
	}
	
	// Test de SaveTurtle en isolation
	
	private class MockTurtle implements IColorTurtle {
		public String out = "";
		public void move(int length) { out += "move" + length; }
		public void turn(int angle) { out += "turn" + angle; }
		public void down() { out += "down"; }
		public void up() { out += "up"; }
		public void setColor(Color c) { out += "setColor"; } 
	};
	
	@Test public void test1() {
		SaveTurtle s = new SaveTurtle();
		s.move(123);
		s.turn(987);
		s.setColor(Color.RED);
		s.up();
		s.down();
		
		MockTurtle t = new MockTurtle();
		s.getCommand().execute(t);
		assertEquals("move123turn987setColorupdown", t.out);
	}

}



