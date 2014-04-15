package uk.co.auroraweb.nat5.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import uk.co.auroraweb.nat5.Entry;
import uk.co.auroraweb.nat5.util.AlertManager;
import uk.co.auroraweb.nat5.util.CSVParser;
import uk.co.auroraweb.nat5.util.EntryUtils;
import uk.co.auroraweb.nat5.util.FileUtils;
import uk.co.auroraweb.nat5.util.TableUtils;

public class GuiMain extends JFrame implements ActionListener {
	
	//options[0] = Loyalty Threshold (Set to 3 by default); options[1] = Random entry selection number; options[2] = Discount per event (%)
	int options[] = {3, 2, 10};
	
	List<Entry> rawData;
	List<Entry> filteredData;
	TableRowSorter<DefaultTableModel> tableSorter;
	DefaultTableModel tblModel;
	
	private static final long serialVersionUID = 1L;
	
	//Panel
	JPanel panel = new JPanel();
	
	//File label
	JLabel lblFile = new JLabel("File:");
	
	//Text field displaying the open file
	JTextField txtFile = new JTextField();
	
	//Import button
	JButton btnImport = new JButton("Import data...");
	
	//Filter button
	JButton btnOptions = new JButton("Settings...");
	
	//Exit button
	JButton btnExit = new JButton("Exit");
	
	//Table containing fans
	JTable tblFans;
	
	//File chooser (To allow the user to select the desired file for parsing
	JFileChooser fc = new JFileChooser();
	
	//Help Button
	JButton btnAbout = new JButton("?");
	
	//Copyright Label
	JLabel lblCopy = new JLabel("National 5 Music Fans | Copyright Adam Hirst 2014.", SwingConstants.CENTER);
	
	public GuiMain() {
		//Sets the gui title
		super("National 5 - Music Fans");
		
		//Adds the event listener for the import button
		btnImport.addActionListener(this);
		btnOptions.addActionListener(this);
		btnAbout.addActionListener(this);
		btnExit.addActionListener(this);
		
		fc.setFileFilter(new FileNameExtensionFilter("CSV Files (.csv)", "csv"));
		
		txtFile.setEditable(false);
		txtFile.setBackground(Color.LIGHT_GRAY);
		
		tblFans = TableUtils.generateTable();
		tblFans.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		final JFrame frmThis = this;
		
		tblFans.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = tblFans.rowAtPoint(evt.getPoint());
		        int col = tblFans.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0 && evt.getClickCount() >= 2) {
		            new GuiProfile(frmThis, rawData.get(tblFans.getSelectedRow()), options);
		        }		        
		    }
		});
		
		tblFans.setAutoCreateRowSorter(true);
		
		//TODO: Design UI
		panel.setLayout(new MigLayout("", "[] [grow, fill] [fill]", "[] [grow, fill] []"));
		
		//Top bar
		panel.add(lblFile);
		panel.add(txtFile);
		panel.add(btnImport, "wrap");
		
		//Table
		panel.add(new JScrollPane(tblFans), "span 3, grow, wrap");
		
		panel.add(btnAbout);
		
		panel.add(lblCopy);
		
		panel.add(btnOptions, "split 2");
		panel.add(btnExit);
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setSize(900, 500);
		setMinimumSize(new Dimension(900,500));
		
		setLocationRelativeTo(null);
		
		URL iconURL = getClass().getResource("/res/icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());
		
		setVisible(true);
	}
	
	public void refreshTable() {
		tblModel = TableUtils.updatedTable(rawData, options);
		tblFans.setModel(tblModel);
		tblModel.fireTableDataChanged();
			
		tableSorter = new TableRowSorter<DefaultTableModel>(tblModel);
	}
	
	public void updateOptions(GuiOptions guiOptions) {
		options[0] = guiOptions.getLoyaltyThreshold();
		options[1] = guiOptions.getRndSelectionNo();
		options[2] = guiOptions.getDiscountPerEvent();
		
		if (rawData != null) {
			refreshTable();
			generateWinners();
		}
		
		AlertManager.alert(this, AlertManager.INFO_MSG, "Options successfuly updated!");
	}
	
	private void generateWinners() {
		List<Entry> loyalFans = EntryUtils.getLoyalFans(rawData, options);
		Random rnd = new Random();
		
		for (int i = 0; i < options[1]; i++) {
			int rndIndex = rnd.nextInt(loyalFans.size());
			Entry winner = loyalFans.get(rndIndex);
			
			loyalFans.remove(rndIndex);
			
			tblFans.setValueAt("Winner", rawData.indexOf(winner), 7);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnImport) {
			
			if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			
				String file = fc.getSelectedFile().getPath();
			
				if (FileUtils.verifyFileFormat(file, "csv")) {
					
					rawData = CSVParser.parseCSV(file, this);
					
					if (rawData != null) {
						refreshTable();
						generateWinners();
						txtFile.setText(file);
						AlertManager.alert(this,  AlertManager.INFO_MSG, "File imported with no errors!");
					}
					
				} else {
					//Provoke error
					AlertManager.alert(this, AlertManager.ERROR_MSG, "Error: File could not be read.");
				}
			}
		} else if (e.getSource() == btnOptions) {
			//Create options window
			new GuiOptions(this, options);
			
		} else if (e.getSource() == btnAbout) {
			AlertManager.alert(this, AlertManager.INFO_MSG, "Music Fans - National 5 Project\nA Java program by Adam Hirst\nFor more information on this project, visit\n<HTML><FONT color=\"#000099\"><U>http://nat5.auroraweb.co.uk</U></FONT></HTML>");
		} else if (e.getSource() == btnExit) {
			//Close the program
			dispose();
		}

	}
		
}
