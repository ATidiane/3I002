package pobj.tme6;

public class CommandDown implements ICommand {

	@Override
	public void execute(IColorTurtle turtle) {
		turtle.down();
	}

}
