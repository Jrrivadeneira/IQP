package ui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.Controller;
import control.MainUIController;

import java.util.HashMap;

public class LoginUI extends JFrame implements UI {

	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 1242665369571041954L;

	private Controller ctrl;
	private JTextField username;
	private JPasswordField password;
	private JButton signIn;

	private HashMap<String, JComponent> components;

	public LoginUI(Controller c) {
		this.setSize(800,600);
		this.setLayout(null);
		this.setTitle("LoginUI");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addController(c);
		
		this.buildUI();
		this.setVisible(true);
	}
	
	public void dispose(){
		say("Closing.");
		if(((control.LoginUIController)ctrl).isValid()){
			MainUIController mc = new MainUIController();
			new MainUI(mc);
		}
		super.dispose();
	}

	public JComponent getComponentByName(String s){
		return components.get(s);
	}
	
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

	public void addController(Controller c) {
		say("adding controller...");
		this.ctrl = c;
		this.addKeyListener(c);
		this.addMouseListener(c);
		say("controller added.");
		this.ctrl.addUI(this);
	}

	public void say(String s) {
		System.out.println("LoginUI: " + s);
	}

	public void focusPassword() {
		password.requestFocus();

	}

	public void buildUI() {
		components = new HashMap<String,JComponent>();
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
