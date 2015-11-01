package main.java.com.proj.core.land;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class LandFile {
	
	public int rows;
	public int cols;
	public ArrayList<Character> allChars = new ArrayList<>(); 
	
	public LandFile(String filename) throws Exception {
		readLandDimension(filename);
	}
	
	public void readLandDimension(String filename) throws Exception {
		InputStream fis = new FileInputStream(filename); 
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
	    BufferedReader br = new BufferedReader(isr);
	    int rowsFile = 0;
	    int columnsFile = 0;
	    String line;
	    if ( (line = br.readLine()) != null ) {
	    	addEachChar(line);
	    	columnsFile = line.length();
	    	rowsFile++;
	    }
	    while ((line = br.readLine()) != null) {
	    	addEachChar(line);
	    	if(columnsFile != line.length()) {
	    		throw new Exception("All lines must have the same length");
	    	}
	    	rowsFile++;
	    }
	    rows = rowsFile;
	    cols = columnsFile;
	    br.close();
	}
	public void addEachChar(String line){
		for(int i=0; i<line.length(); i++){
    		allChars.add(line.charAt(i));
    	}
	}
}
