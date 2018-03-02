package pobj.partiel2012nov;

public class Add extends BinOp implements Expression {

	public Add(Expression fg, Expression fd) {
		super(fg, fd);
	}

	@Override
	public int eval() {
		return getFg().eval() + getFd().eval();
	}

	@Override
	public <T> T accepte(IVisiteur<T> v) {
		return v.visite(this);
	}

}
