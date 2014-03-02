/*
 * GUI Diagram (WIP):
 * 
 * 	+---------------------------+
 *  | Name (Full Name)          |
 *  | Address                   |
 *  | Email Address             |
 *  | Date of Birth (Age)       |
 *  +---------------------------+
 *  | Events Attended:          |
 *  | +-----------------------+ |
 *  | |                       | |
 *  | |                       | |
 *  | +-----------------------+ |
 *  +---------------------------+
 *  |          [QR Code][Close] |
 *  +---------------------------+
 * 
 */

package uk.co.auroraweb.nat5.gui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import uk.co.auroraweb.nat5.Entry;
import uk.co.auroraweb.nat5.util.DateUtils;
import uk.co.auroraweb.nat5.util.JListModel;
import uk.co.auroraweb.nat5.util.QRUtils;

public class GuiProfile extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	JPanel panel = new JPanel();
	
	//Variable labels
	JLabel lblQR = new JLabel("<HTML><DIV width=150 height=150>Loading QR code...</DIV></HTML>");
	JLabel lblName = new JLabel();
	JLabel lblAddress = new JLabel();
	JLabel lblEmail = new JLabel();
	JLabel lblDOB = new JLabel();
	
	//Const labels
	JLabel lblSName = new JLabel("Name: ");
	JLabel lblSAddress = new JLabel("Address: ");
	JLabel lblSEmail = new JLabel("Email: ");
	JLabel lblSDOB = new JLabel("Date of Birth: ");
	JLabel lblEventsAttended = new JLabel("Events Attended:");	
	
	JList<String> lstEventsAttended = new JList<String>();
	
	//Buttons
	JButton btnQR = new JButton("Save QR Code...");
	JButton btnClose = new JButton("Close");
	
	public GuiProfile(JFrame frame, boolean modal, Entry entry) {		
		super(frame, "Profile", modal);
		
		btnClose.addActionListener(this); 
		
		//Set Name Label
		lblName.setText("<HTML><B>" + entry.getFullName() + "</B></HTML>");
		
		//Set Email Label
		lblEmail.setText(entry.getEmail());
		
		//Set Address Label
		lblAddress.setText(entry.getAddress());
		
		//Set Date of Birth Label
		lblDOB.setText(DateUtils.formatDate(entry.getDOB(), "dd/MM/yy") + " (" + entry.getAge() + " years old)");
		
		//Attended Events List
		lstEventsAttended = new JList<String>(new JListModel<String>(entry.getAttendedEvents()));
		
		panel.setLayout(new MigLayout("", "[fill, grow]", ""));
		panel.add(lblQR, "wrap, span 2, align center");
		
		panel.add(lblSName);
		panel.add(lblName, "wrap");
		
		panel.add(lblSAddress);
		panel.add(lblAddress, "wrap");
		
		panel.add(lblSEmail);
		panel.add(lblEmail, "wrap");
		
		panel.add(lblSDOB);
		panel.add(lblDOB, "wrap");
		
		panel.add(lblEventsAttended, "top");
		panel.add(lstEventsAttended, "wrap");
		panel.add(btnQR, "split 2, span 2, align right");
		panel.add(btnClose);
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(getParent());
		setResizable(false);
		
		updateQR(entry.getEmail());
		setVisible(true);
		
	}
	
	private void updateQR(final String email) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				lblQR.setText(QRUtils.generateMarkupQRCode(email));
				revalidate();
				setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnClose) {
			dispose();
		}
	}
	
	
}
