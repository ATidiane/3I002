package pobj.pinboard.editor.commands;



import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command{

	EditorInterface editor;
	Clip toAdd ;
	
	public CommandAdd(EditorInterface ew, Clip cliptoAdd) {
		editor = ew;
		toAdd = cliptoAdd;
	}
	@Override
	public void execute() {	
		editor.getBoard().addClip(toAdd);
	}

	@Override
	public void undo() {
		editor.getBoard().remove(toAdd);
	}

}
