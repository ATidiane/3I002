package pobj.partiel2012nov;


public class VisiteurEval implements IVisiteur<Integer> {

	@Override
	public Integer visite(Constante c) {
		return c.getValue();
	}

	@Override
	public Integer visite(Add e) {
		Integer s1 = e.getFg().accepte(this);
        Integer s2 = e.getFd().accepte(this);
        return s1 + s2;
	}

	@Override
	public Integer visite(Mul e) {
		Integer s1 = e.getFg().accepte(this);
        Integer s2 = e.getFd().accepte(this);
        return s1 * s2;
	}

	@Override
	public Integer visite(Var v) {
		throw new UnsupportedOperationException();
	}
	
}
