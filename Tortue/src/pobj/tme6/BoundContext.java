package pobj.tme6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.paint.Color;

public class BoundContext implements IContext {
	
	private List<Integer> X = new ArrayList<>(); 
	private List<Integer> Y = new ArrayList<>();
	
	public int getMinX() {
		return Collections.min(X);
	}
	
	public int getMinY() {
		return Collections.min(Y);
	}
	
	public int getMaxX() {
		return Collections.max(X);
	}
	
	public int getMaxY() {
		return Collections.max(Y);
	}
	
	public void drawLine(int x1, int y1, int x2, int y2, Color color) {
		if (!(X.contains(x1))) {
			X.add(x1);
		}
		if (!(X.contains(x2))) {
			X.add(x2);
		}
		if (!(Y.contains(y1))) {
			Y.add(y1);
		}
		if (!(Y.contains(y2))) {
			Y.add(y2);
		}
	}
	
}
