package uk.co.auroraweb.nat5.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class JPanelImage extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private BufferedImage img;
	
	public JPanelImage(BufferedImage img) {
		this.img = img;
	}
	
	public JPanelImage() {
		
	}
	
	public void setBufferedImage(BufferedImage img) { 
		this.img = img;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, 150, 150, Color.WHITE, null);
	}
	
}
