package model;

import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EMT {
	/**
	 * EMT class used to store data about the users. Written by Jack
	 * Rivadeneira.
	 */
	private String id;
	private int EMSUnitID;
	private String name;
	private String username;
	private byte[] password;
	private String email;
	private final String validationString = "I have a dream that one day every valley shall be exalted, and every hill and mountain shall be made low, the rough places will be made plain, and the crooked places will be made straight; and the glory of the Lord shall be revealed and all flesh shall see it together.";

	/**
	 * Generates a totally empty EMT
	 */
	private EMT() {

	}

	/**
	 * Constructor for the EMT. Used to create an EMT that will be saved to the
	 * database with the given parameters. The password itself is not stored in
	 * the database. Instead, the password is used to encrypt a validation
	 * String that must be decrypted to sign in.
	 * 
	 * @param ID
	 * @param EMSUNITID
	 * @param Name
	 * @param Username
	 * @param Password
	 * @param Email
	 */
	public EMT(String ID, int EMSUNITID, String Name, String Username,
			String Password, String Email) {
		this.id = ID;
		this.EMSUnitID = EMSUNITID;
		this.name = Name;
		this.username = Username;
		this.setPassword(Password);
		this.email = Email;
	}

	/**
	 * Creates an EMT as it would be loaded from a database.
	 * 
	 * @param ID
	 *            - sets the ID of the emt
	 * @param EMSUNITID
	 *            - sets the EMSUnitID of the emt
	 * @param Name
	 *            - sets the name of the EMT
	 * @param Username
	 *            - sets the username of the EMT
	 * @param Password
	 *            - sets the byte array of the emt
	 * @param Email
	 *            - sets the Email of the EMT
	 * @returns an EMT with the given data.
	 */
	protected static EMT generateEMT(String ID, int EMSUNITID, String Name,
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

	/**
	 * Does not actually set the password! it takes a password p and encrypts
	 * the validation string for login.
	 * 
	 * @param p
	 *            password to be used to encrypt
	 */
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
			System.out.println(this.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * attempts to decrypt the validation string using the given password in
	 * order to validate a login.
	 * 
	 * @param password
	 *            - the password used to decrypt
	 * @returns true if the validation succeeded.
	 */
	protected boolean validate(String password) {
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
			// ex.printStackTrace();
		}
		return false;
	}

	/**
	 * generates a random string to add security to the hash
	 * 
	 * @returns a random string.
	 */
	private static String randomString() {
		char c[] = new char[64];
		int i = 0;
		Random r = new Random();
		while (i < c.length) {
			c[i++] = (char) (r.nextInt(100) + 65);
		}

		return new String(c);
	}

	/**
	 * getter for ID
	 * 
	 * @returns the ID of this EMT
	 */
	public String getID() {
		return this.id;
	}

	/**
	 * getter for EMSUnitID
	 * 
	 * @returns the EMSUnitID of this EMT
	 */
	public int getEMSUnitID() {
		return this.EMSUnitID;
	}

	/**
	 * getter for Name
	 * 
	 * @returns the name of this EMT
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * getter for username
	 * 
	 * @returns the ID username this EMT
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * getter for password
	 * 
	 * @returns the encrypted validation string of this EMT
	 */
	public byte[] getPassword() {
		return this.password;
	}

	/**
	 * getter for Email
	 * 
	 * @returns the Email of this EMT
	 */
	public String getEmail() {
		return this.email;
	}

	public String toString() {
		return this.id + ", " + this.EMSUnitID + ", '" + this.name + "', '"
				+ this.username + "'";
		// + ", " + this.email;
	}

}
