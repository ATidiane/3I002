package pobj.pinboard.editor.commands;

import javafx.scene.canvas.Canvas;
import pobj.pinboard.document.Board;
import pobj.pinboard.editor.Selection;

public class CommandDelete implements Command {
	
	private Board board;
	private Canvas canvas;
	private Selection selected;
	
	public CommandDelete(Board b,Canvas c,Selection s){
		board = b;
		canvas = c;
		selected = s ;
	}
	
	@Override
	public void execute() {
		board.remove(selected.getContents());
		board.draw(canvas.getGraphicsContext2D());
		
	}

	@Override
	public void undo() {
		board.addClip(selected.getContents());
		board.draw(canvas.getGraphicsContext2D());
	}

}
