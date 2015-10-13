package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Controller;

public class VitalsUI extends JFrame implements UI {
	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 6216662618306237505L;
	Controller ctrl;

	private HashMap<String, JComponent> components;
	private JButton Back;
	private JLabel HeartRate;
	private JLabel SPO2;
	private JLabel BloodPressure;

	public VitalsUI(Controller c) {
		say("Started!");
		this.setSize(800, 600); 
		this.setMinimumSize(new Dimension(800, 600)); 
		this.setLocationRelativeTo(null);
		this.setTitle("IQP Project Main Window");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
		this.setUndecorated(true); 
		this.setAlwaysOnTop(true); 
		this.addController(c); 
		this.buildUI(); 
		this.setVisible(true); 
		say("Visible!");
	}

	public void dispose() {
		this.say("Closing...");
		ctrl.dispose();
		super.dispose();
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
		this.Back = new JButton("Back");
		this.Back.setName("back");
		this.Back.addActionListener(ctrl);
		this.add(Back, BorderLayout.WEST);

		JPanel labelsContainer = new JPanel();
		labelsContainer.setLayout(null);

		this.HeartRate = new JLabel("Heart Rate: ");
		this.HeartRate.setLocation(100, 50);
		this.HeartRate.setSize(300, 32);
		labelsContainer.add(HeartRate);

		this.SPO2 = new JLabel("SPO2: ");
		this.SPO2.setLocation(100, 100);
		this.SPO2.setSize(300, 32);
		labelsContainer.add(SPO2);

		this.BloodPressure = new JLabel("Blood Pressure: ");
		this.BloodPressure.setLocation(100, 150);
		this.BloodPressure.setSize(300, 32);
		labelsContainer.add(BloodPressure);

		this.add(labelsContainer, BorderLayout.CENTER);
	}

	public void updateBloodPressure(String value) {
		BloodPressure.setText("Blood Pressure: " + value);
	}

	public void updateHeartRate(String value) {
		HeartRate.setText("Heart Rate: " + value);
	}

	public void updateSPO2(String value) {
		SPO2.setText("SPO2: " + value);
	}

	public JComponent getComponentByName(String s) {
		return components.get(s);
	}
}
