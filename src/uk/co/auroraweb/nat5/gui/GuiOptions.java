package uk.co.auroraweb.nat5.gui;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	boolean loyalOnly = false;
	
	JPanel panel = new JPanel();
	
	//Labels
	JLabel lblLoyaltyThreshold = new JLabel("Loyalty threshold");
	JLabel lblRndSelectionNo = new JLabel("Random selection no.");
	JLabel lblDiscountPerEvent = new JLabel("Discount per event (%)");
	
	//Text Fields
	JTextField txtLoyaltyThreshold = new JTextField(3);
	JTextField txtRndSelectionNo = new JTextField(3);
	JTextField txtDiscountPerEvent = new JTextField(3);
	
	//Buttons
	JButton btnOK = new JButton("OK");
	JButton btnCancel = new JButton("Cancel");
	
	//Check Boxes
	JCheckBox chcLoyalOnly = new JCheckBox("Only display loyal fans");
	
	public GuiOptions(GuiMain frame, int[] options) {
		super(frame, "Settings");
		
		guiMain = frame;
		
		loyaltyThreshold = options[0];
		rndSelectionNo = options[1];
		discountPerEvent = options[2];
		if (options[3] == 1) {
			loyalOnly = true;
		}
		
		generateUI();
	}
	
	/**
	 * Generates the user interface
	 */
	private void generateUI() {
		//Set values
		txtLoyaltyThreshold.setText(Integer.toString(loyaltyThreshold));
		txtRndSelectionNo.setText(Integer.toString(rndSelectionNo));
		txtDiscountPerEvent.setText(Integer.toString(discountPerEvent));
		
		if (loyalOnly) {
			chcLoyalOnly.setSelected(true);
		}

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
		panel.add(chcLoyalOnly, "span 2, wrap");
	
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
	
	/**
	 * Returns the loyalty threshold
	 * @return the loyalty threshold.
	 */
	public int getLoyaltyThreshold(){
		return loyaltyThreshold;
	}
	
	/**
	 * Returns the random selection number
	 * @return the random selection number.
	 */
	public int getRndSelectionNo(){ 
		return rndSelectionNo;
	}
	
	/**
	 * Returns the discount per event
	 * @return the discount per event.
	 */
	public int getDiscountPerEvent() {
		return discountPerEvent;
	}
	
	/**
	 * Returns if the loyal only option is selected as a boolean
	 * @return true if 'Display loyalty fans only' is selected.
	 */
	public boolean getLoyalOnly() {
		return loyalOnly;
	}
	
	/**
	 * Returns if the loyal only option is selected as an integer (1 for true, 0 for false) 
	 * @return 1 if 'Display loyalty fans only' is selected, else returns 0.
	 */
	public int getIntLoyalOnly() {
		if (loyalOnly) {
			return 1;
		} else {
			return 0;
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnOK) {
			
			loyaltyThreshold = Integer.parseInt(txtLoyaltyThreshold.getText());
			rndSelectionNo = Integer.parseInt(txtRndSelectionNo.getText());
			discountPerEvent = Integer.parseInt(txtDiscountPerEvent.getText());
			loyalOnly = chcLoyalOnly.isSelected();

			guiMain.updateOptions(this);

			dispose();
		} else if (e.getSource() == btnCancel) {
			dispose();
		}
	}
	
	
}
