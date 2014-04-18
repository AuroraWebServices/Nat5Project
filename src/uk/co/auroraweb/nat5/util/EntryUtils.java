package uk.co.auroraweb.nat5.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.co.auroraweb.nat5.Entry;

public class EntryUtils {
	
	/**
	 * Returns true if the String event has attended an event
	 * 
	 * @param event the string to be tested
	 * @return true if the String event is equal to "Yes", "1" or "true"
	 */
	public static boolean attendedEvent(String event) {
		return event.equalsIgnoreCase("yes") || event.equals("1") || event.equalsIgnoreCase("true");
	}
	
	/**
	 * Creates an instance of an Entry with the parameters provided
	 * 
	 * @param 	uID		The unique ID
	 * @param	fName	The fan's forename
	 * @param	sName 	The fan's surname
	 * @param	address	The fan's address
	 * @param	town	The fan's town
	 * @param	dOB		The date of birth of the fan
	 * @param	email	The fan's email
	 * @param	events	An ArrayList containing the events attended
	 * @return			An ArrayList containing all the entries of the CSV.
	 * @see				List
	 */
	public static Entry createEntry(String uID, String fName, String sName, String address, String town, Date dOB, String email, List<String> events) {
		Entry entry = new Entry();
		entry.setUniqueID(uID);
		entry.setFirstName(fName);
		entry.setLastName(sName);
		entry.setAddress(address, town);
		entry.setEmail(email);
		entry.setDOB(dOB);
		
		for (int i = 0; i < events.size(); i++){
			entry.addAttendedEvent(events.get(i));
		}
		
		return entry;
	}
	
	/**
	 * Filters a full list of entries to a list only populated with loyal fans
	 * @param data the data to filter
	 * @param options the options to determine loyalty threshold
	 * @return a List<Entry> populated with royalty fans.
	 */
	public static List<Entry> getLoyalFans(List<Entry> data, int[] options) {
		List<Entry> loyalFans = new ArrayList<Entry>();
		
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).isLoyal(options[0])) {
				loyalFans.add(data.get(i));
			}
		}
		
		return loyalFans;
	}
	
	/**
	 * Gives the index of an array list of entries by the unique ID of the entry
	 * @param data the data to get the index of
	 * @param uID the unique ID
	 * @return the index of the array list.
	 */
	public static int getIndexFromUniqueID(List<Entry> data, String uID) {
		
		int returnVal = -1;
		
		testLoop:
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getUniqueID() == uID) {
				returnVal = i;
				
				break testLoop;
			}
		}
		
		return returnVal;
		
	}
	
}
