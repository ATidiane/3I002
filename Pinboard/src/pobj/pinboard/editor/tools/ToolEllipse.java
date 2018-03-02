package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolEllipse implements Tool {

	private Clip ellipse;
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		ellipse = new ClipEllipse(e.getX(), e.getY(), e.getX(), e.getY(), i.getColor());
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		ellipse.setGeometry(ellipse.getLeft(), ellipse.getTop(), e.getX(), e.getY());
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		//i.getBoard().addClip(ellipse);
		CommandAdd cAdd = new CommandAdd(i,ellipse);
		cAdd.execute();
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.strokeOval(ellipse.getLeft(), ellipse.getTop(), ((ClipEllipse)ellipse).getWidth(), ((ClipEllipse)ellipse).getHeigth());
	}

	@Override
	public String getName() {
		return "Ellipse";
	}

}
