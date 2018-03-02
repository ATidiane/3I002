package pobj.tme6.extra;

import javafx.application.Application;
import javafx.stage.Stage;

import pobj.tme6.ContextTurtle;
import pobj.tme6.IColorTurtle;

public class MaisonMain extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		JFXContext ctx = new JFXContext(stage,-200,-200,300,200);
		
		IColorTurtle t = new ContextTurtle(ctx);
		t.move(100);
		t.turn(90);
		t.move(100);
		t.turn(90);
		t.move(100);
		t.turn(30);
		t.move(100);
		t.turn(120);
		t.move(100);
		t.turn(120);
		t.move(100);
	}
	
	public static void main(String args[]) {
		launch(args);
	}
}
