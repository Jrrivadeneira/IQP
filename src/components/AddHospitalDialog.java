package components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddHospitalDialog extends JDialog implements ActionListener {
	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 2490536702195920979L;
	JButton buttonSubmit, buttonCancel;

	JTextField textFieldName, textFieldAddress, textFieldPhoneNumber;

	public AddHospitalDialog() {
		JaxPanel jp = new JaxPanel();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		GridLayout gl = new GridLayout(5, 1);
		int gapSize = core.Configuration.uiSpacing;
		gl.setHgap(gapSize);
		gl.setVgap(gapSize);

		jp.setLayout(gl);
		this.setLayout(new BorderLayout(gapSize, gapSize));

		this.setSize(500, 500);
		this.setTitle("Add Hospital");
		this.setLocationRelativeTo(null);
		this.buttonSubmit = new JButton("Submit");
		this.buttonCancel = new JButton("Cancel");
		this.textFieldName = new JTextField("Name");
		this.textFieldAddress = new JTextField("Address");
		this.textFieldPhoneNumber = new JTextField("Phone Number");

		jp.add(this.textFieldName);
		jp.add(this.textFieldAddress);
		jp.add(this.textFieldPhoneNumber);

		jp.add(this.buttonSubmit);
		jp.add(this.buttonCancel);

		this.add(jp);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new AddHospitalDialog();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.buttonCancel)
			this.dispose();
		if(e.getSource()==this.buttonSubmit){}
	}
}
