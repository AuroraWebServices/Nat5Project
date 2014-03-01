package uk.co.auroraweb.nat5.gui;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.miginfocom.swing.MigLayout;

public class GuiFilter extends JFrame {
	
	private static final long serialVersionUID = 1L;

	JPanel panel = new JPanel();
	
	JLabel lblType = new JLabel("Filter type:"); 
	JLabel lblArgs = new JLabel("Arguments:");
	
	JComboBox cmbType = new JComboBox(new String[] {"Events attended", "Age"});
	
	JRadioButton btn = new JRadioButton();
	
	public GuiFilter() {
		super("Add Filter");
		
		panel.setLayout(new MigLayout());
		
		panel.add(cmbType);
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocation(300, 300);
		setSize(900, 500);
		setMinimumSize(new Dimension(900,500));
		setVisible(true);
	}
	
}
