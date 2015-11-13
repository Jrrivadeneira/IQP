package components;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;

//import javax.swing.JPanel;
import core.Major;

class SelectHospitalDialog extends JDialog {
	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 7296202259515596177L;
	private JaxList listOfHospitals;
	private JButton buttonCancel;

	public SelectHospitalDialog() {
		this.buildUI();
	}

	public void buildUI() {
		this.setLayout(new BorderLayout());
		this.listOfHospitals = new JaxList();
		Major.databaseManager.update(this.listOfHospitals);
		this.add(this.listOfHospitals, BorderLayout.CENTER);

	}
}
