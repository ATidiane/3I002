package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip {

	public ClipEllipse(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillOval(getLeft(), getTop(), getWidth(), getHeigth());
	}
	
	@Override
	public boolean isSelected(double x, double y) {
		
		double cx = (getLeft() + getRight())/2;
		double cy = (getTop() + getBottom())/2;
		double rx = (getRight() - getLeft());
		double ry = (getBottom() - getTop());
		double res = Math.pow(((x - cx)/rx), 2) + Math.pow(((y - cy)/ry), 2);
		
		if (res <= 1) {
			return true;
		}
		return false;
	}
	
	@Override
	public Clip copy() {
		return new ClipEllipse(getLeft(), getTop(), getRight(), getBottom(), getColor());
	}
	
	
}
