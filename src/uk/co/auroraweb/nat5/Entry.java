package uk.co.auroraweb.nat5;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entry {
	
	private String entry[];
	
	private String uID;
	private String fName;
	private String sName;
	private String address;
	private Date dOB;
	private String email;
	private List<String> events = new ArrayList<String>();
	
	public String[] getEntry() {
		return entry;
	}
	
	public String getUniqueID() {
		/**
		 * @return Unique ID
		 */
		return uID;
	}
	
	public String getFullName() {
		/**
		 * @return Full name (First name & Last name)
		 */
		return fName + sName;
	}
	
	public String getFirstName() {
		/**
		 * @return First name
		 */
		return fName;
	}
	
	public String getLastName() {
		/**
		 * @return Last name
		 */
		return sName;
	}
	
	public String getAddress() {
		/**
		 * @return The address
		 */
		return address;
	}
	
	public Date getDOB() {
		/**
		 * @return The date of birth
		 */
		return dOB;
	}
	
	public String getEmail() {
		/**
		 * @return The email address
		 */
		return email;
	}
	
	public List<String> getAttendedEvents() {
		/**
		 * @return the list of attended events
		 */
		return events;
	}
	
	public void setUniqueID(String uID) {
		this.uID = uID;
	}
	
	public void setFirstName(String fName) {
		this.fName = fName;
	}
	
	public void setLastName(String sName) {
		this.sName = sName;
	}
	
	public void setAddress(String address, String town) {
		this.address = address + ", " + town;
	}
	
	public void setDOB(Date dOB) {
		this.dOB = dOB;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addAttendedEvent(String event) {
		events.add(event);
	}
	
}