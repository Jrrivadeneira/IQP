package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import components.JaxList;

import ui.UI;

public class VitalsUIController implements Controller {
	
	MainUIController muic;
	
	public VitalsUIController(MainUIController mui){
		this.muic = mui;
	}
	
	private UI vui;

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void backButton(){
		dispose();
		vui.dispose();
	}

	public void actionPerformed(ActionEvent e) {
		String s = ((JComponent) e.getSource()).getName();
		say(s);
		if (s.equals("back")) {
			backButton();
		}
	}

	public void say(String s) {
		System.out.println("VitalsUIController: " + s);

	}

	public void addUI(UI u) {
		vui = u;
	}

	@Override
	public void dispose() {
		muic.showMainUI();
	}

	@Override
	public void update(JaxList listOfPatients) {
		// TODO Auto-generated method stub
		
	}

}
