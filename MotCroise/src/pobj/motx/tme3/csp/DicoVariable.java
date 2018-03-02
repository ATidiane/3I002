package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable {
	
	private int index;
	private GrillePotentiel gp;
	
	public DicoVariable(int index, GrillePotentiel gp) {
		this.index = index;
		this.gp = gp;
	}
	
	public String toString() {
		//System.out.println("Pour le mot : "+gp.getGrille().getMots().get(index).toString());
		//System.out.println("La liste de mots possible est : ");
		//for (String mot: gp.getMotsPot().get(index).getMots()) {
		//	System.out.print(mot+" ");
		//}
		//System.out.println();
		return "";
	}
	
	public List<String> getDomain() {
		return gp.getMotsPot().get(index).getMots();
	}
	
	public int getIndex() {
		return index;
	}

}
