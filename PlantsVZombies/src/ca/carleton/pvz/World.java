package ca.carleton.pvz;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

import ca.carleton.pvz.level.Level;

/**
 * A class to store the levels in the game.
 *
 */
public class World implements Serializable {

	private static final long serialVersionUID = -3482109380250140865L;
	private Stack<Level> levels;

	/**
	 * Constructs a new game world.
	 */
	public World() {
		levels = new Stack<>();
	}

	/**
	 * Adds a level to the stack.
	 *
	 * @param level The level to be added to the stack.
	 */
	public void addLevel(Level level) {
		if (level != null) {
			levels.add(level);
		}
	}

	/**
	 * Gets the current level.
	 *
	 * @return The current level.
	 */
	public Level getCurrentLevel() {
		return levels.peek();
	}

	/**
	 * Returns whether the current level is beat.
	 *
	 * @return true if the current level is beat, false otherwise.
	 */
	public boolean isCurrentLevelBeat() {
		return levels.peek().isBeat();
	}

	/**
	 * Pops the current level and gets the next level.
	 *
	 * @return The next level.
	 */
	public Level nextLevel() {
		levels.pop();
		if (levels.empty()) { // failsafe: prevent EmptyStackException
			return null;
		}
		return levels.peek();
	}

	/**
	 * Returns a deep copy of the given world, or null if the given world cannot
	 * be serialized.
	 */
	public static World copy(World orig) {
		World copy = null;
		try {
			// write the given world out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(orig);
			out.flush();
			out.close();
			// make an input stream from the byte array and
			// read a copy of the given world back in
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			copy = (World) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return copy;
	}

}
