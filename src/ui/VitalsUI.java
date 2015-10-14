package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Controller;

public class VitalsUI extends FrameUI {
	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 6216662618306237505L;
	private JButton Back;
	private JLabel HeartRate;
	private JLabel SPO2;
	private JLabel BloodPressure;

	/**
	 * Constructor for the frame.
	 * @param c - controller for the frame
	 */
	public VitalsUI(Controller c) {
		super(c);
		this.setTitle("IQP Vitals Window");
	}

	public void dispose() {
		ctrl.dispose();
		super.dispose();
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

	/**
	 * changes the displayed value of blood pressure to the given string
	 * 
	 * @param value
	 */
	public void updateBloodPressure(String value) {
		BloodPressure.setText("Blood Pressure: " + value);
	}

	/**
	 * changes the displayed value of Heart Rate to the given string
	 * 
	 * @param value
	 */
	public void updateHeartRate(String value) {
		HeartRate.setText("Heart Rate: " + value);
	}

	/**
	 * changes the displayed value of SPO2 to the given string
	 * 
	 * @param value
	 */
	public void updateSPO2(String value) {
		SPO2.setText("SPO2: " + value);
	}
}
