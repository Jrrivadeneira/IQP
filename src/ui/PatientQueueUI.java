package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

import components.JaxList;

import control.Controller;

public class PatientQueueUI extends FrameUI {

	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 2852074003736044170L;

	JButton buttonBack;
	JaxList listOfPatients;

	public PatientQueueUI(Controller c) {
		super(c);
	}

	public void buildUI() {
		this.setLayout(new BorderLayout(10, 10));
		this.listOfPatients = new JaxList();
		this.ctrl.update(this.listOfPatients);
		this.add(this.listOfPatients, BorderLayout.CENTER);
		this.buttonBack = new JButton("Back");
		this.buttonBack.addActionListener(this.ctrl);
		this.buttonBack.setPreferredSize(new Dimension(300, 100));
		this.add(this.buttonBack, BorderLayout.SOUTH);
	}

}
