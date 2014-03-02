//Utils for QR codes (Encoding)
//TODO: Testing; comments

package uk.co.auroraweb.nat5.util;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;

public class QRUtils {
	
	/**
	 * Returns a 150x150px encoded QR Code in the form of a BufferedImage. 
	 * The function will return null if an error is returned by the
	 * QR API or by an IOException (If the resource cannot be found).
	 *
	 * @param  data a string containing the data to be encoded
	 * @return      the image provided by the QR Code API
	 * @see         BufferedImage
	 */
	public static BufferedImage generateQRCode(String data) {
		
		return generateQRCode(data, new Dimension(150, 150));
		
	}
	
	/**
	 * Returns an encoded QR Code in the form of a BufferedImage. The
	 * function will return null if an error is returned by the
	 * QR API or by an IOException (If the resource cannot be found).
	 * <p>
	 * The size of the QR code generated is equal to the size
	 * parameter provided.
	 *
	 * @param  data a string containing the data to be encoded
	 * @param  size the size of the QR code
	 * @return      the image provided by the QR Code API
	 * @see         BufferedImage
	 */
	public static BufferedImage generateQRCode(String data, Dimension size) {		
		try {
			URL url = new URL("https://api.qrserver.com/v1/create-qr-code/?size=" + (int) size.getWidth() + "x" + (int)size.getHeight() +"&data=" + data);
			BufferedImage image = ImageIO.read(url);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			
			if (in.readLine().equalsIgnoreCase("An error occurred.")) {
				return null;
			} else {
				return image;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static String generateMarkupQRCode(String data) {
		return generateMarkupQRCode(data, new Dimension(150, 150));
	}
	
	public static String generateMarkupQRCode(String data, Dimension size) {
		return "<HTML><IMG src=\"http://api.auroraweb.co.uk/?size=" + size.width + "x" + size.height + "&data=" + data + "\"></HTML>";
	}
	
}
