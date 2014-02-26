//Used to import the table
//TODO: *

package uk.co.auroraweb.nat5.utils;

public class TableImporter {
	
	//Formats CSV Input to JTable data

	public TableImporter() {
		
	}
	
	public String[] columns(){
		
		//TESTING DATA
		String[] columns = {"First", "Second", "Third"};
		
		return columns;
		
	}
	
	public String[][] data(){
		
		//TESTING DATA
		String[][] data = {{"1A", "1B", "1C"}, {"2A", "2B", "2C"}, {"1A", "1B", "1C"}, {"1A", "1B", "1C"}, {"1A", "1B", "1C"}};
		
		return data;
	}
	
}