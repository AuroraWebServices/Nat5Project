package uk.co.auroraweb.nat5.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
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
	
	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		
		String out = dateFormat.format(date);
		
		return out;
	}
	
}
