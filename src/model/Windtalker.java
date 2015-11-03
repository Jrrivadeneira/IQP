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
				"CREATE TABLE EMT (EMT_ID INT, EMS_ID INT, EMT_NAME VARCHAR(255), EMT_USERNAME VARCHAR(255), EMT_PASSWORD VARBINARY(500))",
				"CREATE TABLE Vitals (VITALS_ID INT, PATIENT_ID INT, SPO2 INT, Heartrate INT, Systolic INT, Diastolic INT)" };
		executeStatements(statements);
	}

	/**
	 * Main function for database testing. To be deleted.
	 * 
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
		wt.addEMT(new EMT(0, 0, "Jack_Rivadeneira", "JJack", "Databean",
				"Me@example.com"));

		for (Hospital h : wt.getHospitals())
			wt.say(h.toString());
		EMT e = wt.getEMT("JJack", "Databean");
		System.out.println(e.validate("Databean"));
		for (EMT emt : wt.getEMTs()) {
			wt.say(emt.toString());
		}
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
	 * 
	 * @param h
	 *            the hospital to be added to the database.
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

	public ArrayList<EMT> getEMTs() {
		executeSelectStatement("SELECT * FROM EMT");
		ArrayList<EMT> emts = new ArrayList<EMT>();
		try {
			while (rs.next()) {
				emts.add(EMT.generateEMT(rs.getInt(1), rs.getInt(2),
						rs.getString(3), rs.getString(4), rs.getBytes(5), null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emts;
	}

	public void addEMS(EMS ems) {

	}

	public void addEMT(EMT e) {
		// this.executeStatement("INSERT INTO EMT (EMT_ID, EMS_ID, EMT_NAME, EMT_USERNAME, EMT_PASSWORD) VALUES ("
		// + e.toString() + ");");
		try {
			PreparedStatement stmt = this.con
					.prepareStatement("INSERT INTO EMT (EMT_ID, EMS_ID, EMT_NAME, EMT_USERNAME, EMT_PASSWORD) VALUES ("
							+ e.toString() + ", ?" + ");");
			stmt.setBytes(1, e.getPassword());
			stmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public boolean validateLogin(String username, String password) {
		say("Validating Login");
		EMT mt = null;
		this.executeSelectStatement("SELECT EMT_PASSWORD FROM EMT WHERE EMT_USERNAME="
				+ "'" + username + "';");
		try {
			byte[] passwordString = null;
			while (rs.next())
				passwordString = rs.getBytes(1);
			System.out.println(new String(passwordString).length());
			mt = EMT.generateEMT(0, 0, null, null, passwordString, null);
			say("Validated.");
			return mt.validate(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		say("Failed to validate login.");
		return false;
	}

	public EMT getEMT(String username, String password) {
		if (!validateLogin(username, password)) {
			say("Could not validate username password combination");
			return null;
		}
		EMT mt = null;
		this.executeSelectStatement("SELECT * FROM EMT WHERE EMT_USERNAME="
				+ "'" + username + "';");
		try {
			while (rs.next()) {
				int ID = rs.getInt(1); // id
				int EMSUNITID = rs.getInt(2); // emsunitid
				String Name = rs.getString(3); // name
				String Username = rs.getString(4); // username
				byte[] Password = rs.getBytes(5); // password
				// Email=rs.getString(6); // Email
				String Email = null;
				mt = EMT.generateEMT(ID, EMSUNITID, Name, Username, Password,
						Email);
				System.out.println(rs.getBytes(5).length);
				say("EMT Successfully generated and ready for validation.");
			}
		} catch (SQLException e) {
			System.out.println("Failed!");
			e.printStackTrace();
		}
		return mt;
	}

	public void addVitals() {

	}
}