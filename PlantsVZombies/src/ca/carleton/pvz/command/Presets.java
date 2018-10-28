package ca.carleton.pvz.command;

/**
 * A constant class for storing preset console outputs.
 * 
 */
public final class Presets {

	public static final String WELCOME = "Welcome to Plants vs. Zombies (text-based)!\n"
			+ "Type \"help\" if you need help.\n";

	public static final String INVALID = "You entered an invalid command. Type \"help\" for valid commands.\n";

	public static final String HELP = "Your valid command words are:" + Parser.getCommands()
			+ "\nYou can type \"help\" followed by (a space and) a valid command (other than \"help\") to learn how to use the command.\n";

}
