package pobj.motx.tme3.csp.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrilleMots;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.MotX;


public class MotXTest2 {
	
	
	
	//@Test
	public void testLarge() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());

		System.out.println("Avant résolution de la grille: ");
		System.out.println(gr);

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX varsx = new MotX(gp);

		CSPSolver csp = new CSPSolver();
		MotX v = (MotX) csp.solve(varsx);
		
		System.out.println("Après résolution de la grille: ");
		System.out.println(v.getGp().getGrille().toString());
		
	}
	
	@Test
	public void testLarge3() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large3.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());

		System.out.println("Avant résolution de la grille: ");
		System.out.println(gr);

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX varsx = new MotX(gp);

		CSPSolver csp = new CSPSolver();
		MotX v = (MotX) csp.solve(varsx);
		
		System.out.println("Après résolution de la grille: ");
		System.out.println(v.getGp().getGrille().toString());
	}
	
	
}
