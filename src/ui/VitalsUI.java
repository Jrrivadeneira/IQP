package ui;

import javax.swing.JComponent;
import javax.swing.JFrame;

import control.Controller;

public class VitalsUI extends JFrame implements UI {

	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 6216662618306237505L;
	Controller ctrl;

	public VitalsUI(Controller c) {
		addController(c);
		buildUI();
	}

	public void addController(Controller c) {
		say("adding controller...");
		this.ctrl = c;
		this.addKeyListener(c);
		this.addMouseListener(c);
		say("controller added.");
		this.ctrl.addUI(this);
	}

	public void say(String s) {
		System.out.println("VitalsUI: " + s);

	}

	public void buildUI() {
		// TODO Auto-generated method stub

	}

	public JComponent getComponentByName(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
