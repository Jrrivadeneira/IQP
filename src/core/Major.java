package core;

import model.EMT;
import model.Windtalker;
import control.LoginUIController;
import control.MainUIController;
import ui.LoginUI;
import ui.MainUI;

public class Major {
	public static boolean verboseOutputEnabled = true;
	public static Windtalker databaseManager;

	public static void main(String[] args) {
		System.out.println("Start!");
		mockDB();
		makeLoginUI();

	}

	public static void mockDB() {
		databaseManager = new Windtalker();
		databaseManager.mockDB();
	}

	public static void makeLoginUI() {
		LoginUIController luc = new LoginUIController();
		new LoginUI(luc);
	}

	public static void makeMainUI() {
		MainUIController muc = new MainUIController();
		new MainUI(muc);
	}

	public static void showMainUI() {

	}

	public static void makeAddEMSUI() {

	}

	public static void makeAddEMTUI() {

	}

	public static void makeAddHospitalUI() {

	}

	public static void makeNewPatientUI() {

	}

	public static void makeVitalsUI() {

	}
}