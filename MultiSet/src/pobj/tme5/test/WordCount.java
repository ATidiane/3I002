package pobj.tme5.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetDecorator;
import pobj.tme5.NaiveMultiSet;
import pobj.util.Chrono;


public class WordCount {

	public static void main(String args[]) {
		
		System.out.println("Trace d'execution de wordcount sur HashMultiSet avec les assertions : ");
		MultiSet<String> hms = new HashMultiSet<String>();
		MultiSet<String> dech = new MultiSetDecorator<String>(hms);
		Chrono chrono = new Chrono(); 
		wordcount(dech);
		chrono.stop();
		
		System.out.println("Trace d'execution de wordcount sur NaiveMultiSet avec les assertions : ");
		MultiSet<String> nms = new NaiveMultiSet<String>();
		MultiSet<String> decn = new MultiSetDecorator<>(nms);
		Chrono chronon = new Chrono();
		wordcount(decn);
		chronon.stop();
		
		
	}
		
	/**
	 * Counts the number of occurrences of each word in the collection
	 * and prints the ten (10) most common words. 
	 * @param ms a MultiSet (Collection)
	 */
	public static void wordcount(MultiSet<String> ms) {
		
		String file = "data/WarAndPeace.txt";
		BufferedReader br;
		try {
			String line;
			br = new BufferedReader(new FileReader(file));
			
			while((line = br.readLine()) != null) {
				for (String word : line.split("\\P{L}+")) {
					ms.add(word);
				}
			}
			br.close();
			
			class MultiSetComparator<T> implements Comparator<T> {
				
				MultiSet<T> ms;
				public MultiSetComparator(MultiSet<T> ms) {
					this.ms = ms;
				}
				public int compare(T o1, T o2) {
					return Integer.compare(ms.count(o2), ms.count(o1));
				}
			}
		
			Comparator<String> c = new MultiSetComparator<String>(ms);
			
			List<String> sorted = ms.elements();
			sorted.sort(c);
			for (int i=0; i<10; i++) {
				String e = sorted.get(i);
				System.out.println(e + " : " + ms.count(e));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
