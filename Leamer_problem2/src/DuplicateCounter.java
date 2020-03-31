import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class DuplicateCounter {
	private Integer wordCounter;
	private Map<String, Integer> map;
	
	public DuplicateCounter() {
		this.wordCounter = 0;
		this.map = new HashMap<>();
	}
	
	public void count(String dataFile) {
		Scanner s;
		try {
			s = new Scanner(new File(dataFile));
			while(s.hasNextLine()) {
				String line = s.nextLine().trim();
				String[] data = line.split("[\n]+");
				for(String word : data) {
					this.wordCounter = map.get(word);
					this.wordCounter = (this.wordCounter == null) ? 1 : ++this.wordCounter;
					map.put(word,  this.wordCounter);
				}
			}
			s.close();
	}
		catch(FileNotFoundException fnfe) {
			System.out.println("File " + dataFile + " cannot be found.");
			System.exit(1);
		}
	}
	
	public void write(String outputFile) {
		File f;
		FileWriter writer;
		PrintWriter pWriter;
		f = new File(outputFile);
		try {
			if(f.exists()) {
				writer = new FileWriter(f, false);
				pWriter = new PrintWriter(writer);
				for(Map.Entry<String, Integer> entry : map.entrySet()) {
					pWriter.write("The word " + entry.getKey() + " happens " + entry.getValue() + " times\n");
				}
				System.out.println("Data written to file");
				pWriter.flush();
				writer.close();
				pWriter.close();
			}
			
			else {
				f.createNewFile();
				writer = new FileWriter(new File(outputFile));
				pWriter = new PrintWriter(writer);
				for(Map.Entry<String, Integer> entry : map.entrySet()) {
					pWriter.write(entry.getKey() + "happens" + entry.getValue() + "times\n");
				}
				System.out.println("Data written to file");
				pWriter.flush();
				writer.close();
				pWriter.close();
			}
		}
		catch(IOException ex) {
			System.out.println("Error in writing to" + outputFile);
			System.exit(1);
		}
	}
}