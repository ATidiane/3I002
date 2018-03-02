package pobj.tme6;

public class CommandUp implements ICommand {

	@Override
	public void execute(IColorTurtle turtle) {
		turtle.up();
	}
	
	
}
