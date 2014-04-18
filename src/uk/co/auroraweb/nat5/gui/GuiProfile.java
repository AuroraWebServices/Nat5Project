package uk.co.auroraweb.nat5.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;
import uk.co.auroraweb.nat5.Entry;
import uk.co.auroraweb.nat5.util.AlertManager;
import uk.co.auroraweb.nat5.util.DateUtils;
//import uk.co.auroraweb.nat5.util.JListModel;
import uk.co.auroraweb.nat5.util.QRUtils;

public class GuiProfile extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	JPanel panel = new JPanel();
	JFrame parent;
	String email;
	
	//Variable labels
	JLabel lblQR = new JLabel("<HTML><DIV width=150 height=150>Loading QR code...</DIV></HTML>");
	JLabel lblName = new JLabel();
	JLabel lblAddress = new JLabel();
	JLabel lblEmail = new JLabel();
	JLabel lblDOB = new JLabel();
	JLabel lblDiscount = new JLabel();
	
	//Constant labels
	JLabel lblSName = new JLabel("Name: ");
	JLabel lblSAddress = new JLabel("Address: ");
	JLabel lblSEmail = new JLabel("Email: ");
	JLabel lblSDOB = new JLabel("Date of Birth: ");
	JLabel lblEventsAttended = new JLabel("Events Attended:");
	JLabel lblSDiscount = new JLabel("Discount:");
	
	//List to show the names of the events attended
	JList<String> lstEventsAttended = new JList<String>();
	
	//Buttons
	JButton btnSaveQR = new JButton("Save QR Code...");
	JButton btnClose = new JButton("Close");
	
	//File chooser (to save QR)
	JFileChooser flcSaveQR = new JFileChooser();
	
	public GuiProfile(JFrame frame,  Entry entry, int[] options) {		
		super(frame, "Profile");
		
		parent = frame;
		email = entry.getEmail();
		
		flcSaveQR.setFileFilter(new FileNameExtensionFilter("Portable Network Graphics (PNG)", "png"));
		
		btnClose.addActionListener(this); 
		btnSaveQR.addActionListener(this);
		
		//Set Name Label
		lblName.setText("<HTML><B>" + entry.getFullName() + "</B></HTML>");
		
		//Set Email Label
		lblEmail.setText(entry.getEmail());
		
		//Set Address Label
		lblAddress.setText(entry.getAddress());
		
		//Set Date of Birth Label
		lblDOB.setText(DateUtils.formatDate(entry.getDOB(), "dd/MM/yy") + " (" + entry.getAge() + " years old)");
		
		//Attended Events List
		lstEventsAttended.setModel(generateListModel(entry.getAttendedEvents()));
		
		//Set Discount
		lblDiscount.setText(entry.getDiscount(options[2], options[0]) + "%");
		
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
		
		panel.add(lblSDiscount);
		panel.add(lblDiscount, "wrap");
		
		panel.add(btnSaveQR, "split 2, span 2, align right");
		panel.add(btnClose);
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setSize(new Dimension(this.getWidth(), this.getHeight() - 10)); //Height fix
		setLocationRelativeTo(getParent());
		setResizable(false);
		
		updateQR();
		setVisible(true);
		
	}
	
	/**
	 * Updates the QR code
	 */
	private void updateQR() {
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
	
	/**
	 * Generates a DefaultListModel<String> from a List<String>
	 * @param events the list to be converted
	 * @return the DefaultListModel
	 */
	private DefaultListModel<String> generateListModel(List<String> events) {
		DefaultListModel<String> output = new DefaultListModel<String>();
		
		for (String event : events) {
			output.addElement(event);
		}
		
		return output;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				
		if (e.getSource() == btnClose) {
			dispose();
		}
		
		if (e.getSource() == btnSaveQR) {
			int returnVal = flcSaveQR.showSaveDialog(this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					QRUtils.saveQR(email, new Dimension(150, 150), flcSaveQR.getSelectedFile());
					AlertManager.alert(parent, AlertManager.INFO_MSG, "QR code saved successfuly!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					AlertManager.alert(parent, AlertManager.ERROR_MSG, "Error! Could not save QR code. Ensure you are saving in a folder that you have access to.");
				}
			}
		}
	}
	
}
