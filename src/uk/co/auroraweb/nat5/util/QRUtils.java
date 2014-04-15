//Utils for QR codes (Encoding)
//TODO: Testing; comments

package uk.co.auroraweb.nat5.util;

import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class QRUtils {
	
	public static String generateMarkupQRCode(String data) {
		return generateMarkupQRCode(data, new Dimension(150, 150));
	}
	
	public static String generateMarkupQRCode(String data, Dimension size) {
		return "<HTML><IMG src=\"http://api.auroraweb.co.uk/?size=" + size.width + "x" + size.height + "&data=" + data + "\"></HTML>";
	}
	
	public static void saveQR(String data, Dimension size, File file) throws IOException {
		URL url = new URL("http://api.auroraweb.co.uk/?size=" + size.width + "x" + size.height + "&data=" + data);
		InputStream input = url.openStream();
		FileOutputStream output = new FileOutputStream(file);
		
		byte[] b = new byte[2048];
		int len;
		
		while ((len = input.read(b)) != -1) {
			output.write(b, 0, len);
		}
		
		input.close();
		output.close();
	}
	
}
