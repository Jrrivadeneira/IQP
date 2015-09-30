package core;

import control.MainUIController;
import ui.MainUI;


public class Major {
	public static void main(String[] args) {
		System.out.println("Start!");
		MainUI mui = new MainUI();
		MainUIController muc = new MainUIController(mui);
	}
}
