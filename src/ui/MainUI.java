package ui;

import java.awt.Dimension;

import javax.swing.*;

import control.Controller;

public class MainUI extends JFrame implements UI{
	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 904208402458521466L;

	private Controller ctrl;
	
	
	/**
	 * This is the constructor for the main UI.
	 */
	public MainUI(Controller c) {
		say("started!");
		this.setSize(800, 600); // If you minimize it it will be this size
		this.setMinimumSize(new Dimension(800, 600)); // kinda self explained
		this.setLocationRelativeTo(null); // Makes it appear in the center of the screen
		this.setTitle("IQP Project Main Window"); // sets the title of the window
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Actually terminates the process when you close the window
		this.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);// Makes the window maximized
		this.setUndecorated(true); // removes the window's top bar and borders.
		this.setAlwaysOnTop(true); // makes the window always above every other application that runs.
		this.addController(c);
		this.buildUI();
		this.setVisible(true); // the window isn't visible until this line.
		say("Visible!");
		
	}
	
	JButton newPatient;
	
	
	public void buildUI(){
		
	}
	
	public void addComponent(JButton c,String name){
		say("Adding Button: " + name);
		c.setName(name);
		c.addActionListener(ctrl);
		add(c);
		say("Button added.");
	}
	
	public void addComponent(JTextField c, String name){
		say("Adding Text Field: " + name);
		c.setName(name);
		say("Name set.");
		say("Adding controller...");
		c.addActionListener(ctrl);
		say("Controller added.");
		add(c);
		say("Text Field Added.");
	}
	
	public void addController(Controller c){
		say("adding controller...");
		this.ctrl = c;
		this.addKeyListener(c);
		this.addMouseListener(c);
		say("controller added.");
		ctrl.addUI(this);
	}

	@Override
	public void say(String s) {
		// TODO Auto-generated method stub
		System.out.println("MainUI: " + s);
	}

	@Override
	public JComponent getComponentByName(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
