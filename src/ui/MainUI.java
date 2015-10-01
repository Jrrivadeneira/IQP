package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import control.Controller;

public class MainUI extends JFrame implements UI{
	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 904208402458521466L;

	private Controller ctrl;
	private JButton newPatient, patientQueue, settings, logout;
	
	
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
		this.addController(c); // adds the controller
		this.buildUI(); // makes the actual UI
		this.setVisible(true); // the window isn't visible until this line.
		say("Visible!");	
	}
	
	public void dispose(){
		say("closing...");
		super.dispose();
	}
	
	
	public void buildUI(){
		newPatient = new JButton("New Patient");
		this.addComponent(newPatient, "new patient");
		this.add(newPatient,BorderLayout.CENTER);
		
		patientQueue = new JButton("Patient Queue");
		this.addComponent(patientQueue, "patient queue");
		patientQueue.setPreferredSize(new Dimension(200,100));
		this.add(patientQueue,BorderLayout.WEST);
		
		JPanel southwest = new JPanel();
		southwest.setLayout(new BorderLayout());
		
		settings = new JButton("Settings");
		this.addComponent(settings, "settings");
		southwest.add(settings,BorderLayout.CENTER);
		
		logout = new JButton("Logout");
		logout.setPreferredSize(new Dimension(400,300));
		this.addComponent(logout, "logout");
		southwest.add(logout, BorderLayout.WEST);
		this.add(southwest,BorderLayout.SOUTH);
		this.pack();
	}
	
	private void addComponent(JButton c, String name){
		say("Adding Button: " + name);
		c.setName(name);
		c.addActionListener(ctrl);
//		add(c);
		say("Button added.");
	}
	
//	private void addComponent(JTextField c, String name){
//		say("Adding Text Field: " + name);
//		c.setName(name);
//		say("Name set.");
//		say("Adding controller...");
//		c.addActionListener(ctrl);
//		say("Controller added.");
//		add(c);
//		say("Text Field Added.");
//	}
	
	public void addController(Controller c){
		say("adding controller...");
		this.ctrl = c;
		this.addKeyListener(c);
		this.addMouseListener(c);
		say("controller added.");
		ctrl.addUI(this);
	}

	public void say(String s) {
		System.out.println("MainUI: " + s);
	}

	public JComponent getComponentByName(String s) {
		return null;
	}

}
