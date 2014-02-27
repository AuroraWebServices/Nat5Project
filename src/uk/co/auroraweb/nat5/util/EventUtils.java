package uk.co.auroraweb.nat5.util;

import java.util.List;

public class EventUtils {
	
	public static int getEventsAttended(List<String> events) {
		int eventsAttended = 0;
		
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i) == "YES") {
				
				
				eventsAttended++;
				
			}
		}
		
		return eventsAttended;
	}
	
}
