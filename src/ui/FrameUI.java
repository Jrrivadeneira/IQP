package ui;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;

import control.Controller;

public abstract class FrameUI extends JFrame implements UI {

	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = -7741619825870505748L;
	Controller ctrl;
	protected HashMap<String, JComponent> components;

	/**
	 * FrameUI abstracted constructor
	 * 
	 * @param c
	 *            controller for the UI.
	 */
	public FrameUI(Controller c) {
		this.setSize(800, 600);
		this.setMinimumSize(new Dimension(800, 600));
		this.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.addController(c);
		this.buildUI();
		this.setVisible(true);
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
		if (core.Major.verboseOutputEnabled)
			System.out.println(this.getClass().getName().split("\\.")[1] + ": "
					+ s);
	}

	public JComponent getComponentByName(String s) {
		return components.get(s);
	}

	public void dispose() {
		say("Closing...");
		super.dispose();
	}

}
