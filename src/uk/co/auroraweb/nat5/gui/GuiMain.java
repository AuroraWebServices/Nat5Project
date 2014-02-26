//Main Gui - Displays a button used to import a .csv and displays a table with fan information
//TODO: Open the file chooser when 'Import data' button is pressed

package uk.co.auroraweb.nat5.gui;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import uk.co.auroraweb.nat5.utils.TableImporter;
import uk.co.auroraweb.nat5.utils.TableGenerator;

public class GuiMain extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	//Panel
	JPanel panel = new JPanel();
	
	//File label
	JLabel lblFile = new JLabel("File:");
	
	//Text field displaying the open file
	JTextField txtFile = new JTextField();
	
	//Import button
	JButton btnImport = new JButton("Import data...");
	
	//Table containing fans
	JTable tblFans;
	
	JFileChooser fc = new JFileChooser();
	
	public GuiMain() {
		//Sets the gui title
		super("National 5 - Music Fans");
		
		//Sets the table contents
		tblFans = TableGenerator.generateTable(TableImporter.data(), TableImporter.columns());		
		
		//TODO: Design UI
		panel.setLayout(new MigLayout("", "[] [grow, fill] []", "[] [grow, fill]"));
		
		//Top bar
		panel.add(lblFile);
		panel.add(txtFile);
		panel.add(btnImport, "wrap");
		
		//Table
		panel.add(new JScrollPane(tblFans), "span 3, grow");
		
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocation(300, 300);
		setVisible(true);
	}

}
