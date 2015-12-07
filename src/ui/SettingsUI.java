package ui;

import java.awt.GridLayout;

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
		this.setTitle("SettingsUI");
		GridLayout layout = new GridLayout(2, 4);

		layout.setHgap(core.Configuration.uiSpacing);
		layout.setVgap(core.Configuration.uiSpacing);

		this.setLayout(layout);

		this.changePassword = new JButton("Change Password");
		this.changePassword.setName("change password");
		this.changePassword.addActionListener(this.ctrl);
		this.changePassword.setFont(core.Configuration.buttonFont);
		this.add(this.changePassword);

		this.uiTweaks = new JButton("UI Tweaks");
		this.uiTweaks.setName("ui tweaks");
		this.uiTweaks.addActionListener(this.ctrl);
		this.uiTweaks.setFont(core.Configuration.buttonFont);
		this.add(this.uiTweaks);

		this.addHospital = new JButton("Add Hospital");
		this.addHospital.setName("add hospital");
		this.addHospital.addActionListener(this.ctrl);
		this.addHospital.setFont(core.Configuration.buttonFont);
		this.add(this.addHospital);

		this.back = new JButton("Back");
		this.back.setName("back");
		this.back.addActionListener(this.ctrl);
		this.back.setFont(core.Configuration.buttonFont);
		this.add(back);
	}

}
