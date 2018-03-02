package pobj.partiel2012nov;

public interface Expression {
	
	public <T> T accepte(IVisiteur<T> v);
	int eval();
}
