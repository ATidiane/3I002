package pobj.tme6;

import javafx.scene.paint.Color;

public class PrintContext implements IContext {
	
	public void drawLine(int x1, int y1, int x2, int y2, Color color) {
		System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " " + color.toString());
	}
	
}
