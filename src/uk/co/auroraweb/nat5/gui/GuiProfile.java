package uk.co.auroraweb.nat5.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
import uk.co.auroraweb.nat5.util.JListModel;
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
	
	//Const labels
	JLabel lblSName = new JLabel("Name: ");
	JLabel lblSAddress = new JLabel("Address: ");
	JLabel lblSEmail = new JLabel("Email: ");
	JLabel lblSDOB = new JLabel("Date of Birth: ");
	JLabel lblEventsAttended = new JLabel("Events Attended:");
	JLabel lblSDiscount = new JLabel("Discount:");
	
	JList<String> lstEventsAttended = new JList<String>();
	
	//Buttons
	JButton btnQR = new JButton("Save QR Code...");
	JButton btnClose = new JButton("Close");
	
	//File chooser (to save QR)
	JFileChooser flcQR = new JFileChooser();
	
	public GuiProfile(JFrame frame,  Entry entry, int[] options) {		
		super(frame, "Profile");
		
		parent = frame;
		email = entry.getEmail();
		
		flcQR.setFileFilter(new FileNameExtensionFilter("Portable Network Graphics (PNG)", "png"));
		
		btnClose.addActionListener(this); 
		btnQR.addActionListener(this);
		
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
		
		//Set Discount
		lblDiscount.setText(entry.getDiscount(options[2]) + "%");
		
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
		
		panel.add(btnQR, "split 2, span 2, align right");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnClose) {
			dispose();
		}
		
		if (e.getSource() == btnQR) {
			int returnVal = flcQR.showSaveDialog(this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					QRUtils.saveQR(email, new Dimension(150, 150), flcQR.getSelectedFile());
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
