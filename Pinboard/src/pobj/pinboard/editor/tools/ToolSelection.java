package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class ToolSelection implements Tool {
	
	private double clickX, clickY;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		
		if (e.isShiftDown()) {
			i.getSelection().toogleSelect(i.getBoard(), e.getX(), e.getY());
		} else {
			i.getSelection().select(i.getBoard(), e.getX(), e.getY());
		}
		
		if (i.getSelection().isSelected(e.getX(), e.getY())) {
			System.out.println("Ahmed");
			clickX = e.getX();
			clickY = e.getY();
		} else { 
			System.out.println("V");
			clickX = -1;
			clickY = -1;
		}
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		if ((clickX == -1) && (clickY == -1)) {
			return;
		}
		
		double x = e.getX() - clickX;
		double y = e.getY() - clickY;
		
		i.getSelection().move(x, y);
		System.out.println(i.getSelection().getContents().size());
		for(Clip c: i.getSelection().getContents()) {
			
			c.move(x, y);
		}
		
		clickX = e.getX();
		clickY = e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName() {
		return "Selecting...";
	}

}
