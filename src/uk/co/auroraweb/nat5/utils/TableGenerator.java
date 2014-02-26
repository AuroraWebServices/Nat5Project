//Generates a table with formatting

package uk.co.auroraweb.nat5.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TableGenerator {
	
	
	
	public TableGenerator() {}
	
	public JTable generateTable(String[][] data, String[] columns) {
		
		return new JTable(data, columns)
		{

			//Sets cells to non editable
			public boolean isCellEditable(int data, int columns)
			{
				return false;
			}
			
			//Formatting
			public Component prepareRenderer(TableCellRenderer r, int data, int columns)
			{
				Component c = super.prepareRenderer(r,  data,  columns);
				
				//Sets every second row to gray
				 if (data % 2 == 0)
				 {
					 c.setBackground(Color.WHITE);
				 } else {
					 c.setBackground(Color.LIGHT_GRAY);
				 }
				 
				 //Sets the row background color to orange when selected
				 if (isCellSelected(data, columns)) {
					 c.setBackground(Color.ORANGE);
				 }
				 
				 return c;
			}
			
			//TODO: Add double click event handling
		};
	}
	
}
