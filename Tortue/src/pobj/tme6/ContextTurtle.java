package pobj.tme6;

public class ContextTurtle extends ColorTurtle implements IColorTurtle {
	
	private IContext context;
	
	public ContextTurtle(IContext context) {
		this.context = context;
	}
	
	void draw(int x1, int y1, int x2, int y2) {
		context.drawLine(x1, y1, x2, y2, getColor());
	}
}
