package pobj.motx.tme2.test;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrilleMots;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;

public class GrillePotentielTest2 extends GrillePotentielTest {

	@Test
	public void testEasy2() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		// grille 6x5, mots sans croisement
		Grille gr = GrilleLoader.loadGrille("data/easy2.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());

		// System.out.println(gr);

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		assertTrue(!gp.isDead());

		int[] expected = { 245, 302, 1 };

		testNombrePot(gp, expected);

		System.out.println("Succès test GrillePotentiel : easy2.");
	}

}