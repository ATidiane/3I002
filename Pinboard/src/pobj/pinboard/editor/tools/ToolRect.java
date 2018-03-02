package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolRect implements Tool {

	private Clip rect;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		rect = new ClipRect(e.getX(), e.getY(), e.getX(), e.getY(), i.getColor());
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		rect.setGeometry(rect.getLeft(), rect.getTop(), e.getX(), e.getY());
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		CommandAdd cAdd = new CommandAdd(i,rect);
		cAdd.execute();
		//i.getBoard().addClip(rect);
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.strokeRect(rect.getLeft(), rect.getTop(), ((ClipRect)rect).getWidth(), ((ClipRect)rect).getHeigth());
	}

	@Override
	public String getName() {
		return "Rectangle";
	}

}
