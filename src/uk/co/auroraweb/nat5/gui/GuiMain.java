//Main Gui - Displays a button used to import a .csv and displays a table with fan information
//TODO: Design UI; comments

package uk.co.auroraweb.nat5.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;

import uk.co.auroraweb.nat5.utils.TableImporter;
import uk.co.auroraweb.nat5.utils.TableGenerator;

public class GuiMain extends JFrame {
	
	private static final long serialVersionUID = 1L;

	TableImporter ti = new TableImporter();
	TableGenerator tg = new TableGenerator();
	
	//Panel
	JPanel panel = new JPanel();
	
	//Import button
	JButton btnImport = new JButton();
	
	//Table containing fans
	JTable tblFans;

	
	public GuiMain() {
		super("National 5 - Music Fans");
		
		tblFans = tg.generateTable(ti.data(), ti.columns());
		
		//TODO: Design UI
		panel.setLayout(new MigLayout());
		panel.add(btnImport, "wrap");
		panel.add(new JScrollPane(tblFans));
		
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocation(300, 300);
		setVisible(true);
	}
	
}
