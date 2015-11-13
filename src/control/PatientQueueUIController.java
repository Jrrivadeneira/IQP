package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import components.JaxList;

import ui.UI;

public class PatientQueueUIController implements Controller {

	UI pqui;

	public PatientQueueUIController() {

	}

	public PatientQueueUIController(UI ui) {
		this.addUI(ui);
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

	}

	public void say(String s) {

	}

	public void addUI(UI u) {
		this.pqui = u;
	}

	public void dispose() {

	}

	public void update(JaxList listOfPatients) {

	}
}
