package ui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import control.Controller;
import java.util.HashMap;

public class LoginUI extends FrameUI {

	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 1242665369571041954L;

	private JTextField username;
	private JPasswordField password;
	private JButton signIn;

	/**
	 * Constructor for the LoginUI
	 * 
	 * @param c
	 *            the controller to be used for this ui.
	 */
	public LoginUI(Controller c) {
		super(c);
		this.setTitle("LoginUI");
	}

	/**
	 * Adds a component to the ui properly.
	 * 
	 * @param c
	 *            The component to be added.
	 * @param name
	 *            The name of the component to be added.
	 */
	private void addComponent(JButton c, String name) {
		say("Adding button: " + name);
		c.setName(name);
		say("Name set.");
		say("Adding controller...");
		c.addActionListener(ctrl);
		say("Controller added.");
		components.put(name, c);
		say("Added to map.");
		add(c);
		say("Button added.");
	}

	/**
	 * Adds a component to the ui properly.
	 * 
	 * @param c
	 *            The component to be added.
	 * @param name
	 *            The name of the component to be added.
	 */
	private void addComponent(JTextField c, String name) {
		say("Adding text field: " + name);
		c.setName(name);
		say("Name set.");
		say("Adding controller...");
		c.addActionListener(ctrl);
		say("Controller added.");
		components.put(name, c);
		say("Added to map.");
		add(c);
		say("Text field added.");
	}

	/**
	 * Sets user focus to the password field.
	 */
	public void focusPassword() {
		say("Setting focus to password field...");
		password.requestFocus();
		say("Done!");
	}

	/**
	 * Returns true if the username is suspected to be an ID numbers
	 */
	public boolean isIDNumber() {
		return "1234567890".contains(this.username.getText().charAt(0) + "");
	}

	public String getUsername() {
		return this.username.getText();
	}

	public void buildUI() {
		this.setLayout(null);
		components = new HashMap<String, JComponent>();
		JLabel userlabel = new JLabel("Username");
		userlabel.setSize(100, 32);
		userlabel.setLocation(220, 400);
		this.add(userlabel);
		JLabel passlabel = new JLabel("Password");
		passlabel.setSize(100, 32);
		passlabel.setLocation(220, 500);
		this.add(passlabel);
		username = new JTextField();
		username.setSize(300, 32);
		username.setLocation(300, 400);
		this.addComponent(username, "username");
		password = new JPasswordField();
		password.setSize(300, 32);
		password.setLocation(300, 500);
		this.addComponent(password, "password");
		this.signIn = new JButton("Sign In");
		this.signIn.setBounds(300, 600, 300, 50);
		this.signIn.setText("Sign In");
		this.addComponent(signIn, "signin");
	}
}
