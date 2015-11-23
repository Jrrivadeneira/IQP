package model;

public class Hospital {
	private int id;
	private String name;

	public Hospital(int ID, String Name) {
		this.id = ID;
		this.name = "'" + Name + "'";
		EMS E = new EMS(3);
//		E.IM_PROTECTED = 3;
		System.out.println(E.IM_PROTECTED);
	}
	
	public static void main(String[] args){
		new Hospital(0,"Our fair lady of mercy.");
	}

	public String toString() {
		return id + ", " + name;
	}
}
