package pobj.partiel2012nov;

public interface Expression {
	public int eval();
	public <T> T accepte(IVisiteur<T> v);
}
