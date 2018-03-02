package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractClip {

	private double left, top, right, bottom;
	private Color color;
	
	public AbstractClip(double left, double top, double right, double bottom, Color color) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.color = color;
	} 
	
	public abstract void draw(GraphicsContext ctx);
	
	public double getTop() {
		return top;
	}
	
	public double getLeft() {
		return left;
	}

	public double getBottom() {
		return bottom;
	}

	public double getRight() {
		return right;
	}

	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	public void move(double x, double y) {
		left += x;
		right += x;
		top += y;
		bottom += y;
	}

	public boolean isSelected(double x, double y) {
		if ((x >= left && x <= right) && (y >= top && y <= bottom)) {
			return true;
		}
		return false;
	}

	public void setColor(Color c) {
		color = c;
	}
	
	public double getWidth() {
		return right-left;
	}
	
	public double getHeigth() {
		return bottom-top;
	}

	public Color getColor() {
		return color;
	}

	public abstract Clip copy();
	
}
