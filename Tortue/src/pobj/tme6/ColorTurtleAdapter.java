package pobj.tme6;

import javafx.scene.paint.Color;

public class ColorTurtleAdapter implements IColorTurtle {
	
	private ITurtle turtle;
	private Color color;
	
	public ColorTurtleAdapter(ITurtle turtle) {
		this.turtle = turtle;
		this.color = color.BLACK;
	}
	
	public void move(int length) {
		turtle.move(length);
	}
	
	public void turn(int angle) {
		turtle.turn(angle);
	}
	
	public void up() {
		turtle.up();
	}
	
	public void down() {
		turtle.down();
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

}
