package pobj.tme6;

import javafx.scene.paint.Color;

public class ColorTurtle extends Turtle implements IColorTurtle {
	
	private Color color;
	
	public ColorTurtle() {
		color = color.BLACK;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	void draw(int x1, int y1, int x2, int y2) {
		System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " " + color.toString());
	}
	
	public Color getColor() {
		return color;
	}
}
