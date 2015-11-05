package components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public final class ChangePasswordDialog extends JDialog implements
		ActionListener {

	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 3649376132648275680L;

	private JLabel oldPass, newPass, conPass;
	private JPasswordField passwordFieldOld, passwordFieldNew,
			passwordFieldConfirmNew;
	private JButton buttonSubmit, buttonCancel;
	private final int buttonLine = 190;
	private boolean submitted;
	private Color red = new Color(255, 200, 200);
	// private Color green = new Color(200, 255, 200);
	private char[] opt, npt, cpt;

	public ChangePasswordDialog() {
		this.setSize(250, 260);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("Change Password");
		this.buildUI();
		this.setVisible(true);
	}

	private void buildUI() {
		this.setLayout(null);
		this.setResizable(false);
		this.oldPass = new JLabel("Old Password");
		this.oldPass.setBounds(20, 0, 100, 32);
		this.add(this.oldPass);

		this.newPass = new JLabel("New Password");
		this.newPass.setBounds(20, 60, 120, 32);
		this.add(this.newPass);

		this.conPass = new JLabel("Confirm New Password");
		this.conPass.setBounds(20, 120, 200, 32);
		this.add(this.conPass);

		this.passwordFieldOld = new JPasswordField();
		this.passwordFieldOld.setBounds(20, 30, 210, 32);
		this.passwordFieldOld.addActionListener(this);
		this.add(passwordFieldOld);

		this.passwordFieldNew = new JPasswordField();
		this.passwordFieldNew.setBounds(20, 90, 210, 32);
		this.passwordFieldNew.addActionListener(this);
		this.add(this.passwordFieldNew);

		this.passwordFieldConfirmNew = new JPasswordField();
		this.passwordFieldConfirmNew.setBounds(20, 150, 210, 32);
		this.passwordFieldConfirmNew.addActionListener(this);
		this.add(this.passwordFieldConfirmNew);

		this.buttonSubmit = new JButton("Submit");
		this.buttonSubmit.addActionListener(this);
		this.buttonSubmit.setBounds(130, this.buttonLine, 100, 32);
		this.add(buttonSubmit);

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(20, this.buttonLine, 100, 32);
		this.buttonCancel.addActionListener(this);
		this.add(buttonCancel);

	}

	public boolean didClickSubmit() {
		return submitted;
	}

	public static void main(String[] args) {
		System.out.println("ChangePasswordDialog test!");
		new ChangePasswordDialog();
	}

	public char[][] getResults() {
		char[][] ret = { opt, npt, cpt };
		return ret;
	}

	private boolean submit() {
		this.opt = this.passwordFieldOld.getPassword();
		// validate old password
		this.npt = this.passwordFieldNew.getPassword();
		this.cpt = this.passwordFieldConfirmNew.getPassword();
		// confirm that these match
		if (!new String(npt).equals(new String(cpt))) {
			System.out.println("They dont match!!!");
			this.passwordFieldNew.setBackground(red);
			this.passwordFieldConfirmNew.setBackground(red);
			return false;
		}
		this.submitted = true;
		this.dispose();
		return true;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.buttonCancel) {
			this.dispose();
		}
		if (e.getSource() == this.buttonSubmit) {
			submit();
		}
		if (e.getSource() == this.oldPass) {
			this.passwordFieldNew.requestFocus();
		}
		if (e.getSource() == this.newPass) {
			this.passwordFieldConfirmNew.requestFocus();
		}
	}
}
