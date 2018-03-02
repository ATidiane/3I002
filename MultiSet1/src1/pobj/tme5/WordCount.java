package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import pobj.tme5.MultiSet;
import pobj.util.Chrono;


public class WordCount {

	public static void main(String args[]) {
		
		HashMultiSet<String> hms = new HashMultiSet<String>();
		System.out.println("Trace d'execution de wordcount sur HashMultiSet : ");
		Chrono chrono = new Chrono(); 
		MultiSet<String> dec = new MultiSetDecorator<String>(hms);
		wordcount(dec);
		
		//System.out.println(dec.toString());
		chrono.stop();
		MultiSet<String> unchecked = new HashMultiSet<>();
		MultiSet<String> base = new HashMultiSet<String>();
		MultiSet<String> checked = new MultiSetDecorator<String>(base);
		unchecked.add("x");
		checked.add("y");
		System.out.println(unchecked.toString());
		System.out.println(checked.toString());
		//System.out.println(hms.toString());
		/*System.out.println("\nTrace d'execution de wordcount sur NaiveMultiSet : ");
		NaiveMultiSet<String> nms = new NaiveMultiSet<String>();
		Chrono chrono2 = new Chrono();
		wordcount(nms);
		chrono2.stop();*/
		
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
		/*	Comparator<String> c = new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return Integer.compare(ms.count(o2), ms.count(o1));
				}
			};*/
			Comparator<String> c = new MultiSetComparator<String>(ms);
			
			List<String> sorted = ms.elements();
			sorted.sort(c);
			for (int i=0; i<10; i++) {
				System.out.println(sorted.get(i));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
