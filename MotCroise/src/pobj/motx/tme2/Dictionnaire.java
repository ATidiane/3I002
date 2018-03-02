package pobj.motx.tme2;

import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Un ensemble de mots.
 *
 */
public class Dictionnaire {

	// stockage des mots
	private List<String> mots = new ArrayList<>();
	
	
	/**
	 * Accède à la liste de mots du dictionnaire
	 * @return la liste de mots du dictionnaire
	 */
	public List<String> getMots() {
		return mots;
	}

	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}
	
	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}

	
	/**
	 * Removes words that do not have a the character c at index i
	 * @param c filter character 
	 * @param i index of the character wanted c
	 * @return the number of the removed words
	 */
	public int filtreParLettre(char c, int i) {
		List<String> cible = new ArrayList<>();
		int cpt = 0;
		for(String mot: mots) {
			if (mot.charAt(i) == c) {
				cible.add(mot);
			} else {
				cpt++;
			}
		}
		mots = cible;
		return cpt;
	}

	/**
	 * Removes words that does not have a character 
	 * at index i which is part of the set of letters l
	 * @param i wanted index
	 * @param l set of letters
	 * @return the number of the removed words
	 */
	public int filtreParIndexEL(int i, EnsembleLettre l) {
		List<String> cible = new ArrayList<>();
		int cpt = 0;
		for(String mot : mots) {
			if (l.contains(mot.charAt(i))) {
				cible.add(mot);
			} else {
				cpt++;
			}
		}
		mots = cible;
		return cpt;
	}
	
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}
	
	/**
	 * Loads a dictionary 
	 * @param path location of the dictionary
	 * @return a dictionary
	 */
	public static Dictionnaire loadDictionnaire(String path) {
		Dictionnaire dict = new Dictionnaire();
		// try-with-ressource: cette syntaxe permet d'accéder au contenu du fichier ligne par ligne
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			for (String line = br.readLine(); line!= null; line = br.readLine()) {
				dict.mots.add(line);
			}
		} catch (IOException e) {
			//Problème d'accès au fichier
			e.printStackTrace();
		}
		
		return dict;
	}
	
	/**
	 * Builds a list of characters that contains a character at index c of 
	 * each word in the dictionary
	 * @param c index of crossover character
	 * @return the list of characters
	 */
	public EnsembleLettre calculeEL(int c) {
		EnsembleLettre l = new EnsembleLettre();
		for(String mot : mots) {
			l.add(mot.charAt(c));
		}
		return l;
	}
	
	
	
	
	
}