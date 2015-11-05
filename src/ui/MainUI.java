package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.Border;

import control.Controller;

public class MainUI extends FrameUI {
	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 904208402458521466L;

	private JButton newPatient, patientQueue, settings, logout;
	Border mt;

	/**
	 * This is the constructor for the main UI.
	 */
	public MainUI(Controller c) {
		super(c);
		setTitle("IQP Main Window");
	}

	public void buildUI() {
		int padding = 30;
		mt = BorderFactory
				.createEmptyBorder(padding, padding, padding, padding);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(padding, padding));
		mainPanel.setBorder(mt);
		this.setLayout(new BorderLayout(padding, 0));

		newPatient = new JButton("New Patient");
		this.addComponent(newPatient, "new patient");
		mainPanel.add(newPatient, BorderLayout.CENTER);

		patientQueue = new JButton("Patient Queue");
		this.addComponent(patientQueue, "patient queue");
		patientQueue.setPreferredSize(new Dimension(200, 100));
		mainPanel.add(patientQueue, BorderLayout.WEST);

		JPanel southwest = new JPanel();
		southwest.setLayout(new BorderLayout(padding, 0));

		settings = new JButton("Settings");
		this.addComponent(settings, "settings");
		southwest.add(settings, BorderLayout.CENTER);

		logout = new JButton("Logout");
		logout.setPreferredSize(new Dimension(400, 300));
		this.addComponent(logout, "logout");
		southwest.add(logout, BorderLayout.WEST);
		mainPanel.add(southwest, BorderLayout.SOUTH);
		this.add(mainPanel, BorderLayout.CENTER);

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
