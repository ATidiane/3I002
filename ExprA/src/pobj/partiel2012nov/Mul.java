package pobj.partiel2012nov;

public class Mul extends BinOp implements Expression {

	
	public Mul(Expression fg,Expression fd) {
		super(fg,fd);
	}

	@Override
	public String toString() {
		return getFg().toString() + " * "  + getFd().toString() ;
	}

	@Override
	public int eval() {
		return getFg().eval() * getFd().eval();
	}

	@Override
	public <T> T accepte(IVisiteur<T> v) {
		return v.visite(this);
	}
}
