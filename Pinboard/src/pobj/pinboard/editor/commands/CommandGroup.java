package pobj.pinboard.editor.commands;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.Selection;

public class CommandGroup implements Command{
	
	private Board board;
	private Canvas canvas;
	private Selection selected;
	private Color color = Color.BLACK;
	
	public CommandGroup(Board b,Canvas c, Selection s) {
		board=b;
		canvas=c;
		selected=s;
	}
 
	@Override
	public void execute() {
		if (!selected.getContents().isEmpty()) {
			//ôter les élements de la planche
			board.remove(selected.getContents());
			
			//Pour mettre la planche à blanche
			//canvas.getGraphicsContext2D().setFill(Color.WHITE);
			//canvas.getGraphicsContext2D().fillRect(0, 0,canvas.getGraphicsContext2D().getCanvas().getWidth(), canvas.getGraphicsContext2D().getCanvas().getHeight());
			//Créer le group
			ClipGroup group = new ClipGroup(color);
			for(Clip c : selected.getContents()) {
				group.addClip(c);
			}
			// Add to the board
			board.addClip(group);
			//Draw it 
			group.draw(canvas.getGraphicsContext2D());

		}
		
	}

	@Override
	public void undo() {
		CommandUngroup cug = new CommandUngroup(board,canvas,selected);
		cug.execute();
		System.out.println("Ungrouper");
	}

}
