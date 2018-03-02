package pobj.partiel2012nov;

public class VisiteurEval implements IVisiteur<Integer>{

	@Override
	public Integer visite(Constante c) {
		return c.eval();
	}

	@Override
	public Integer visite(Add e) {
		return e.eval();
	}

	@Override
	public Integer visite(Mul e) {
		return e.eval();
	}

	@Override
	public Integer visite(Var v) {
		throw new UnsupportedOperationException();
	}

}
