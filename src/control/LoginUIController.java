package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import ui.UI;

public class LoginUIController implements Controller {

	private boolean verified = true;
	private UI login;
	
	public boolean isValid(){
		return verified;
	}
	
	public LoginUIController() {
		this.say("Start.");

	}

	private boolean verify(Object o){
		return true;
	}
	
	public void addUI(UI u){
		this.login=u;
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
		if(s.equals("username")){
			this.login.getComponentByName("password").requestFocus();
		}
		if("signin password".contains(s)){
			say("validating login...");
			if(this.verify(null)){
				say("verified.");
				login.dispose();
			}
		}
	}

	public void say(String s) {
		System.out.println("LoginUIController: " + s);
	}

}
