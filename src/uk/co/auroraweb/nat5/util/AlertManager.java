package uk.co.auroraweb.nat5.util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AlertManager {
	
	public static int ERROR_MSG = 0;
	public static int INFO_MSG = 1;
	public static int WARN_MSG = 2;
	
	/**
	 * Displays a dialog to the user
	 * @param frame the JFrame
	 * @param type the type of message; 0 for error, 1 for info and 2 for warning
	 * @param msg the message to be displayed
	 */
	public static void alert(JFrame frame, int type, String msg) {
		JOptionPane.showMessageDialog(frame, msg, "Music Fans", type);
	}
	
}
