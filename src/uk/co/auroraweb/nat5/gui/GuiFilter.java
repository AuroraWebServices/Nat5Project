/*
 * +--------------------------+
 * | Filter by: [Dropdown [v] |
 * | 0 More than O less than  |
 * | Value: [ <> Value      ] |
 * | 0 Filter from raw        |
 * | O Filter from filtered   |
 * |     [ OK ] [ Cancel ]    |
 * +--------------------------+
 */

package uk.co.auroraweb.nat5.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class GuiFilter extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	JPanel panel = new JPanel();
	
	//Labels
	JLabel lblFilterTxt = new JLabel("Text:  ");
	JLabel lblFilterBy = new JLabel("Filter By: ");
	JLabel lblValue = new JLabel("Value:");
	
	//Dropdown
	JComboBox<String> cmbFilterBy = new JComboBox<String>(new String[] {"None", "Events attended", "Age"});
	
	//Radio buttons
	JRadioButton rdbMoreThan = new JRadioButton("More than");
	JRadioButton rdbLessThan = new JRadioButton("Less than");
	JRadioButton rdbFilterType1 = new JRadioButton("Filter from raw data");
	JRadioButton rdbFilterType2 = new JRadioButton("Filter from filtered");
	
	//Radio button groups
	ButtonGroup btnGroupCondition = new ButtonGroup();
	ButtonGroup btnGroupFilterType = new ButtonGroup();
	
	//Text fields
	JTextField txtFilter = new JTextField(12);
	JTextField txtValue = new JTextField(12);
	
	//Buttons
	JButton btnOK = new JButton("OK");
	JButton btnCancel = new JButton("Cancel");
	
	boolean filterChanged = false;
	
	public GuiFilter(JFrame frame, boolean modal) {
		super(frame, "Add Filter", modal);
		
		//Add radio buttons to respective groups
		btnGroupCondition.add(rdbMoreThan);
		btnGroupCondition.add(rdbLessThan);
		btnGroupFilterType.add(rdbFilterType1);
		btnGroupFilterType.add(rdbFilterType2);
		
		//Action listeners
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);
		cmbFilterBy.addActionListener(this);
		
		//Set defaults
		rdbMoreThan.setSelected(true);
		rdbFilterType1.setSelected(true);
		
		setFiltersEnabled(false);
		
		//Panel config
		panel.setLayout(new MigLayout("", "", ""));
		
		panel.add(lblFilterTxt, "split 2");
		panel.add(txtFilter, "align right, wrap");
		
		panel.add(lblFilterBy, "split 2");
		panel.add(cmbFilterBy, "wrap");
		
		panel.add(rdbMoreThan, "split 2");
		panel.add(rdbLessThan, "wrap");
		
		panel.add(lblValue, "split 2");
		panel.add(txtValue, "wrap");
		
		panel.add(rdbFilterType1, "wrap");
		
		panel.add(rdbFilterType2, "wrap");
		
		panel.add(btnOK, "split 2, align right");
		panel.add(btnCancel);
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		
		setSize(new Dimension(this.getWidth() - 10, this.getHeight() - 10));
		setLocationRelativeTo(getParent());
		setResizable(false);
		setVisible(true);
		
	}
	
	public String getSearch() {
		return txtFilter.getText();
	}
	
	public int getFilterBy() {
		return cmbFilterBy.getSelectedIndex(); 
	}
	
	/**
	 * (Used locally to) set the components to disabled if the 
	 * filter type dropdown is set to "None"
	 * 
	 * @param type true to enable components, false to disable
	 */
	private void setFiltersEnabled(boolean type) {
		rdbMoreThan.setEnabled(type);
		rdbLessThan.setEnabled(type);
		txtValue.setEditable(type);
		rdbFilterType1.setEnabled(type);
		rdbFilterType2.setEnabled(type);
		
		if (type) {
			txtValue.setBackground(Color.WHITE);
			lblValue.setForeground(Color.BLACK); 
		} else {
			txtValue.setBackground(Color.LIGHT_GRAY);
			lblValue.setForeground(Color.GRAY);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnOK) {
			filterChanged = true;
			dispose();
		} else if (e.getSource() == btnCancel) {
			dispose();
		} else if (e.getSource() == cmbFilterBy) {
			if (cmbFilterBy.getSelectedIndex() == 0) {
				setFiltersEnabled(false);				
			} else {
				setFiltersEnabled(true);
			}
		}
	}
	
	
}
