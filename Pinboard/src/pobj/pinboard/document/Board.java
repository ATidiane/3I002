package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	
	private List<Clip> board;
	
	public Board() {
		board = new ArrayList<Clip>();
	}
	
	public List<Clip> getContents() {
		return board;
	}
	
	public void addClip(Clip clip) {
		board.add(clip);
	}
	
	public void addClip(List<Clip> clip) {
		board.addAll(clip);
	}
	
	public void remove(Clip clip) {
		board.remove(clip);
	}
	
	public void remove(List<Clip> clip) {
		board.removeAll(clip);
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		
		for (Clip c: board) {
			c.draw(gc);
		}
	}
	
}