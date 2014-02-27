package uk.co.auroraweb.nat5.util;

import java.io.File;

public class FileUtils {

	public static boolean verifyFileFormat(String file, String format) {
		
		File f = new File(file);
		
		String fileExtension = "";
		
		int i = file.lastIndexOf(".");
		if (i > 0) {
			fileExtension = file.substring(i+1);
		}
		
		System.out.println(fileExtension);
		
		if (f.isFile() && fileExtension.equalsIgnoreCase(format)) {
			return true;
		} else {
			return false;
		}
	}
	
}
