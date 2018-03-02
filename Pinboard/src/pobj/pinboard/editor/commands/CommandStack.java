package pobj.pinboard.editor.commands;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandStack {

	private Deque<Command> undo = new ArrayDeque<Command>();
	private Deque<Command> redo = new ArrayDeque<Command>();

	public void addCommand(Command cmd){
		undo.push(cmd);
		redo.clear();
	}
	
	public void undo() {
		Command last = undo.pop();
		System.out.println("Undo" + undo.size());
		System.out.println("Redo" +redo.size());
		last.undo();
		redo.push(last);
		if(!redo.isEmpty()){
			System.out.println("Redo");
			System.out.println(redo.size());
		}
	}

	public void redo() {
		Command last = redo.pop();
		last.execute();
		undo.push(last);
	}
	
	public boolean isUndoEmpty() {
		return undo.isEmpty();
	
	}
	
	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}
}