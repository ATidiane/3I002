package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ColorTurtle;
import pobj.tme6.IColorTurtle;
import pobj.tme6.ICommand;
import pobj.tme6.SaveTurtle;


public class Question6NotationIntegration {

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
		SaveTurtle t = new SaveTurtle();
		t.setColor(Color.BLUE);
		t.move(100); t.turn(-180);
		t.move(100); t.turn(-180);
		t.move(100); t.turn(-180);
		ICommand tri = t.getCommand();

		IColorTurtle out = new ColorTurtle();
		for (int i=0; i<3; i++) {
		    tri.execute(out);
		    out.up();
		    out.move(30);
		    out.down();
		}
		assertEqualsNormalized( "0 0 0 100 0x0000ffff\n0 100 0 0 0x0000ffff\n0 0 0 100 0x0000ffff\n0 70 0 -30 0x0000ffff\n0 -30 0 70 0x0000ffff\n0 70 0 -30 0x0000ffff\n0 0 0 100 0x0000ffff\n0 100 0 0 0x0000ffff\n0 0 0 100 0x0000ffff\n", getOut());
	}
}



