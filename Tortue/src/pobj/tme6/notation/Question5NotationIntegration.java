package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ColorTurtle;
import pobj.tme6.CommandDown;
import pobj.tme6.CommandMove;
import pobj.tme6.CommandSetColor;
import pobj.tme6.CommandUp;
import pobj.tme6.IColorTurtle;


public class Question5NotationIntegration {

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
		IColorTurtle t = new ColorTurtle();
		(new CommandMove(90)).execute(t);
		(new CommandUp()).execute(t);
		(new CommandSetColor(Color.CYAN)).execute(t);
		(new CommandMove(90)).execute(t);
		(new CommandDown()).execute(t);
		(new CommandMove(90)).execute(t);
		assertEqualsNormalized("0 0 0 90 0x000000ff\n0 180 0 270 0x00ffffff\n", getOut());
	}
}



