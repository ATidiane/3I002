package pobj.tme6;

import java.util.ArrayList;
import java.util.List;

public class CommandList implements ICommand {
	
	private List<ICommand> commandlist;
	
	public CommandList() {
		commandlist = new ArrayList<>();
	}
	
	public void addCommand(ICommand command) {
		commandlist.add(command);
	}
	
	public void replaceCommand(int i,ICommand command) {
		commandlist.set(i, command);
	}
	
	public void execute(IColorTurtle turtle) {
		for (ICommand c : commandlist) {
			c.execute(turtle);
		}
	}
	
	public List<ICommand> getCommandList() {
		return commandlist;
	}
}
