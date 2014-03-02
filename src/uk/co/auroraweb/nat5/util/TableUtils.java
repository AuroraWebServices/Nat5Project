//Generates a table with formatting

package uk.co.auroraweb.nat5.util;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import uk.co.auroraweb.nat5.Entry;

public class TableUtils {
	
	/**
	 * Generates an empty table for use on first load with the columns
	 * Unique ID, Forename, Surname, Address, Date of Birth, E-Mail
	 * Address and Events Attended.
	 * 
	 * @return an empty JTable with columns Unique ID, Forename, Surname, Address, Date of Birth, E-Mail Address and Events Attended.
	 */
	public static JTable generateTable() {
		
		return new JTable(new String[][] {}, new String[] {"Unique ID", "Forename", "Surname", "Address", "Date of Birth" ,"E-Mail Address" ,"Events attended"})
		{

			private static final long serialVersionUID = 1L;

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
					 c.setBackground(Color.CYAN);
				 }
				 
				 return c;
			}
			
			//TODO: Add double click event handling
		};
	}
	
	/**
	 * Updates a DefaultTableModel with the data provided to allow
	 * a table to be updated with the model provided.
	 * 
	 * @param data the data to update the table with
	 * @return the updated DefaultTableModel
	 * @see DefaultTableModel
	 * @see List
	 */
	public static DefaultTableModel updatedTable(List<Entry> data) {
		String[][] formattedData = new String[data.size()][7];
		
		
		
		for (int i = 0; i < data.size(); i++) {
			
			formattedData[i][0] = data.get(i).getUniqueID();
			formattedData[i][1] = data.get(i).getFirstName();
			formattedData[i][2] = data.get(i).getLastName();
			formattedData[i][3] = data.get(i).getAddress();
			formattedData[i][4] = DateUtils.formatDate(data.get(i).getDOB(), "dd/MM/yy");
			formattedData[i][5] = data.get(i).getEmail();
			formattedData[i][6] = Integer.toString(data.get(i).getNoEventsAttended());
			
		}
		
		
		return new DefaultTableModel(formattedData, new String [] {"Unique ID", "Forename", "Surname", "Address", "Date of Birth" ,"E-Mail Address" ,"Events attended"});
		
	}
	
	public static RowFilter<DefaultTableModel, Object> filterFromText(DefaultTableModel model, String text) {
		
		RowFilter<DefaultTableModel, Object> filter = null;
		
		try {
			filter = RowFilter.regexFilter(text, 0);	
		} catch (PatternSyntaxException e) { 
			e.printStackTrace(); 
		}
		
		return filter;
	}
	
}
