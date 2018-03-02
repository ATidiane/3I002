package pobj.tme6;


import javafx.scene.paint.Color;

public class SaveTurtle implements IColorTurtle {

	private CommandList commandlist;
	
	public SaveTurtle () {
		commandlist = new CommandList();
	}
	public CommandList getCommand() {
		return commandlist;
	}
	@Override
	public void move(int length) {
		addCommand(new CommandMove(length));
	}

	@Override
	public void turn(int angle) {
		addCommand(new CommandTurn(angle));
	}

	@Override
	public void up() {
		addCommand(new CommandUp());

	}

	@Override
	public void down() {
		addCommand(new CommandDown());
	}

	@Override
	public void setColor(Color color) {
		addCommand(new CommandSetColor(color));
	}
	
	public void addCommand(ICommand c) {
		commandlist.addCommand(c);
	}
	
}
