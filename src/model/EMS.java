package model;

public class EMS {
	private int EMS_UNIT_ID;
	public EMS(int EUI) {
		this.EMS_UNIT_ID = EUI;
	}

	public int getID() {
		return this.EMS_UNIT_ID;
	}

	public String toString() {
		return "" + this.EMS_UNIT_ID;
	}
}
