package ui;

import java.awt.BorderLayout;

import javax.swing.*;

import control.Controller;

public class SettingsUI extends FrameUI {

	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = -6221117603324451662L;
	JButton changePassword, uiTweaks, addHospital, back;

	public SettingsUI(Controller c) {
		super(c);
	}

	public void dispose() {
		this.ctrl.dispose();
		super.dispose();
	}

	public void buildUI() {
		this.changePassword = new JButton("change password");
		this.uiTweaks = new JButton("ui tweaks");
		this.addHospital = new JButton("add hospital");
		this.back = new JButton("Back");
		this.back.setName("back");
		this.back.addActionListener(this.ctrl);
		this.add(back, BorderLayout.WEST);
	}

	public void showChangePasswordDialog() {

	}

}
