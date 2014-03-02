package uk.co.auroraweb.nat5.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	
	/**
	 * Returns a Date from a parsed String.
	 * 
	 * @param date a String to be parsed into a Date
	 * @return the parsed date
	 */
	public static Date parseDate(String date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		Date output = new Date();
		
		try {
			output = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	/**
	 * Returns a formatted date from a Date and a format as a 
	 * String.
	 * 
	 * @param date a Date to format
	 * @param format the requested format (example: "dd/MM/yy")
	 * @return a formatted Date as a String
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		
		String out = dateFormat.format(date);
		
		return out;
	}
	
	/**
	 * Returns the number of years between now and the Date target.
	 * 
	 * @param target the target date (How many years between then and now)
	 * @return the number of years between target and now
	 */
	public static int getYearsAgo(Date target) {
		
		Calendar targetCal = Calendar.getInstance();
		targetCal.setTime(target);
		
		Calendar now = Calendar.getInstance();
		
		int years = now.get(Calendar.YEAR) - targetCal.get(Calendar.YEAR);
		
		if (now.get(Calendar.DAY_OF_YEAR) <= targetCal.get(Calendar.DAY_OF_YEAR)) {
			years--;
		}
		
		return years;
	}
	
}
