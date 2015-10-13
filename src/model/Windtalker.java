package model;

import java.sql.*;

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
			
//			wipeout();
//			int c = st.executeUpdate("CREATE TABLE Patients (Name VARCHAR(30),LName VARCHAR(50))");
//			System.out.println("Table have been created.");
//			System.out.println(c + " Row(s) have been affected");
			
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException:\n" + ex.toString());
			ex.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException:\n" + ex.toString());
			wipeout();
			ex.printStackTrace();
		}
	}
	
	public void closeConnection(){
		try{
		con.close();
		}catch(Exception ex){
			
		}
	}

	private void executeStatement(String statement) {
		say("Executing: "+ statement);
		try {
			st.executeUpdate(statement);
		} catch (SQLException ex) {
			say("Execution Failed!");
			ex.printStackTrace();
		}
		say("Succeeded.");
	}
	
	private void executeStatements(String[] statements){
		for (String s : statements)
			executeStatement(s);
	}

	private void executeSelectStatement(String statement){
		say("Executing: "+ statement);
		try {
			rs = st.executeQuery(statement);
		} catch (SQLException ex) {
			say("Execution Failed!");
			ex.printStackTrace();
		}
		say("Succeeded.");
	}
	
	private void wipeout() {
		String statements[] = { 
				"DROP TABLE Patients", 
				"DROP TABLE Hospitals",
				"DROP TABLE EMS", 
				"DROP TABLE EMT", 
				"DROP TABLE Vitals" };
		executeStatements(statements);
	}
	
	public void createDatabase() {
		String statements[] = {
				"CREATE TABLE Patients (PATIENT_ID INT, EMT_ID INT, Region INT, Notes VARCHAR(255))",
				"CREATE TABLE Hospitals (HOSPITAL_ID INT, HOSPITAL_NAME VARCHAR(255))",
				"CREATE TABLE EMS (EMS_ID INT)",
				"CREATE TABLE EMT (EMT_ID INT, EMS_ID INT)",
				"CREATE TABLE Vitals (VITALS_ID INT, PATIENT_ID INT, SPO2 INT, Heartrate INT, Systolic INT, Diastolic INT)" };
		executeStatements(statements);
	}

	public static void main(String[] args) {
		Windtalker wt = new Windtalker();
		wt.wipeout();
		wt.createDatabase();
		wt.addHospital();
		wt.getHospital();
		wt.closeConnection();
	}

	public void say(String s) {
		if(verbose)
		System.out.println("Windtalker: " + s);
	}
	
	public void addPatient(){
		
	}
	
	public void addHospital(){
		executeStatement("INSERT INTO Hospitals (HOSPITAL_ID, HOSPITAL_NAME) VALUES (1,'Colombia');");
	}
	
	public void getHospital(){
		executeSelectStatement("SELECT * FROM Hospitals WHERE HOSPITAL_ID=1");
		int id = 0;
		String name = null;
		try {
			while(rs.next()){
			id = rs.getInt(1);
			name = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String s = id + " " + name;
		System.out.println(s);
	}
	
	public void addEMS(){
		
	}
	
	public void addEMT(){
		
	}
	
	public void addVitals(){
		
	}
}