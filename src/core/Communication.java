package core;

public abstract class Communication {
	/**
	 * Prints the given string with the classname as a prefix. Will only print
	 * if verbose output is enabled.
	 * 
	 * @param s
	 *            String- The string to be printed;
	 */
	public void say(String s) {
		if (core.Major.verboseOutputEnabled)
			System.out.println(this.getClass().getName().split("\\.")[1] + ": " + s);
	}
}
