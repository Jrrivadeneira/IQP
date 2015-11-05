package model;

public class Vitals {
	protected int id, patientID, spo2, heartRate, bloodPressureSystolic,
			bloodPressureDiastolic;

	// "CREATE TABLE Vitals (VITALS_ID INT, PATIENT_ID INT, SPO2 INT, Heartrate INT, Systolic INT, Diastolic INT)"
	public String toString() {
		return id + "," + this.patientID + "," + this.spo2 + ","
				+ this.heartRate + "," + this.bloodPressureSystolic + ","
				+ this.bloodPressureDiastolic;
	}

	protected String getInsertStatement() {
		return "INSERT INTO EMS (VITALS_ID, PATIENT_ID, SPO2, Heartrate, Systolic, Diastolic) VALUES ("
				+ this.toString() + ");";
	}
}
