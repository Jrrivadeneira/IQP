package ui;
import java.awt.Dimension;
import javax.swing.*;
public class MainUI extends JFrame{
	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 904208402458521466L;
	
	
	/**
	 * This is the constructor for the main UI.
	 */
	public MainUI(){
		System.out.println("MainUI started!");
		this.setSize(800,600); // If you minimize it it will be this size
		this.setMinimumSize(new Dimension(800,600)); // kinda self explained
		this.setLocationRelativeTo(null); // Makes it appear in the center of the screen
		this.setTitle("IQP Project Main Window"); //sets the title of the window
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Actually terminates the process when you close the window
		this.setExtendedState(this.getExtendedState()|MAXIMIZED_BOTH);// Makes the window maximized
		this.setUndecorated(true); // removes the window's top bar and borders.
		this.setAlwaysOnTop(true); // makes the window always above every other application that runs.
		this.setVisible(true); // the window isn't visible until this line.
		System.out.println("MainUI Visible!");
	}
	
	
}
