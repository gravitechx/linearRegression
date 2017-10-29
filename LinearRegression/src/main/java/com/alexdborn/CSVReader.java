package com.alexdborn;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
	String file;
	String splitter;
	BufferedReader br = null;
	String line = "";
	
	
	CSVReader(String file, String splitter){
		this.file = file;
		this.splitter = splitter;
	}
	
	public Double[][] readToDoubleArray() {
		Double[][] points = new Double[2][];
		try {
			// Create new buffered reader
			br = new BufferedReader(new FileReader(file));
			// While we have a line
			ArrayList<Double> x = new ArrayList<Double>();
			ArrayList<Double> y = new ArrayList<Double>();
			
			while(line != null) {
				// Read that line as a string
				line = br.readLine();

				if(line != null) {
					x.add(Double.parseDouble(line.substring(0, line.indexOf(splitter))));
					y.add(Double.parseDouble(line.substring(line.indexOf(splitter) + 1, line.length())));
				}
				
				points[0] = x.toArray(new Double[x.size()]);
				points[1] = y.toArray(new Double[y.size()]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        }
		return points;
	}
	
}
