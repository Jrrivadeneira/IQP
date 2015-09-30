package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import ui.MainUI;
import ui.UI;

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

	public void actionPerformed(ActionEvent e) {

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

}
