package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
public class EventsUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	JButton[] buttons = { new JButton("Oxygen"), new JButton("Epinepherine"),
			new JButton("IV"), new JButton("Morphine"), new JButton("Meds"),
			new JButton("Button"), new JButton("Button"),
			new JButton("Button"), new JButton("Button"),
			new JButton("Button"), new JButton("Button"),
			new JButton("Button"), new JButton("Button"),
			new JButton("Button"), new JButton("Button"),
			new JButton("Button"), };
	private static final long serialVersionUID = -874224048818061454L;

	public EventsUI() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(4, 4));
		for (JButton b : buttons) {
			b.addActionListener(this);
			add(b);
		}
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		Calendar c = Calendar.getInstance();
		System.out.println(b.getText() + "\t\t\t" + System.currentTimeMillis());
		
	}

	public static void main(String[] args) {
		new EventsUI();
	}
}
