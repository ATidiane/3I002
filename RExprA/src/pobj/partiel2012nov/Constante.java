package pobj.partiel2012nov;

public class Constante implements Expression {
	private int cons;
	
	public Constante() {
		cons = 0;
	}
	public Constante(int cons) {
		this.cons = cons;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Constante other = (Constante) obj;
		if (cons != other.cons)
			return false;
		return true;
	}
	
	public int getValue() {
		return cons;
	}
	
	public int eval() {
		return cons;
	}
	
	public <T> T accepte(IVisiteur<T> v) {
		return v.visite(this);
	}
}
