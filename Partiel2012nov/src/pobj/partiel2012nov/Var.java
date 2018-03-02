package pobj.partiel2012nov;

public class Var implements Expression {
	private final String nom;

	public Var(String nom) {
		this.nom = nom;
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
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	public String getNom() {
		return nom;
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
