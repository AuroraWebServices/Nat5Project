package uk.co.auroraweb.nat5.util;

import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class QRUtils {
	
	/**
	 * Returns a 150x150px QR code in HTML
	 * @param data the string to be encoded
	 * @return HTML formated QR code.
	 */
	public static String generateMarkupQRCode(String data) {
		return generateMarkupQRCode(data, new Dimension(150, 150));
	}
	
	/**
	 * Returns a QR code in HTML
	 * @param data the string to be encoded
	 * @param size the dimension of the QR code
	 * @return HTML formated QR code
	 */
	public static String generateMarkupQRCode(String data, Dimension size) {
		return "<HTML><IMG src=\"http://api.auroraweb.co.uk/?size=" + size.width + "x" + size.height + "&data=" + data + "\"></HTML>";
	}
	
	/**
	 * Saves a QR code to a PNG
	 * @param data the string to be encoded
	 * @param size the dimension of the QR code
	 * @param file the output file
	 * @throws IOException
	 */
	public static void saveQR(String data, Dimension size, File file) throws IOException {
		//Connection URL
		URL url = new URL("http://api.auroraweb.co.uk/?size=" + size.width + "x" + size.height + "&data=" + data);
		//Connection input strema
		InputStream input = url.openStream();
		//File output stream
		FileOutputStream output = new FileOutputStream(file);
		
		byte[] b = new byte[2048];
		int len;
		
		while ((len = input.read(b)) != -1) {
			//Write to output
			output.write(b, 0, len);
		}
		
		//Close input & output
		input.close();
		output.close();
	}
	
}
