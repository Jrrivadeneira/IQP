package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import control.Controller;

public class MainUI extends FrameUI {
	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 904208402458521466L;

	private JButton newPatient, patientQueue, settings, logout;

	/**
	 * This is the constructor for the main UI.
	 */
	public MainUI(Controller c) {
		super(c);
		setTitle("IQP Main Window");
	}

	public void buildUI() {
		newPatient = new JButton("New Patient");
		this.addComponent(newPatient, "new patient");
		this.add(newPatient, BorderLayout.CENTER);

		patientQueue = new JButton("Patient Queue");
		this.addComponent(patientQueue, "patient queue");
		patientQueue.setPreferredSize(new Dimension(200, 100));
		this.add(patientQueue, BorderLayout.WEST);

		JPanel southwest = new JPanel();
		southwest.setLayout(new BorderLayout());

		settings = new JButton("Settings");
		this.addComponent(settings, "settings");
		southwest.add(settings, BorderLayout.CENTER);

		logout = new JButton("Logout");
		logout.setPreferredSize(new Dimension(400, 300));
		this.addComponent(logout, "logout");
		southwest.add(logout, BorderLayout.WEST);
		this.add(southwest, BorderLayout.SOUTH);
		this.pack();
	}

	/**
	 * gives the component a name and adds the controller as the component's
	 * action listener.
	 * 
	 * @param c
	 * @param name
	 */
	private void addComponent(JButton c, String name) {
		say("Adding Button: " + name);
		c.setName(name);
		c.addActionListener(ctrl);
		say("Button added.");
	}

}
