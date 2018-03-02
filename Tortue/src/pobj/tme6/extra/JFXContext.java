package pobj.tme6.extra;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;

import pobj.tme6.IContext;

public class JFXContext implements IContext {

	private Canvas canvas;
	double minX, minY, maxX, maxY;
	
	public JFXContext(Stage stage, int minX, int minY, int maxX, int maxY) {
		stage.setTitle("Turtle");
		stage.setResizable(false);

		VBox vbox = new VBox();
		canvas = new Canvas(1024,768);
		vbox.getChildren().addAll(canvas);
		Scene scene = new Scene(vbox);
        stage.setScene(scene);
		stage.show();
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}	

	@Override
	public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(color);
		gc.strokeLine(calcX(x1), calcY(y1), calcX(x2), calcY(y2));
	}
	
	private int calcX(int x) {
		return (int)((x-minX)/(maxX-minX)*canvas.getWidth());
	}

	private int calcY(int y) {
		return (int)((y-minY)/(maxY-minY)*canvas.getHeight());
	}

}
