package model;

import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EMT {
	private int id;
	private int EMSUnitID;
	private String name;
	private String username;
	private byte[] password;
	private String email;
	private final String validationString = "I have a dream that one day every valley shall be exalted, and every hill and mountain shall be made low, the rough places will be made plain, and the crooked places will be made straight; and the glory of the Lord shall be revealed and all flesh shall see it together.";

	private EMT() {

	}

	public EMT(int ID, int EMSUNITID, String Name, String Username,
			String Password, String Email) {
		this.id = ID;
		this.EMSUnitID = EMSUNITID;
		this.name = Name;
		this.username = Username;
		this.setPassword(Password);
		this.email = Email;
	}

	public static EMT generateEMT(int ID, int EMSUNITID, String Name,
			String Username, byte[] Password, String Email) {
		EMT e = new EMT();
		e.id = ID;
		e.EMSUnitID = EMSUNITID;
		e.name = Name;
		e.username = Username;
		e.password = Password;
		e.email = Email;

		return e;
	}

	private void setPassword(String p) {
		char pa = 'A';
		while (p.length() != 16) {
			p += pa;
			pa++;
		}
		try {
			this.password = (randomString() + this.validationString).getBytes();
			SecretKeySpec sec = new SecretKeySpec(p.getBytes(), "AES");
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, sec);
			this.password = c.doFinal(this.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(this.password.length);
	}

	public boolean validate(String password) {
		char c = 'A';
		while (password.length() < 16) {
			password += c;
			c++;
		}
		byte[] key = password.getBytes();
		byte[] encrypted = this.password;
		try {
			Cipher ci = Cipher.getInstance("AES");
			SecretKeySpec spec = new SecretKeySpec(key, "AES");
			ci.init(Cipher.DECRYPT_MODE, spec);
			byte[] validData = ci.doFinal(encrypted);
			String data = new String(validData);
			// System.out.println(data);
			return data.substring(64).equals(this.validationString);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		// System.out.println(randomString());
		// EMT e = new EMT(1, 1, "Jack", "psychomadeye", "Databean",
		// "me@example.com");
		// System.out.println(new String(e.password));
	}

	private static String randomString() {
		char c[] = new char[64];
		int i = 0;
		Random r = new Random();
		while (i < c.length) {
			c[i++] = (char) (r.nextInt(100) + 65);
		}

		return new String(c);
	}

	public int getID() {
		return this.id;
	}

	public int getEMSUnitID() {
		return this.EMSUnitID;
	}

	public String getName() {
		return this.name;
	}

	public String getUsername() {
		return this.username;
	}

	public byte[] getPassword() {
		return this.password;
	}

	public String getEmail() {
		return this.email;
	}

	public String toString() {
		return this.id + ", " + this.EMSUnitID + ", '" + this.name + "', '"
				+ this.username + "'";
		// + ", " + this.email;
	}

}
