package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PinDialog extends JDialog implements ActionListener {
	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = -8617040352019344781L;
	private JButton buttonCancel, buttonEnter, buttonOne, buttonTwo,
			buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven,
			buttonEight, buttonNine, buttonZero;
	private String currentPin = "";
	int status = 0;
	private final int size = 150;
	private JTextField pinField = new JTextField();

	/**
	 * Patient Evaluation and intervention
	 */
	public PinDialog() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.buildUI();
		this.setVisible(true);
	}

	private void buildUI() {
		this.setSize(size * 3, size * 5);
		this.setLocationRelativeTo(null);
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

		this.pinField.setPreferredSize(new Dimension(200, this.size));
		this.pinField.setEditable(false);
		this.pinField.setFont(new Font("Arial", Font.PLAIN, 54));
		this.pinField.setText("");
		this.add(this.pinField, BorderLayout.NORTH);
		this.add(pad, BorderLayout.CENTER);

	}

	public String getPin() {
		return this.currentPin;
	}

	public static void main(String[] args) {
		new PinDialog();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.buttonCancel) {
			this.dispose();
			return;
		}
		if (e.getSource() == this.buttonEnter) {
			this.status = 1;
			this.dispose();
			return;
		}

		pinField.setText(pinField.getText() + "*");
		this.currentPin += ((JButton) e.getSource()).getText();
	}

}
