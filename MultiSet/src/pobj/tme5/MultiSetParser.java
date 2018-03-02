package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MultiSetParser {
	
	public static MultiSet<String> parse(String fileName) throws InvalidMultiSetFormat {
		MultiSet<String> multset = new HashMultiSet<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
			
			for (String line = br.readLine(); line!= null; line = br.readLine()) {
				String [] parts = line.split(":");
				multset.add(parts[0], Integer.decode(parts[1].trim()));
				
			}
			br.close();
			
		}catch(ArrayIndexOutOfBoundsException i){
			
			throw new InvalidMultiSetFormat("la ligne ne contient pas de \":\"",
					i.getCause());
			
		}catch(NumberFormatException n) {
			
			throw new InvalidMultiSetFormat("la chaine ne represente pas un entier",
					n.getCause());
			
		} catch (IOException e) {
			
			throw new InvalidMultiSetFormat("Erreur d'I/0", e.getCause());
			
		}
		
		return multset;
		
	}
}
