package core;

import control.LoginUIController;
import control.MainUIController;
import ui.LoginUI;
import ui.MainUI;


public class Major {
	public static void main(String[] args) {
		System.out.println("Start!");
		makeLogin();
	}
	
	public static void makeLogin(){
		LoginUIController luc = new LoginUIController();
		new LoginUI(luc);
	}
	
	public static void makeMainUI(){
		MainUIController muc = new MainUIController();
		new MainUI(muc);

	}
	
	
}
