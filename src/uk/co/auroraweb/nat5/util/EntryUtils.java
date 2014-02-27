package uk.co.auroraweb.nat5.util;

import java.util.Date;
import java.util.List;

import uk.co.auroraweb.nat5.Entry;

public class EntryUtils {
	
	/**
	 * Calculates and returns the number of attended events depending
	 * on the events param provided.
	 *  
	 * @param 	events	The input file to be read
	 * @return			An integer with the number of attended events
	 */
	public static int getEventsAttended(List<String> events) {
		int eventsAttended = 0;
		
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i) != "") {
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
	 */
	public static Entry createEntry(String uID, String fName, String sName, String address, String town, Date dOB, String email, List<String> events) {
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
	
}