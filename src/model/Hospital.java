package model;

public class Hospital {
	private int id;
	private String name;

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
