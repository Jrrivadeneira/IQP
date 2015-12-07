package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import components.ChangePasswordDialog;
import components.JaxList;
import core.Major;
import ui.UI;

//import ui.SettingsUI;

public class SettingsUIController implements Controller {

	private MainUIController pctrl;
	private UI sui;

	public SettingsUIController(MainUIController muic) {
		this.pctrl = muic;

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private void back() {
		sui.dispose();
	}

	private void changePassword() {
		ChangePasswordDialog CPD = new ChangePasswordDialog();
		if (CPD.didClickSubmit())
			Major.databaseManager.changePassword(CPD.getResults());
		// SettingsUI u = (SettingsUI)sui;
	}

	public void actionPerformed(ActionEvent e) {
		String s = ((JComponent) e.getSource()).getName();
		say(s);
		if (s.equals("back")) {
			back();
		}
		if (s.equals("add hospital")) {

		}
		if (s.equals("change password")) {
			changePassword();
		}
		if (s.equals("ui tweaks")) {

		}
	}

	public void say(String s) {
		System.out.println("SettingsUIController: " + s);

	}

	public void addUI(UI u) {
		sui = u;
	}

	public void dispose() {
		this.pctrl.showMainUI();
	}

	@Override
	public void update(JaxList listOfPatients) {
		// TODO Auto-generated method stub

	}

}
