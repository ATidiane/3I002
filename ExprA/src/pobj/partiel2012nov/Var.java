package pobj.partiel2012nov;

public class Var implements Expression {
	
	private final String value;

	public Var(String value) {
		this.value = value;
	}

	public String getNom() {
		return value;
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
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+value ;
	}

	@Override
	public int eval() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public <T> T accepte(IVisiteur<T> v) {
		return v.visite(this);
	}
	
	

}
