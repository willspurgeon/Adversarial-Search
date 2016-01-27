package Player;

//Will Spurgeon and Daniel Pongratz

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Printer {
	
	public static void printToDebugFile(String input){
		try{
    		String data = " This content will append to the end of the file";
    		
    		File file =new File("debug.txt");
    		
    		//if file doesn't exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
    		
    		FileWriter fileWritter = new FileWriter(file.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(input + "\n");
    	        bufferWritter.close();
	        
    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}
}
