package ui;

import javax.swing.JComponent;

import control.Controller;

public interface UI {
	public abstract void addController(Controller c);
	public abstract void say(String s);
	public abstract void buildUI();
	public abstract JComponent getComponentByName(String s);
	public abstract void dispose();
}
