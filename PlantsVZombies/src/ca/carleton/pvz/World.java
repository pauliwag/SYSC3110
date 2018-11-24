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
	 * @param level
	 *            The level to be added to the stack.
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
	 * Sets the current level to that given.
	 *
	 * @param level
	 *            The new active level.
	 */
	public void updateCurrentLevel(Level level) {
		levels.set(0, level);
	}

	/**
	 * Returns a copy of the object, or null if the object cannot be serialized.
	 */
	public static World copy(World orig) {
		World obj = null;
		try {
			// Write the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(orig);
			out.flush();
			out.close();

			// Make an input stream from the byte array and read
			// a copy of the object back in.
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			obj = (World) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return obj;
	}

}
