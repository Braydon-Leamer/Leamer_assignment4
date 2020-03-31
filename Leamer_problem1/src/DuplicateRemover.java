import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class DuplicateRemover {
	private Set<String> uniqueWords;
	public void remove(String dataFile) {
		try {	
			String word;
			uniqueWords = new HashSet<String>();
			Scanner s = new Scanner(new File(dataFile));
			while(s.hasNext()) {
				word = s.next();
				uniqueWords.add(word);
			}
			s.close();
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("File " + dataFile + "cannot be found.");
			System.exit(1);
		}
	}
	
	public void write(String outputFile) {
		File f;
		FileWriter writer = null;
		f = new File(outputFile);
		try {
			if(f.exists()){
			writer = new FileWriter(f , false);
			Iterator<String> iterate = uniqueWords.iterator();
			while(iterate.hasNext()) {
				String str = (String) iterate.next();
				writer.write(str+"\n");
			}
			writer.close();
			System.out.println("Data Written");
		}
			
		else {
			f.createNewFile();
			writer = new FileWriter(f);
			Iterator<String> iterate = uniqueWords.iterator();
			while(iterate.hasNext()) {
				String str = (String) iterate.next();
				writer.write(str+"\n");
			}
			writer.close();
			System.out.println("Data Written");
		}
	}
		catch(IOException ex) {
			System.out.println("Error in writing to" + outputFile);
			System.exit(1);
		}
	}
}

