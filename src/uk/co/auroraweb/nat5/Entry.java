package uk.co.auroraweb.nat5;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.co.auroraweb.nat5.util.DateUtils;

public class Entry {
	
	private String uID;
	private String fName;
	private String sName;
	private String address;
	private Date dOB;
	private String email;
	private List<String> events = new ArrayList<String>();
	
	/**
	 * Gets the unique ID
	 * @return the unique ID
	 */
	public String getUniqueID() {
		return uID;
	}
	
	/**
	 * Gets the entries full name
	 * @return the full name
	 */
	public String getFullName() {
		if (fName.charAt(fName.length() - 1) != " ".charAt(0)) {return fName + " " + sName;} else {return fName + sName;}
	}
	
	/**
	 * Gets the first name
	 * @return the forename
	 */
	public String getFirstName() {
		return fName;
	}
	
	/**
	 * Gets the surname
	 * @return the surname
	 */
	public String getLastName() {
		return sName;
	}
	
	/**
	 * Gets the entries address
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Gets the entries date of birth
	 * @return the date of birth
	 */
	public Date getDOB() {
		return dOB;
	}
	
	/**
	 * Gets the entries email
	 * @return the email address
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Gets the events attended
	 * @return an array list of the entries attended
	 */
	public List<String> getAttendedEvents() {
		return events;
	}
	
	/**
	 * Gets the number of events attended
	 * @return an integer with the number of events attended
	 */
	public int getNoEventsAttended() {
		return events.size();
	}
	
	/**
	 * Gets an fan's age
	 * @return the age
	 */
	public int getAge() {
		return DateUtils.getYearsAgo(dOB);
	}
	
	/**
	 * Returns the fan's discount
	 * @param perEvent the discount per event attended
	 * @param loyalThreshold the threshold of being considered as loyal
	 * @return the fan's discount
	 */
	public int getDiscount(int perEvent, int loyalThreshold) {
		if (getNoEventsAttended() < loyalThreshold) {
			return 0;
		} else {
			return getNoEventsAttended() * perEvent;
		}
	}
	
	/**
	 * Returns true if the entry has attended more events than the 
	 * threshold parameter
	 * @param threshold the minimum number of events to attend to be considered as loyal
	 * @return true if the entry has attended more events than the set threshold
	 */
	public boolean isLoyal(int threshold) {
		return events.size() >= threshold;
	}
	
	/**
	 * Sets the unique ID
	 * @param uID the unique ID
	 */
	public void setUniqueID(String uID) {
		this.uID = uID;
	}
	
	/**
	 * Sets the first name
	 * @param fName the forename
	 */
	public void setFirstName(String fName) {
		this.fName = fName;
	}
	
	/**
	 * Sets the surname
	 * @param sName the surname
	 */
	public void setLastName(String sName) {
		this.sName = sName;
	}
	
	/**
	 * Sets the address
	 * @param address the address
	 * @param town the town
	 */
	public void setAddress(String address, String town) {
		this.address = address + ", " + town;
	}
	
	/**
	 * Sets the date of birth
	 * @param dOB the date of birth
	 */
	public void setDOB(Date dOB) {
		this.dOB = dOB;
	}
	
	/**
	 * Sets the email
	 * @param email the email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Adds to the attended event list
	 * @param event the event to be added
	 */
	public void addAttendedEvent(String event) {
		events.add(event);
	}
	
}