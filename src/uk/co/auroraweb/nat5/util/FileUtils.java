package uk.co.auroraweb.nat5.util;

import java.io.File;

public class FileUtils {

	/**
	 * Checks if a file extension is equal to the String parameter.
	 * 
	 * @param file the location of the file to check
	 * @param ext the extension of the file
	 * @return true if the file extension if equal to the 'ext' parameter
	 */
	public static boolean verifyFileFormat(String file, String ext) {
		
		File f = new File(file);
		
		String fileExtension = "";
		
		int i = file.lastIndexOf(".");
		if (i > 0) {
			fileExtension = file.substring(i+1);
		}
		
		if (f.isFile() && fileExtension.equalsIgnoreCase(ext)) {
			return true;
		} else {
			return false;
		}
	}
	
}
