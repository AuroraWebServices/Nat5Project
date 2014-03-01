//Gui will display information on selected fan
//TODO: *

/**
 * Diagram:
 * 
 * 	+--------+----------------------+
 *  |        |Name					|
 *  |QR code |Email	Address			|
 *  |		 |Address				|
 *  +--------+----------------------+
 *  |		Attended Events			|
 *  |		   (ListBox)			|
 *  |								|
 *  +-------------------------------+
 *  |					 [Exit btn] |
 *  +-------------------------------+
 * 
 * 	v1,
 */

package uk.co.auroraweb.nat5.gui;

import java.awt.Image;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import uk.co.auroraweb.nat5.Entry;
import uk.co.auroraweb.nat5.util.QRUtils;
import uk.co.auroraweb.nat5.util.DateUtils;

public class GuiProfile extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JButton btnClose = new JButton();
	
	//DrawPanel panel = new DrawPanel();
	
	
	//TODO: Manage import data
	public GuiProfile(Entry entry) {
		super("National 5 Project");
		
		String fullName = entry.getFullName();
		String email = entry.getEmail();
		String address = entry.getAddress();
		String dOB = DateUtils.formatDate(entry.getDOB(), "dd/MM/yy");
		List<String> attendedEvents = entry.getAttendedEvents();
		
		Image qrCode = QRUtils.generateQRCode(email);
		
	}
	
}
