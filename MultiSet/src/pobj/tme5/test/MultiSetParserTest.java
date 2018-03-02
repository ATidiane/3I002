package pobj.tme5.test;

import java.util.List;

import pobj.tme5.InvalidMultiSetFormat;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetParser;

public class MultiSetParserTest {
	
	public static void main(String[] args) {
		System.out.println("Affichage de la liste d'éléments après parsing du fichier TestParse.txt : ");
		try {
			MultiSet<String> parse = MultiSetParser.parse("data/TestParse.txt");
			List<String> sorted = parse.elements();
	
			for (int i=0; i< parse.elements().size(); i++) {
				String e = sorted.get(i);
				System.out.println(e + " : " + parse.count(e));
			}
		} catch(InvalidMultiSetFormat i) {
			i.getMessage();
		}
	}
}
