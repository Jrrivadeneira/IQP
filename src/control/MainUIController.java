package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

import components.JaxList;

import ui.SettingsUI;
import ui.UI;
import ui.VitalsUI;

public class MainUIController implements Controller {
	/**
	 * Constructor for the MainUIController
	 */

	UI mui;

	public MainUIController() {
		System.out.println("MainUIController: Start!");
	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public void logout() {
		this.mui.dispose();
		say("Switching to login screen");
		core.Major.makeLoginUI();
	}

	public void showVitalsWindow() {
		say("Creating vitals controller...");
		VitalsUIController vuic = new VitalsUIController(this);
		say("Done.");
		say("Creating VitalsUI...");
		new VitalsUI(vuic);
		say("Hiding MainUI");
		this.hideMainUI();
	}

	private void hideMainUI() {
		say("Hiding MainUI...");
		JFrame jf = (JFrame) mui;
		jf.setVisible(false);
		say("Done.");
	}

	public void showMainUI() {
		say("Showing MainUI...");
		JFrame jf = (JFrame) mui;
		jf.setVisible(true);
		say("Done.");
	}

	private void showSettingsWindow() {
		say("Creating Settings Controller");
		SettingsUIController suc = new SettingsUIController(this);
		say("Done.");
		say("Creating SettingsUI...");
		new SettingsUI(suc);
		say("Done.");
		say("Hiding MainUI");
		this.hideMainUI();
	}

	public void actionPerformed(ActionEvent e) {
		String s = ((JComponent) e.getSource()).getName();
		say(s);
		if (s.equals("logout")) {
			this.logout();
		}
		if (s.equals("new patient")) {
			this.showVitalsWindow();
		}
		if (s.equals("settings")) {
			this.showSettingsWindow();
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void say(String s) {
		System.out.println("MainUIController: " + s);

	}

	public void addUI(UI u) {
		this.mui = u;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(JaxList listOfPatients) {
		// TODO Auto-generated method stub
		
	}

}
