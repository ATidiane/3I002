package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ColorTurtle;
import pobj.tme6.CommandList;
import pobj.tme6.CommandMove;
import pobj.tme6.CommandSetColor;
import pobj.tme6.CommandTurn;
import pobj.tme6.ICommand;
import pobj.tme6.Substitution;


public class Question7NotationSubstitute {

	// Common for all tests
	
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Before public void atBefore() {
		System.setOut(new PrintStream(out));
	}
	
	private String getOut() {
		System.out.flush();
		return out.toString();
	}
		
	private String normalize(String r) {
		return r.replaceAll("[\n ]","");
	}

	private void assertEqualsNormalized(String a, String b) {
		assertEquals(normalize(a), normalize(b));
	}
	
	
	// Specific
	
	@Test public void test() {
		CommandList l1 = new CommandList();
		l1.addCommand(new CommandMove(10));
		l1.addCommand(new CommandTurn(90));
		l1.addCommand(new CommandMove(10));
	
		CommandList l2 = new CommandList();
		l2.addCommand(new CommandSetColor(Color.RED));
		l2.addCommand(new CommandMove(10));
		l2.addCommand(new CommandSetColor(Color.BLUE));
		l2.addCommand(new CommandMove(10));
	
		ICommand c = Substitution.substitute(l2, l1);
		c.execute(new ColorTurtle());
		assertEqualsNormalized("0 0 0 10 0xff0000ff\n0 10 10 10 0xff0000ff\n10 10 20 10 0x0000ffff\n20 10 20 0 0x0000ffff\n", getOut());
	}
}



