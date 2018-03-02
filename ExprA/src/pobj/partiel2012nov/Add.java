package pobj.partiel2012nov;

public class Add extends BinOp implements Expression {
	
	public Add(Expression fg,Expression fd){
		super(fg,fd);
	}

	@Override
	public String toString() {
		return "( "+getFg()+" + "+getFd()+" )";
	}

	@Override
	public int eval() {
		int res = getFg().eval() + getFd().eval();
		return res;
	}

	@Override
	public <T> T accepte(IVisiteur<T> v) {
		return v.visite(this);
	}
	
}
