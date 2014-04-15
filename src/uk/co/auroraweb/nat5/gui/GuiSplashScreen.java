package uk.co.auroraweb.nat5.gui;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class GuiSplashScreen extends JWindow {

	private static final long serialVersionUID = 1L;
	
	public GuiSplashScreen() {
		
		URL imgURL = getClass().getResource("/res/splash.jpg");
		ImageIcon img = new ImageIcon(imgURL);
		JLabel lblImg = new JLabel("", img, JLabel.CENTER);
		
		pack();
		setSize(new Dimension(500, 200));
		setLocationRelativeTo(null);
		add(lblImg);
		pack();
		setVisible(true);
		
	}
	
}
