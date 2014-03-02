package uk.co.auroraweb.nat5.util;

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
	 * Calculates and returns the number of attended events depending
	 * on the events parameter provided.
	 *  
	 * @param 	events	The input file to be read
	 * @return			An integer with the number of attended events
	 * @see 			List
	 */
	@Deprecated
	public static int getEventsAttended(List<String> events) {
		int eventsAttended = 1;
		
		for (int i = 0; i < events.size(); i++) {
			if (attendedEvent(events.get(i))) {
				eventsAttended++;
			}
		}
				
		return eventsAttended;
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
	 * @param	events	An ArrayList containing 
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
	
}
