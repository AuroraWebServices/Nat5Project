//Utils for QR codes (Encoding)
//TODO: Testing; comments

package uk.co.auroraweb.nat5.util;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class QRUtils {
	
	public static Image generateQRCode(String msg) {
		URL url;
		Image image;
		
		try {
			url = new URL("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + msg);
			image = ImageIO.read(url);
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
}
