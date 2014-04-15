//Used to parse CSV files

package uk.co.auroraweb.nat5.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import uk.co.auroraweb.nat5.Entry;


public class CSVParser {
	
	private static Scanner sc;
	
	
	/**
	 * Returns an ArrayList featuring all entries of the CSV.
	 * The file argument must in existence or a FileNotFoundException
	 * error will occur.
	 * 
	 * @param 	file	The input file to be read
	 * @return			An ArrayList containing all the entries of the CSV.
	 */
	public static List<Entry> parseCSV(String file, JFrame frame){
		
		List<Entry> out = new ArrayList<Entry>();
		boolean errorOccured = false;
		
		String delimiter = ",";
		
		try {
			sc = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String fLine = sc.nextLine();
		
		while (fLine == null) {
			fLine = sc.nextLine();
		}
		
		String[] fInput = (fLine.split(delimiter));
		String[] fEvents = Arrays.copyOfRange(fInput, 7, fInput.length);
	    
		String line;
		
		inputLoop:
		while (sc.hasNextLine()) 
		{
		    line = sc.nextLine();
		    
		    while (line == null || line.charAt(0) == ',') {
				line = sc.nextLine();
			}
		    
		    //The full input
		    String[] input = (line.split(delimiter));
		    //The required values
		    String[] req = Arrays.copyOfRange(input, 0, 6);
		    
		    //Test to see if any of the required values are empty
		    for (int i = 0; i < req.length - 1; i++) {
		    	if (req[i].isEmpty()) {
		    		//Provokes the error message
		    		AlertManager.alert(frame, AlertManager.ERROR_MSG, "An error has occured: A required input is blank.");
		    		//Sets errorOccured to true
		    		errorOccured = true;
		    		//Breaks from the import loop.
		    		break inputLoop;
		    	}
		    }
		    
		    String uID = input[0];
		    String fName = input[1];
		    String sName = input[2];
		    String address = input[3];
		    String town = input[4];
		    Date dOB = DateUtils.parseDate(input[5]);   
		    String email = input[6];
		    
		    String[] events = Arrays.copyOfRange(input,  7, input.length);
		    List<String> eventsAttended = new ArrayList<String>();
		    
		    //If events[i] was attended, then get the name of the event by referencing fEvents[i]
		    for (int i = 0; i < events.length; i++) {
		    	if (EntryUtils.attendedEvent(events[i])) {
		    		eventsAttended.add(fEvents[i]);
		    	}
		    }
		    
		    out.add(EntryUtils.createEntry(uID, fName, sName, address, town, dOB, email, eventsAttended));
		    
		}
		
		sc.close();
		
		if (errorOccured) {
			return null;
		} else {
			return out;
		}
		
		
	}
	
}
