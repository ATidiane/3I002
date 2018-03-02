package pobj.partiel2012nov;

public class Constante implements Expression {
	
	private int val;
	
	public Constante() {
		val = 0;
	}
	
	public int getValue() {
		return val;
	}

	public Constante (int v) {
		val = v;
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
		if (val != other.val)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+val ;
	}

	@Override
	public int eval() {
		return val;
	}
	
	@Override
	public <T> T accepte(IVisiteur<T> v) {
		return v.visite(this);
	}
	
	

}
