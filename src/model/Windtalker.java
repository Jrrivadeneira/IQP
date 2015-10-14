package model;

import java.sql.*;
import java.util.ArrayList;

public class Windtalker implements Model {
	private String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private String MYSQL_URL = "jdbc:mysql://localhost:3306/SCHOOL";
	private Connection con;
	private Statement st;
	private boolean verbose = true;
	private ResultSet rs;

	public Windtalker() {
		try {
			say("Loading class...");
			Class.forName(MYSQL_DRIVER);
			say("Loaded.");
			say("Connecting to the database...");
			con = DriverManager.getConnection(MYSQL_URL, "root", "databean");
			say("Connected.");
			st = con.createStatement();

			// wipeout();
			// int c =
			// st.executeUpdate("CREATE TABLE Patients (Name VARCHAR(30),LName VARCHAR(50))");
			// System.out.println("Table have been created.");
			// System.out.println(c + " Row(s) have been affected");

		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException:\n" + ex.toString());
			ex.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException:\n" + ex.toString());
			wipeout();
			ex.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (Exception ex) {

		}
	}

	private void executeStatement(String statement) {
		say("Executing: " + statement);
		try {
			st.executeUpdate(statement);
		} catch (SQLException ex) {
			say("Execution Failed!");
			ex.printStackTrace();
		}
		say("Succeeded.");
	}

	private void executeStatements(String[] statements) {
		for (String s : statements)
			executeStatement(s);
	}

	private void executeSelectStatement(String statement) {
		say("Executing: " + statement);
		try {
			rs = st.executeQuery(statement);
		} catch (SQLException ex) {
			say("Execution Failed!");
			ex.printStackTrace();
		}
		say("Succeeded.");
	}

	private void wipeout() {
		String statements[] = { "DROP TABLE Patients", "DROP TABLE Hospitals",
				"DROP TABLE EMS", "DROP TABLE EMT", "DROP TABLE Vitals" };
		executeStatements(statements);
	}

	/**
	 * Creates all the tables in the database.
	 */
	public void createTables() {
		String statements[] = {
				"CREATE TABLE Patients (PATIENT_ID INT, EMT_ID INT, Region INT, Notes VARCHAR(255))",
				"CREATE TABLE Hospitals (HOSPITAL_ID INT, HOSPITAL_NAME VARCHAR(255))",
				"CREATE TABLE EMS (EMS_ID INT)",
				"CREATE TABLE EMT (EMT_ID INT, EMS_ID INT, EMT_NAME VARCHAR(255), EMT_USERNAME VARCHAR(255), EMT_PASSWORD VARCHAR(255))",
				"CREATE TABLE Vitals (VITALS_ID INT, PATIENT_ID INT, SPO2 INT, Heartrate INT, Systolic INT, Diastolic INT)" };
		executeStatements(statements);
	}

	/**
	 * Main function for database testing. To be deleted.
	 * @param args
	 */
	public static void main(String[] args) {
		Windtalker wt = new Windtalker();
		wt.wipeout();
		wt.createTables();
		wt.addHospital(new Hospital(1, "Colombia Presbyterian"));
		wt.addHospital(new Hospital(2, "Bellevue"));
		wt.addHospital(new Hospital(3, "Hospital for Special Surgery"));
		wt.addHospital(new Hospital(4, "Mount Sinai"));
		for (Hospital h : wt.getHospitals())
			wt.say(h.toString());
		wt.closeConnection();
	}

	public void say(String s) {
		if (verbose)
			System.out.println("Windtalker: " + s);
	}

	public void addPatient() {

	}

	/**
	 * Adds the given hospital to the database in the hospitals table.
	 * @param h the hospital to be added to the database.
	 */
	public void addHospital(Hospital h) {
		executeStatement("INSERT INTO Hospitals (HOSPITAL_ID, HOSPITAL_NAME) VALUES ("
				+ h.toString() + ");");
	}

	/**
	 * Selects all data in the Hospitals table.
	 * 
	 * @returns an ArrayList of objects of Hospital type.
	 */
	public ArrayList<Hospital> getHospitals() {
		executeSelectStatement("SELECT * FROM Hospitals");
		ArrayList<Hospital> hospitals = new ArrayList<Hospital>();
		try {
			while (rs.next()) {
				hospitals.add(new Hospital(rs.getInt(1), rs.getString(2)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hospitals;
	}

	public void addEMS() {

	}

	public void addEMT() {

	}

	public void addVitals() {

	}
}