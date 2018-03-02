package pobj.partiel2012nov;

public class Mul extends BinOp implements Expression {

	public Mul(Expression fg, Expression fd) {
		super(fg, fd);
	}
	
	public int eval() {
		return getFg().eval() * getFd().eval();
	}

	public <T> T accepte(IVisiteur<T> v) {
		return v.visite(this);
	}	
}
