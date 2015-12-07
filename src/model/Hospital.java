package model;

public class Hospital {
	private int id;
	private String name;

	public int getID() {
		return this.id;
	}

	public Hospital(int ID, Hospital h) {
		this.id = ID;
		this.name = h.name;
	}

	public Hospital(int ID, String Name) {
		this.id = ID;
		this.name = "'" + Name + "'";
	}

	public static void main(String[] args) {
		new Hospital(0, "Our fair lady of mercy.");
	}

	public String toString() {
		return id + ", " + name;
	}
}
