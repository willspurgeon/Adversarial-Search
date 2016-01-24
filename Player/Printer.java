package Player;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Printer {
	
	public static void printToDebugFile(String input){
		PrintWriter writer;
		try {
			writer = new PrintWriter("debug.txt", "UTF-8");
			writer.println(input);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
