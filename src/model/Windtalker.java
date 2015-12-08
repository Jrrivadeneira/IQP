package model;

import java.sql.*;
import java.util.ArrayList;

import components.JaxList;

/**
 * This is our database interaction object used to store and retrieve data from
 * the database. To accomplish this it uses an external library called
 * mysql-connector-java for a database driver for the mysql database.
 */
public class Windtalker implements Model {
	/**
	 * Written by Jack Rivadeneira
	 */

	private String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private String MYSQL_URL = "jdbc:mysql://localhost:3306/SCHOOL";
	private Connection con;
	private Statement st;
	private boolean verbose = true;
	private ResultSet rs;
	private EMT user;

	public void setUser(EMT e) {

	}

	/**
	 * Constructor for the windtalker object.
	 */
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

	/**
	 * Closes the connection to the database.
	 */
	private void closeConnection() {
		try {
			con.close();
		} catch (Exception ex) {

		}
	}

	/**
	 * Executes a given sql string.
	 * 
	 * @param statement
	 *            to be executed.
	 */
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

	/**
	 * Executes an array of statements.
	 * 
	 * @param statements
	 *            The array of statements to be executed
	 */
	private void executeStatements(String[] statements) {
		for (String s : statements)
			executeStatement(s);
	}

	/**
	 * Executes a given select statement.
	 * 
	 * @param statement
	 *            to be executed
	 */
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

