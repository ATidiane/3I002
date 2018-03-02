package pobj.tme6.extra;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Application;

import pobj.tme6.BoundContext;
import pobj.tme6.ContextTurtle;
import pobj.tme6.ICommand;
import pobj.tme6.SaveTurtle;
import pobj.tme6.Substitution;

public class KochMain
extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		final int size = 10000; // pour avoir assez de précision
		final int iters = 5; 
		
		SaveTurtle t = new SaveTurtle();
		t.setColor(Color.rgb(128,128,128));
		t.move(size);
		t.turn(60);
		t.setColor(Color.rgb(0,0,128));
		t.move(size);
		t.turn(-120);
		t.move(size);
		t.turn(60);
		t.move(size);
		ICommand subst = t.getCommand();
		
		SaveTurtle t2 = new SaveTurtle();
		t2.move(size);
		t2.turn(-120);
		t2.move(size);
		t2.turn(-120);
		t2.move(size);
		ICommand x = t2.getCommand();
		
		for (int i = 0; i < iters; i++) {
			x = Substitution.substitute(x,  subst);
		}
		
		BoundContext b = new BoundContext();
		x.execute(new ContextTurtle(b));
		
		JFXContext out = new JFXContext(stage, b.getMinX(),b.getMinY(),b.getMaxX(),b.getMaxY());		
		x.execute(new ContextTurtle(out));
	}

	public static void main(String args[]) {
		launch(args);
	}

}

