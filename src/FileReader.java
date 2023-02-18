import java.io.*;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;   // Import the FileWriter class
import java.util.LinkedList;

public class FileReader {	
	public void read(String inputText) throws IOException {
		System.out.println("Starting ");
		File file = new File(inputText);
		Scanner input;   	
			
			input = new Scanner(file);
			LinkedListBasedSet list = new LinkedListBasedSet();		
			
				list.init();
			while(input.hasNext()) {
				String str  = input.nextLine();				
				
				str = str.replaceAll("[^a-zA-Z0-9]", " ");  //diðer karakterleri sil
				str=str.replaceAll("\\d", ""); //sayýlarý sil			
				str.toLowerCase(Locale.US);//küçük harfe çevir
						
				String[] wordsInLine = str.split(" ");
				
				for(int i = 0;i<wordsInLine.length;i++)
				{
					String tmp = wordsInLine[i];
					if(tmp.length()>0)
						list.add(tmp);	
					}								
				}									
			list.finalize();
			input.close();
			BufferedWriter f = null;
	        try {
	            f = new BufferedWriter(new FileWriter("named.out.txt"));
	            f.write(list.toString());
	        }
	        catch(IOException e) {
	            System.out.println(e);
	        }
	        finally {
	            if (f != null)
	                f.close();  
	        }	
		}
}
	
