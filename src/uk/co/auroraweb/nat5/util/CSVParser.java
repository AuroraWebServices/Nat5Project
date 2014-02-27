//Used to parse the CSV file

package uk.co.auroraweb.nat5.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import uk.co.auroraweb.nat5.Entry;


public class CSVParser {

	private static Scanner sc;

	public static List<Entry> parseCSV(String file){
		
		List<Entry> out = new ArrayList<Entry>();
	
		String delimiter = ",";
		Scanner sc = null;
		
		try {
			sc = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line;

		while (sc.hasNextLine()) 
		{
		    line = sc.nextLine();
		    String[] input = (line.split(delimiter));
		    
		    String uID = input[0];
		    String fName = input[1];
		    String sName = input[2];
		    String address = input[3];
		    String town = input[4];
		    Date dOB = Utils.parseDate(input[5]);   
		    String email = input[6];
		    
		    List<String> events = new ArrayList<String>();
		    
		    for (int i = 8; i < input.length - 1; i++) {
		    	events.add(input[i]);
		    }
		    
		    Entry entry = createEntry(uID, fName, sName, address, town, dOB, email, events);
		    out.add(entry);
		}
		
		sc.close();
		
		return out;
		
	}
	
	private static Entry createEntry(String uID, String fName, String sName, String address, String town, Date dOB, String email, List<String> events) {
		Entry entry = new Entry();
		entry.setUniqueID(uID);
		entry.setFirstName(fName);
		entry.setLastName(sName);
		entry.setAddress(address, town);
		entry.setEmail(email);
		entry.setDOB(dOB);
		
		for (int i = 0; i < events.size() - 1; i++){
			entry.addAttendedEvent(events.get(i));
		}
		
		
		
		return entry;
	}
	
	public static String[] getHeader(String file) {
		String delimiter = ",";
		sc = new Scanner(file);
		
		String line = sc.nextLine();
		String[] columns = line.split(delimiter);
		
		return columns;
	}
	
	
	
}
