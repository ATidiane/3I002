package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.Selection;

public class CommandUngroup implements Command {

	private Board board;
	private Canvas canvas;
	private Selection selected;
	
	public CommandUngroup(Board b,Canvas c,Selection s) {
		board = b;
		canvas = c;
		selected =s;
	}
	
	@Override
	public void execute() {
		if (!board.getContents().isEmpty()) {
			
			System.out.println("Board Elements"+board.getContents().size());
			
			//Séparer le group Add to the board
			List<Clip> ungroupClips = new ArrayList<Clip>();
			List<Clip> grouptoremove = new ArrayList<Clip>();
			for ( Clip c : board.getContents()){
				if ( c instanceof ClipGroup) {
					System.out.println("HAh");
					ClipGroup g = (ClipGroup) c;
					grouptoremove.add(g);
					for(Clip cl : g.getClips()) {
						ungroupClips.add(cl);
					}
				}
			}
			board.remove(grouptoremove);
			board.addClip(ungroupClips);
			
			board.draw(canvas.getGraphicsContext2D());
			
			System.out.println("Board elements after ungroup"+board.getContents().size());
			//Draw it 
			//group.draw(canvas.getGraphicsContext2D());
		}
		
	}

	@Override
	public void undo() {
		CommandGroup cg = new CommandGroup(board,canvas,selected);
		cg.execute();
		
	}

}
