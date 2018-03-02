package pobj.partiel2012nov;

public class Var implements Expression {
	private final String var;

	public Var(String var) {
		this.var = var;
	}

	public String getNom() {
		return var;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Var other = (Var) obj;
		if (var == null) {
			if (other.var != null)
				return false;
		} else if (!var.equals(other.var))
			return false;
		return true;
	}
	
	public int eval() {
		throw new UnsupportedOperationException();
	}

	public <T> T accepte(IVisiteur<T> v) {
		return v.visite(this);
	}
	
}