	/**
	 * Deletes the contents of the database.
	 */
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
				"CREATE TABLE Hospitals (HOSPITAL_ID INT, HOSPITAL_NAME VARCHAR(255), HOSPITAL_ADDRESS VARCHAR(255), HOSPITAL_PHONE VARCHAR(255))",
				"CREATE TABLE EMS (EMS_ID INT)",
				"CREATE TABLE EMT (EMT_ID VARCHAR(255), EMS_ID INT, EMT_NAME VARCHAR(255), EMT_USERNAME VARCHAR(255), EMT_PASSWORD VARBINARY(500), EMT_EMAIL VARCHAR(255))",
				"CREATE TABLE Vitals (VITALS_ID INT, PATIENT_ID INT, SPO2 INT, Heartrate INT, Systolic INT, Diastolic INT)" };
		executeStatements(statements);
	}

	public void mockDB() {
		this.wipeout();
		this.createTables();
		this.addHospital(new Hospital(0, "Colombia Presbyterian"));
		this.addNewHospital(new Hospital(0, "Bellevue"));
		this.addNewHospital(new Hospital(0, "Hospital for Special Surgery"));
		this.addNewHospital(new Hospital(0, "Mount Sinai"));
		this.addEMT(new EMT("79450210601", 0, "Jack_Rivadeneira", "Jack",
				"password", "Me@example.com"));

	}

	/**
	 * Main function for database testing. To be deleted.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Windtalker wt = new Windtalker();
		wt.mockDB();
		for (Hospital h : wt.getHospitals())
			wt.say(h.toString());
		EMT e = wt.getEMT("Jack", "password");
		System.out.println(e.validate("password"));
		for (EMT emt : wt.getEMTs()) {
			wt.say(emt.toString());
		}
		wt.closeConnection();
	}

	/**
	 * Standard output for this class
	 */
	public void say(String s) {
		if (verbose)
			System.out.println("Windtalker: " + s);
	}

	/**
	 * Will add a given patient to the database.
	 */
	public void addPatient() {

	}

	public void addNewHospital(Hospital h) {

		ArrayList<Hospital> l = getHospitals();
		if (l.size() == 0) {
			this.addHospital(h);
		}
		Hospital lastHospital = l.get(l.size() - 1);
		this.addHospital(new Hospital(lastHospital.getID() + 1, h));

	}

	/**
	 * Adds the given hospital to the database in the hospitals table.
	 * 
	 * @param h
	 *            the hospital to be added to the database.
	 */
	private void addHospital(Hospital h) {
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

	/**
	 * @returns Returns a list of all emts in the database
	 */
	public ArrayList<EMT> getEMTs() {
		executeSelectStatement("SELECT * FROM EMT");
		ArrayList<EMT> emts = new ArrayList<EMT>();
		try {
			while (rs.next()) {
				emts.add(EMT.generateEMT(rs.getString(1), rs.getInt(2),
						rs.getString(3), rs.getString(4), rs.getBytes(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emts;
	}

	/**
	 * Adds the given EMS unit to the database
	 * 
	 * @param ems
	 *            the EMS unit to be added to the database
	 */
	public void addEMS(EMS ems) {
		this.executeStatement("INSERT INTO EMS (EMS_ID) VALUES ("
				+ ems.toString() + ");");
	}

	/**
	 * Adds the given emt object to the database.
	 * 
	 * @param e
	 *            - The object to be added to the database
	 */
	public void addEMT(EMT e) {
		try {
			PreparedStatement stmt = this.con
					.prepareStatement("INSERT INTO EMT (EMT_ID, EMS_ID, EMT_NAME, EMT_USERNAME, EMT_PASSWORD) VALUES ("
							+ e.toString() + ", ?" + ");");
			stmt.setBytes(1, e.getPassword());
			stmt.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Validates the login for an EMT with a given username and password.
	 * 
	 * @param username
	 *            - username of the EMT
	 * @param password
	 *            - password of the EMT
	 * @return Returns true if the validation was successful. If it failed then
	 *         the login is aborted.
	 */
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
			mt = EMT.generateEMT(null, 0, null, null, passwordString, null);
			say("Done.");
			if (mt.validate(password)) {
				return true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		say("Failed to validate login.");
		return false;
	}

	/**
	 * This is for login. The username and password should be passed to this in
	 * order to validate the login. If the login is valid then it will sign in
	 * that EMT.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public EMT getEMT(String username, String password) {
		say("Getting user");
		if (!validateLogin(username, password)) {
			say("Could not validate username password combination.");
			return null;
		}
		EMT mt = null;
		this.executeSelectStatement("SELECT * FROM EMT WHERE EMT_USERNAME="
				+ "'" + username + "';");
		mt = extractEMT();
		say("EMT Successfully generated and ready for validation.");
		this.user = mt;
		return mt;
	}

	/**
	 * Extracts an EMT after a select statement was called.
	 * 
	 * @returns The last EMT that fits this criteria.
	 */
	private EMT extractEMT() {
		try {
			while (rs.next()) {
				String ID = rs.getString(1);
				int EMS_UNIT_ID = rs.getInt(2);
				String Name = rs.getString(3);
				String Username = rs.getString(4);
				byte[] Password = rs.getBytes(5);
				String Email = rs.getString(6);
				return EMT.generateEMT(ID, EMS_UNIT_ID, Name, Username,
						Password, Email);
			}
		} catch (Exception ex) {

		}
		return null;
	}

	private EMT getEMTByID(String id) {
		executeSelectStatement("SELECT * FROM EMT WHERE EMT_ID = " + id + ";");

		return extractEMT();
	}

	/**
	 * Adds a vital sign reading to the database.
	 */
	public void addVitals(Vitals v) {
		executeStatement(v.getInsertStatement());
	}

	public void update(JaxList listOfHospitals) {
		ArrayList<Hospital> hospitals = this.getHospitals();
		for (Hospital h : hospitals) {
			listOfHospitals.addToList(h.toString());
		}
	}

	private void removeEMT(String username, String password) {
		if (this.validateLogin(username, password)) {
			this.executeStatement("DELETE FROM EMT WHERE EMT_USERNAME='"
					+ username + "'");
		}
	}

	/**
	 * Changes the password of the currently signed in EMT
	 * 
	 * @param results
	 */
	public void changePassword(char[][] results) {
		say("Changing Password...");
		this.getEMT(this.user.getUsername(), new String(results[0]));
		this.removeEMT(this.user.getUsername(), new String(results[0]));
		this.user = new EMT(this.user.getID(), this.user.getEMSUnitID(),
				this.user.getName(), this.user.getUsername(), new String(
						results[1]), this.user.getEmail());
		this.addEMT(this.user);

		this.say("Done.");
	}

	/**
	 * signs in the user using their ID number.
	 * @param ID
	 * @returns true if the signin succeeded.
	 */
	public boolean signInWithID(String ID) {
		say("Signing in with ID: " + ID);
		this.user = this.getEMTByID(ID);
		return this.user != null;
	}
}