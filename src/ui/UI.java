package ui;

import javax.swing.JComponent;

import control.Controller;

public interface UI {
	/**
	 * Sets the Controller object for this UI to the given Controller object.
	 * 
	 * @param c
	 *            Controller- The controller object for this UI.
	 */
	public abstract void addController(Controller c);

	public abstract void say(String s);

	/**
	 * This is where the UI is to be created. All components are created and
	 * added here.
	 */
	public abstract void buildUI();

	/**
	 * Returns a component with the same name as the given String.
	 * 
	 * @param s
	 *            The name of the component to be found.
	 */
	public abstract JComponent getComponentByName(String s);

	public abstract void dispose();
}
