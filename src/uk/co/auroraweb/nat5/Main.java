//Main class - (Currently only) Loads the main gui
//TODO: Comment

package uk.co.auroraweb.nat5;

import java.awt.Cursor;

import uk.co.auroraweb.nat5.gui.GuiMain;
import uk.co.auroraweb.nat5.gui.GuiSplashScreen;

public class Main {
	
	public static void main(String[] args) {
		
		GuiSplashScreen splash = new GuiSplashScreen();
		
		try {
			splash.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Thread.sleep(5000);
			splash.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		splash.dispose();
		
	    new GuiMain();
	}
		
}
