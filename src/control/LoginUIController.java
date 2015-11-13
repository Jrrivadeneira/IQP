package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import components.JaxList;

import core.Major;
import ui.LoginUI;
import ui.MainUI;
import ui.UI;

public class LoginUIController implements Controller {

	private boolean verified = true;
	private UI login;

	public boolean isValid() {
		return verified;
	}

	public LoginUIController() {
		this.say("Start.");

	}

	private boolean verify(String username, char[] password) {
		return Major.databaseManager.validateLogin(username, new String(
				password));
	}

	public void addUI(UI u) {
		this.login = u;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void actionPerformed(ActionEvent e) {
		String s = ((JComponent) e.getSource()).getName();
		say(s);
		if (s.equals("username")) {
			// this.login.getComponentByName("password").requestFocus();
			LoginUI ui = (LoginUI) this.login;
			ui.focusPassword();
		}
		if ("signin password".contains(s)) {
			say("validating login...");
			if (this.verify(((JTextField) this.login
					.getComponentByName("username")).getText(),
					(((JPasswordField) this.login
							.getComponentByName("password")).getPassword()))) {
				say("verified.");
				login.dispose();
				MainUIController mc = new MainUIController();
				new MainUI(mc);
			} else {
				say("Could not verify username password combination.");
			}
		}
	}

	/**
	 * Prints the given string with the classname as a prefix. Will only print
	 * if verbose output is enabled.
	 * 
	 * @param s
	 *            String- The string to be printed;
	 */
	public void say(String s) {
		if (core.Major.verboseOutputEnabled)
			System.out.println(this.getClass().getName().split("\\.")[1] + ": "
					+ s);
	}

	/**
	 * Calls the dispose function on its UI object.
	 */
	public void dispose() {
		login.dispose();
	}

	@Override
	public void update(JaxList listOfPatients) {
		// TODO Auto-generated method stub
		
	}

}
