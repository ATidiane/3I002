package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Case;
import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP {
	
	private List<IVariable> vars;
	private GrillePotentiel gp;

	public MotX(GrillePotentiel gp) {
		this.gp = gp;
		vars = new ArrayList<IVariable>();
		for (int i=0; i< gp.getGrille().getMots().size(); i++) {
			for (Case c: gp.getGrille().getMots().get(i).getLettres()) {
				if (c.isVide()) {
					vars.add(new DicoVariable(i, gp));
					break;
				}
			}
		}
	}
	
	public ICSP assign(IVariable vi, String val) {
		if (vi instanceof DicoVariable) {
			return new MotX(gp.fixer(((DicoVariable) vi).getIndex(), val));
		}
		return new MotX(gp);
	}
	
	public List<IVariable> getVars() {
		return vars;
	}

	@Override
	public boolean isConsistent() {
		if (gp.isDead()) {
			return false;
		}
		return true;
	}
	
	public GrillePotentiel getGp() {
		return gp;
	}
}
