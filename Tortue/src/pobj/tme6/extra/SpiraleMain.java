package pobj.tme6.extra;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import pobj.tme6.ContextTurtle;
import pobj.tme6.IColorTurtle;

public class SpiraleMain 
extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		JFXContext ctx = new JFXContext(stage,-200,-175,200,175);
		
		IColorTurtle t = new ContextTurtle(ctx);
		for (int i=0; i<480; i++) {
			t.turn(15);
			for (int j=0; j<12; j++) {
				t.setColor(Color.rgb(128+10*j,64+5*j,64+5*j));
				t.move(20);
				t.turn(15);
			}
			for (int j=11; j>=0; j--) {
				t.setColor(Color.rgb(128+10*j,64+5*j,64+5*j));
				t.move(20);
				t.turn(15);
			}
		}
	}
	public static void main(String args[]) {
		launch(args);
	}
}

