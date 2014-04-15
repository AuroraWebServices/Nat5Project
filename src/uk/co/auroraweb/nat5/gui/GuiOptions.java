package uk.co.auroraweb.nat5.gui;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class GuiOptions extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	GuiMain guiMain;
	
	int loyaltyThreshold = 3;
	int rndSelectionNo = 2;
	int discountPerEvent = 10;
	
	JPanel panel = new JPanel();
	
	//Labels
	JLabel lblLoyaltyThreshold = new JLabel("Loyalty Threshold");
	JLabel lblRndSelectionNo = new JLabel("Random Selection No.");
	JLabel lblDiscountPerEvent = new JLabel("Discount per event (%)");
	
	//Text Fields
	JTextField txtLoyaltyThreshold = new JTextField(3);
	JTextField txtRndSelectionNo = new JTextField(3);
	JTextField txtDiscountPerEvent = new JTextField(3);
	
	//Buttons
	JButton btnOK = new JButton("OK");
	JButton btnCancel = new JButton("Cancel");
	
	public GuiOptions(GuiMain frame, int[] options) {
		super(frame, "Settings");
		
		guiMain = frame;
		
		loyaltyThreshold = options[0];
		rndSelectionNo = options[1];
		discountPerEvent = options[2];
		
		generateUI();
	}
	
	public GuiOptions(GuiMain frame, GuiOptions current) {
		super(frame, "Settings");
		
		guiMain = frame;
		
		generateUI();
		
		//Set text fields to current options
		txtLoyaltyThreshold.setText(String.valueOf(current.getLoyaltyThreshold()));
		txtRndSelectionNo.setText(String.valueOf(current.getRndSelectionNo()));
		txtDiscountPerEvent.setText(String.valueOf(current.getDiscountPerEvent()));
	}
	
	private void generateUI() {
		//Set values
		txtLoyaltyThreshold.setText(Integer.toString(loyaltyThreshold));
		txtRndSelectionNo.setText(Integer.toString(rndSelectionNo));
		txtDiscountPerEvent.setText(Integer.toString(discountPerEvent));

		//Action listeners
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);
				
		//Panel config
		panel.setLayout(new MigLayout("", "", ""));
		
		panel.add(lblLoyaltyThreshold);
		panel.add(txtLoyaltyThreshold, "wrap");
		panel.add(lblRndSelectionNo);
		panel.add(txtRndSelectionNo, "wrap");
		panel.add(lblDiscountPerEvent);
		panel.add(txtDiscountPerEvent, "wrap");
	
		panel.add(btnOK, "span 2, split 2, align right");
		panel.add(btnCancel);
				
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
				
		setSize(new Dimension(this.getWidth() - 10, this.getHeight() - 10));
		setLocationRelativeTo(getParent());
		setResizable(false);
		setVisible(true);
	}
	
	public int getLoyaltyThreshold(){
		return loyaltyThreshold;
	}
	
	public int getRndSelectionNo(){ 
		return rndSelectionNo;
	}
	
	public int getDiscountPerEvent() {
		return discountPerEvent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnOK) {
			
			loyaltyThreshold = Integer.parseInt(txtLoyaltyThreshold.getText());
			rndSelectionNo = Integer.parseInt(txtRndSelectionNo.getText());
			discountPerEvent = Integer.parseInt(txtDiscountPerEvent.getText());

			guiMain.updateOptions(this);

			dispose();
		} else if (e.getSource() == btnCancel) {
			dispose();
		}
	}
	
	
}
