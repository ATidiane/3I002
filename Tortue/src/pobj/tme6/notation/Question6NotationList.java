package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javafx.scene.paint.Color;

import org.junit.Test;

import pobj.tme6.CommandDown;
import pobj.tme6.CommandList;
import pobj.tme6.CommandMove;
import pobj.tme6.CommandSetColor;
import pobj.tme6.CommandTurn;
import pobj.tme6.CommandUp;
import pobj.tme6.IColorTurtle;
import pobj.tme6.ICommand;


public class Question6NotationList {

	// Test de classe
	
	@Test public void testClass() {
		CommandList c = new CommandList();
		assertTrue(c instanceof ICommand);
	}
	
	// Test de CommandList en isolation
	
	private class MockTurtle implements IColorTurtle {
		public String out = "";
		public void move(int length) { out += "move" + length; }
		public void turn(int angle) { out += "turn" + angle; }
		public void down() { out += "down"; }
		public void up() { out += "up"; }
		public void setColor(Color c) { out += "setColor"; } 
	};
	
	@Test public void test1() {
		CommandList c = new CommandList();
		c.addCommand(new CommandMove(123));
		c.addCommand(new CommandTurn(987));
		c.addCommand(new CommandSetColor(Color.RED));
		c.addCommand(new CommandUp());
		c.addCommand(new CommandDown());
		
		MockTurtle t = new MockTurtle();
		c.execute(t);
		assertEquals("move123turn987setColorupdown", t.out);
	}
}



