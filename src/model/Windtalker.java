package model;

import java.sql.*;

public class Windtalker implements Model{
	private String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private String MYSQL_URL = "jdbc:mysql://localhost:3306/SCHOOL";

	private Connection con;
	private Statement st;

	// private ResultSet rs;

	public Windtalker() {
		try {
			Class.forName(MYSQL_DRIVER);
			System.out.println("Class Loaded....");
			con = DriverManager.getConnection(MYSQL_URL, "root", "databean");
			System.out.println("Connected to the database....");
			st = con.createStatement();
			int c = st.executeUpdate("CREATE TABLE Patients (Name VARCHAR(30),LName VARCHAR(50))");
			System.out.println("Table have been created.");
			System.out.println(c + " Row(s) have been affected");
			con.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException:\n" + ex.toString());
			ex.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException:\n" + ex.toString());
			wipeout();
			ex.printStackTrace();
		}
	}

	private void executeStatement(String statement) {
		try {
			st.executeUpdate(statement);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void wipeout() {
		String statements[] = { 
				"DROP TABLE Patients", 
				"DROP TABLE Hospital",
				"DROP TABLE EMS", 
				"DROP TABLE EMT", 
				"DROP TABLE Vitals"
				};
		for (String s : statements)
			executeStatement(s);
	}

	public void createDatabase() {
		String statements[] = { 
				"CREATE TABLE Patients (PATIENT_ID INT, EMT_ID INT, Region INT, Notes VARCHAR(255)",
				"CREATE TABLE Hospital (HOSPITAL_ID INT)",
				"CREATE TABLE EMS (EMS_ID INT)",
				"CREATE TABLE EMT (EMT_ID INT, EMS_ID INT)",
				"CREATE TABLE Vitals (VITALS_ID INT, PATIENT_ID INT, SPO2 INT, Heartrate INT, Systolic INT, Diastolic INT)"
				};
		for (String s : statements)
			executeStatement(s);
	}

	public static void main(String[] args) {
		new Windtalker();
	}

	@Override
	public void say(String s) {
		// TODO Auto-generated method stub
		
	}
}