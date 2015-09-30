package core;

import control.LoginUIController;
import control.MainUIController;
import ui.LoginUI;
import ui.MainUI;


public class Major {
	public static void main(String[] args) {
		System.out.println("Start!");
		LoginUIController luc = new LoginUIController();
		LoginUI lui = new LoginUI(luc);

//		MainUIController muc = new MainUIController();
//		MainUI mui = new MainUI(muc);

	}
}
