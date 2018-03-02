package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class ClipGroup extends AbstractClip implements Composite  {

	private List<Clip> clips;
	private double top,left,bottom,right;
	private Color color;
	
	private List<Double> L = new ArrayList<Double>();
	private List<Double> T = new ArrayList<Double>();
	private List<Double> R = new ArrayList<Double>();
	private List<Double> B = new ArrayList<Double>();
	
	
	public ClipGroup(Color color) {
		super(0,0,0,0, color);
		clips = new ArrayList<Clip>();
	}

	
	public void draw(GraphicsContext ctx) {
		ctx.strokeRect(getLeft(), getTop(), getWidth(), getHeigth());
		for (Clip c : clips){
			c.draw(ctx);
			System.out.println("Je draw group");
		}
	}

	public void setGeometry(double left, double top, double right, double bottom) {
		move(right-left,bottom-top);
	}

	public void move(double x, double y) {
		calculCoord();
		for (Clip c : clips) {
			c.move(x, y);
		}
	}

	@Override
	public List<Clip> getClips() {
		return clips;
	}

	@Override
	public void addClip(Clip toAdd) {
		clips.add(toAdd);
		L.add(toAdd.getLeft());
		R.add(toAdd.getRight());
		T.add(toAdd.getTop());
		B.add(toAdd.getBottom());
		calculCoord();
	}
	
	@Override
	public void removeClip(Clip toRemove) {
		clips.remove(toRemove);
		L.remove(toRemove.getLeft());
		R.remove(toRemove.getRight());
		T.remove(toRemove.getTop());
		B.remove(toRemove.getBottom());
		calculCoord();
	}

	@Override
	public Clip copy() {
		ClipGroup copy = new ClipGroup(this.color);
		for ( Clip c : clips){
			copy.addClip(c.copy());
		}
		return copy;
	}
	
	public void calculCoord() {
		left = Collections.min(L);
		top = Collections.min(T);
		right = Collections.max(R);
		bottom = Collections.max(B);
		
	}

}
