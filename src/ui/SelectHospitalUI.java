package ui;

import java.awt.GridLayout;

import javax.swing.JButton;

import model.Hospital;
import control.Controller;
import control.SelectHospitalUIController;

public class SelectHospitalUI extends FrameUI {

	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = -875394061165852496L;

	public SelectHospitalUI(Controller c) {
		super(c);
	}

	public void buildUI() {
		this.setLayout(new GridLayout(3, (core.Major.databaseManager
				.getHospitals().size() / 3) + 1));
		for (Hospital h : core.Major.databaseManager.getHospitals()) {
			String s = h.toString();
			JButton b = new JButton(s);
			b.setName(s);
			b.addActionListener(this.ctrl);
			this.add(b);
		}
	}

	public static void main(String[] args) {
		SelectHospitalUIController c = new SelectHospitalUIController();
		new SelectHospitalUI(c);

	}

}
