package pobj.pinboard.editor.tools;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolImage implements Tool {

	private Clip img;
	private File filename;
	
	public ToolImage(File filename) {
		this.filename = filename;
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		if (i.getBoard().getContents().isEmpty()) {
			img = new ClipImage(e.getX(), e.getY(), filename);
		}
		
		if (!img.isSelected(e.getX(), e.getY())) {
			img = new ClipImage(e.getX(), e.getY(), filename);
		}
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		img.setGeometry(e.getX(), e.getY(), img.getRight(), img.getBottom());
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		//if (i.getBoard().getContents().contains(img)) {
		CommandAdd cAdd = new CommandAdd(i,img);
		cAdd.execute();
		//i.getBoard().addClip(img);
		//}
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
	}

	@Override
	public String getName() {
		return "Image";
	}
	
}
