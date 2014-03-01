/*
 * GUI Diagram (WIP):
 * 
 * 	+---------+---------------------------+
 *  | QR Code | Name (Full Name)          |
 *  |         | Address                   |
 *  |         | Email Address             |
 *  |		  | Date of Birth (Age)       |
 *  +---------+---------------------------+
 *  | 			Events Attended:          |
 *  | 			+-----------------------+ |
 *  | 			|                       | |
 *  | 			|                       | |
 *  | 			+-----------------------+ |
 *  +-------------------------------------+
 *  |               [Save QR Code][Close] |
 *  +-------------------------------------+
 * 
 */

package uk.co.auroraweb.nat5.gui;

import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import uk.co.auroraweb.nat5.Entry;
import uk.co.auroraweb.nat5.util.DateUtils;
import uk.co.auroraweb.nat5.util.JListModel;
import uk.co.auroraweb.nat5.util.JPanelImage;
import uk.co.auroraweb.nat5.util.QRUtils;

public class GuiProfile extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JPanel panel = new JPanel();
	JPanel panelInfo = new JPanel();
	JPanelImage panelQR = new JPanelImage();	
	
	//Variable labels
	JLabel lblName = new JLabel();
	JLabel lblAddress = new JLabel();
	JLabel lblEmail = new JLabel();
	JLabel lblDOB = new JLabel();
	
	//Static labels
	JLabel lblEventsAttended = new JLabel("Events Attended:");	
	
	JList<String> lstEventsAttended = new JList<String>();
	
	//Buttons
	JButton btnSaveQR = new JButton("Save QR Code");
	JButton btnClose = new JButton("Close");
	
	public GuiProfile(Entry entry) {		
		super("National 5 - Music Fans");
		
		//Set Name Label
		lblName.setText(entry.getFullName());
		
		//Set Email Label
		lblEmail.setText(entry.getEmail());
		
		//Set Address Label
		lblAddress.setText(entry.getAddress());
		
		//Set Date of Birth Label
		lblDOB.setText(DateUtils.formatDate(entry.getDOB(), "dd/MM/yy"));
		
		//Attended Events List
		lstEventsAttended = new JList<String>(new JListModel<String>(entry.getAttendedEvents()));
		
		//Set QR Code Image
		BufferedImage qrCode = QRUtils.generateQRCode(entry.getEmail());
		panelQR.setBufferedImage(qrCode);
		
		
		//Info panel
		panelInfo.setLayout(new MigLayout());
		panel.add(lblName, "wrap");
		panel.add(lblAddress, "wrap");
		panel.add(lblEmail, "wrap");
		panel.add(lblDOB);
		
		//Main Panel
		panel.setLayout(new MigLayout("", "[] [fill, grow]", "[] [fill, grow] [fill, grow] []"));
		panel.add(panelQR);
		panel.add(panelInfo, "wrap");
		panel.add(lblEventsAttended, "wrap, skip");
		panel.add(lstEventsAttended, "wrap, skip");
		panel.add(btnSaveQR, "skip, split 2");
		panel.add(btnClose);
	}
	
	
}
