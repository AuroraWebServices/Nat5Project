//Main Gui - Displays a button used to import a .csv and displays a table with fan information
//TODO: Cleanup! (Drastically needed)

package uk.co.auroraweb.nat5.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import uk.co.auroraweb.nat5.Entry;
import uk.co.auroraweb.nat5.util.CSVParser;
import uk.co.auroraweb.nat5.util.FileUtils;
import uk.co.auroraweb.nat5.util.TableUtils;

public class GuiMain extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	//Panel
	JPanel panel = new JPanel();
	
	//File label
	JLabel lblFile = new JLabel("File:");
	
	//Text field displaying the open file
	JTextField txtFile = new JTextField();
	
	//Import button
	JButton btnImport = new JButton("Import data...");
	
	//Export button
	JButton btnExport = new JButton("Export data...");
	
	//Filter button
	JButton btnFilter = new JButton("Filter...");
	
	//Exit button
	JButton btnExit = new JButton("Exit");
	
	//Table containing fans
	JTable tblFans;
	
	//File chooser (To allow the user to select the desired file for parsing
	JFileChooser fc = new JFileChooser();
	
	//Help Button
	JButton btnHelp = new JButton("?");
	
	//Copyright Label
	JLabel lblCopy = new JLabel("National 5 Music Fans | Copyright Adam Hirst 2014.", SwingConstants.CENTER);
	
	public GuiMain() {
		//Sets the gui title
		super("National 5 - Music Fans");
		
		//Adds the event listener for the import button
		btnImport.addActionListener(this);
		btnExport.addActionListener(this);
		btnExit.addActionListener(this);
		
		fc.setFileFilter(new FileNameExtensionFilter("CSV Files (.csv)", "csv"));
		
		txtFile.setEditable(false);
		txtFile.setBackground(Color.LIGHT_GRAY);
		
		btnExport.setEnabled(false);
		btnFilter.setEnabled(false);
		
		tblFans = TableUtils.generateTable();
		tblFans.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tblFans.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = tblFans.rowAtPoint(evt.getPoint());
		        int col = tblFans.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
		            //TODO: OPEN PROFILE PAGE
		        	System.out.print("test");
		        }
		    }
		});
		
		
		//TODO: Design UI
		panel.setLayout(new MigLayout("", "[] [grow, fill] [fill]", "[] [grow, fill] []"));
		
		//Top bar
		panel.add(lblFile);
		panel.add(txtFile);
		panel.add(btnImport, "split 2");
		panel.add(btnExport, "wrap");
		
		//Table
		panel.add(new JScrollPane(tblFans), "span 3, grow, wrap");
		
		panel.add(btnHelp);
		
		panel.add(lblCopy);
		
		panel.add(btnFilter, "split 2");
		panel.add(btnExit);
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocation(300, 300);
		setSize(900, 500);
		setMinimumSize(new Dimension(900,500));
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnImport) {
			
			fc.showOpenDialog(this);
			
			String file = fc.getSelectedFile().getPath();
			
			if (FileUtils.verifyFileFormat(file, "csv")) {
				
				List<Entry> data = CSVParser.parseCSV(file);
				
				DefaultTableModel model = TableUtils.updatedTable(data);
				tblFans.setModel(model);
				model.fireTableDataChanged();
				
				txtFile.setText(file);
				
				btnExport.setEnabled(true);
				btnFilter.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Error: File could not be read.", "National 5 - Music Fans", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == btnExport) {
			//TODO: export functionality
			System.out.print(getSize());
		} else if (e.getSource() == btnExit) {
			//Close the program
			super.dispose();
		}

	}
		
}
