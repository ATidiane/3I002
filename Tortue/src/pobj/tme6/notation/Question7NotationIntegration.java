package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import pobj.tme6.ColorTurtle;
import pobj.tme6.ICommand;
import pobj.tme6.SaveTurtle;
import pobj.tme6.Substitution;


public class Question7NotationIntegration {

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
		t.move(10);
		t.turn(90);
		t.move(10);
		ICommand subst = t.getCommand();
		
		SaveTurtle t2 = new SaveTurtle();
		t2.setColor(Color.BLUE);
		t2.move(10);
		ICommand x = t2.getCommand();
		
		x = Substitution.substitute(x,  subst);
		x = Substitution.substitute(x,  subst);

		x.execute(new ColorTurtle());
		assertEqualsNormalized("0 0 0 10 0x0000ffff\n0 10 10 10 0x0000ffff\n10 10 10 0 0x0000ffff\n10 0 0 0 0x0000ffff\n", getOut());
	}
}



