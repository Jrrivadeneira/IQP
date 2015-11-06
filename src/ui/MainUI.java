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

		int padding = 10;
		JPanel southwest = new JPanel();
		southwest.setLayout(new BorderLayout(padding, padding));

		mt = BorderFactory
				.createEmptyBorder(padding, padding, padding, padding);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(padding, padding));
		mainPanel.setBorder(mt);
		this.setLayout(new BorderLayout(padding, 0));

		this.newPatient = new JButton("New Patient");
		this.newPatient.setFont(core.Configuration.buttonFont);
		this.addComponent(newPatient, "new patient");
		mainPanel.add(newPatient, BorderLayout.CENTER);

		this.patientQueue = new JButton("Patient Queue");
		// this.patientQueue.setText("Patient \nQueue");
//		this.patientQueue.setLayout(new BorderLayout(3, 3));
//		this.patientQueue.add(new JLabel("Patient"), BorderLayout.NORTH);
//		this.patientQueue.add(new JLabel("Queue"), BorderLayout.SOUTH);

		this.patientQueue.setFont(core.Configuration.buttonFont);
		this.addComponent(patientQueue, "patient queue");
		this.patientQueue.setPreferredSize(new Dimension(500, 600));
		southwest.add(patientQueue, BorderLayout.NORTH);

		this.settings = new JButton("Settings");
		this.addComponent(settings, "settings");
		this.settings.setPreferredSize(new Dimension(500, 200));
		this.settings.setFont(core.Configuration.buttonFont);
		southwest.add(settings, BorderLayout.SOUTH);

		this.logout = new JButton("Logout");
		this.logout.setPreferredSize(new Dimension(500, 300));
		this.addComponent(logout, "logout");
		this.logout.setFont(core.Configuration.buttonFont);
		southwest.add(logout, BorderLayout.CENTER);
		mainPanel.add(southwest, BorderLayout.WEST);
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
