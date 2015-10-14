package model;

public class Hospital {
	private int id;
	private String name;
	
	public Hospital(int ID, String Name){
		this.id = ID;
		this.name = "'"+Name+"'";
	}
	
	public String toString(){
		return id + ", " + name;
	}
}
