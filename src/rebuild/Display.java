package rebuild;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import components.JaxList;

public class Display extends JFrame implements ActionListener {

	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = 7070175919870901366L;
	private JPanel startScreen, runNumberScreen, actionScreen, current;
	private JButton start, buttonOne, buttonTwo, buttonThree, buttonFour,
			buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine,
			buttonCancel, buttonZero, buttonEnter, endRun;
	private JButton[] actionButtons = { new JButton("Oxygen"),
			new JButton("Manual CPR"), new JButton("Lucas CPR"),
			new JButton("IV Hookup"), new JButton("Fluids Administered"),
			new JButton("Aspirin"), new JButton("Oral Meds Administered"),
			new JButton("IV Meds Administered"), new JButton("Epinephrine"),
			new JButton("Backboard"), new JButton("Spine Immobilized"),
			new JButton("Morphine"), new JButton("AED"),
			new JButton("Nebulizer"), new JButton("Button"),
			new JButton("Button") };

	private JTextField pinField;
	private int gen = 0;

	private JaxList actionList = new JaxList();
	private String runNumber = "";

	public Display() {
		this.startScreen = new JPanel();
		this.startScreen.setLayout(new BorderLayout());

		start = new JButton("START");
		start.addActionListener(this);
		this.startScreen.add(start);

		this.runNumberScreen = new JPanel();
		this.runNumberScreen.setLayout(new BorderLayout());
		this.buildRunNumberScreen();
		this.actionScreen = new JPanel();
		this.buildActionScreen();
		this.current = this.startScreen;

		this.add(current);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * builds the action screen where an EMT can hit specific action buttons
	 */
	private void buildActionScreen() {
		this.actionScreen.setLayout(new BorderLayout());
		JPanel LeftSide = new JPanel();
		LeftSide.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();// button panel
		buttonPanel.setLayout(new GridLayout(4, 4));
		for (JButton eachButton : this.actionButtons) {
			eachButton
					.addActionListener(ext -> {
						long time = System.currentTimeMillis();
						int seconds = (int) ((time / 1000) % 60);
						int minutes = (int) ((time / 1000) / 60) % 60;
						int hours = (int) ((time) / 1000 / 3600) % 24;
						String tim = hours + ":" + minutes + ":" + seconds
								+ "_UTC";
						String text = ((JButton) ext.getSource()).getText()
								+ "_" + tim;
						actionList.addToList(text);

					});
			buttonPanel.add(eachButton);
		}
		LeftSide.add(buttonPanel, BorderLayout.CENTER);

		JPanel typeArea = new JPanel();
		typeArea.setLayout(new GridLayout(2, 5));
		typeArea.add(new JButton(this.runNumber));
		typeArea.add(new JButton("Age"));
		typeArea.add(new JButton("Sex"));
		typeArea.add(new JButton("Weight"));
		typeArea.add(new JButton("Name"));
		typeArea.add(new JButton("Button"));
		typeArea.add(new JButton("Button"));
		typeArea.add(new JButton("Button"));
		typeArea.add(new JButton("Button"));
		typeArea.add(new JButton("Button"));

		typeArea.setPreferredSize(new Dimension(500, 300));
		LeftSide.add(typeArea, BorderLayout.NORTH);

		JPanel rightSide = new JPanel();// this will hold a list and a button
		rightSide.setLayout(new BorderLayout());
		this.endRun = new JButton("End Run");
		this.endRun.setPreferredSize(new Dimension(400, 370));
		this.endRun.addActionListener(this);
		rightSide.add(actionList, BorderLayout.CENTER);
		rightSide.add(endRun, BorderLayout.SOUTH);
		this.actionScreen.add(rightSide, BorderLayout.EAST);
		this.actionScreen.add(LeftSide, BorderLayout.CENTER);
	}

	/**
	 * Invoked when the user hits the start button.
	 */
	private void startButton() {
		System.out.println("HERE!");
		this.current.setVisible(false);
		this.current = this.runNumberScreen;
		this.current.setVisible(true);
		this.add(current);
		runNumber = "";
		gen = 1;
		this.pinField.setText("Enter Run Number");
	}

	public static void main(String[] args) {
		new Display();
	}

	/**
	 * builds the enter run number screen where the EMT enters the run number
	 */
	private void buildRunNumberScreen() {
		runNumber = "";
		JPanel pad = new JPanel();
		pad.setLayout(new GridLayout(4, 3));
		this.setLayout(new BorderLayout());

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.addActionListener(this);

		this.buttonEnter = new JButton("Enter");
		this.buttonEnter.addActionListener(this);

		this.buttonZero = new JButton("0");
		this.buttonZero.addActionListener(this);

		this.buttonOne = new JButton("1");
		this.buttonOne.addActionListener(this);

		this.buttonTwo = new JButton("2");
		this.buttonTwo.addActionListener(this);

		this.buttonThree = new JButton("3");
		this.buttonThree.addActionListener(this);

		this.buttonFour = new JButton("4");
		this.buttonFour.addActionListener(this);

		this.buttonFive = new JButton("5");
		this.buttonFive.addActionListener(this);

		this.buttonSix = new JButton("6");
		this.buttonSix.addActionListener(this);

		this.buttonSeven = new JButton("7");
		this.buttonSeven.addActionListener(this);

		this.buttonEight = new JButton("8");
		this.buttonEight.addActionListener(this);

		this.buttonNine = new JButton("9");
		this.buttonNine.addActionListener(this);

		pad.add(this.buttonOne);
		pad.add(this.buttonTwo);
		pad.add(this.buttonThree);
		pad.add(this.buttonFour);
		pad.add(this.buttonFive);
		pad.add(this.buttonSix);
		pad.add(this.buttonSeven);
		pad.add(this.buttonEight);
		pad.add(this.buttonNine);
		pad.add(this.buttonCancel);
		pad.add(this.buttonZero);
		pad.add(this.buttonEnter);
		this.pinField = new JTextField();
		this.pinField.setEditable(false);
		this.pinField.setBackground(Color.WHITE);
		this.pinField.setFont(new Font("Arial", Font.PLAIN, 54));
		this.pinField.setText("Pin Number");
		this.runNumberScreen.add(this.pinField, BorderLayout.NORTH);
		this.runNumberScreen.add(pad, BorderLayout.CENTER);

	}

	/**
	 * is called when the enter button is hit on the enter run number screen
	 */
	private void enterButton() {
		gen = 2;
		System.out.println("ENTER BUTTON HIT!");
		this.current.setVisible(false);
		this.current = this.actionScreen;
		this.current.setVisible(true);
		this.add(this.current);

	}

	/**
	 * Resets the variables and returns you to the home screen.
	 */
	private void goToHomeScreen() {
		gen = 0;
		actionList.clear();
		this.setTitle("Home");
		this.current.setVisible(false);
		this.current = this.startScreen;
		this.current.setVisible(true);
		this.add(current);
	}

	/**
	 * Saves the data entered by an EMT on the action screen to a file named
	 * after the run number
	 */
	private void saveToFile() {
		System.out.println("Saving...");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					this.runNumber + ".txt"));
			for (Object s : this.actionList.getAll()) {
				bw.write(s.toString());
				bw.newLine();
			}
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done.");
	}

	/**
	 * Is called when the user hits the end run button on the Action Screen
	 */
	private void endRunButton() {
		saveToFile();
		goToHomeScreen();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		String text = (((JButton) o).getText());
		if (gen == 1) {
			if (o.equals(this.buttonEnter)) {
				this.enterButton();
			} else if (o.equals(this.buttonCancel)) {
				this.goToHomeScreen();
			} else {
				this.runNumber += text;
				this.pinField.setText(this.runNumber);
			}
		}
		if (o.equals(this.endRun)) {
			endRunButton();
		}
		if (o.equals(this.start)) {
			startButton();
		}
	}
}