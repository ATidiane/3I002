package pobj.partiel2012nov;

import java.util.Map;


public class VisiteurEvalVar extends VisiteurEval implements IVisiteur<Integer>{
	private Map<String, Integer> mp;
	
	public VisiteurEvalVar(Map<String, Integer> mp) {
		super();
		this.mp = mp;
	}
	
	@Override
	public Integer visite(Var v) {
		System.out.println(mp.get(v.getNom()));
		return mp.get(v.getNom());
	}
}
