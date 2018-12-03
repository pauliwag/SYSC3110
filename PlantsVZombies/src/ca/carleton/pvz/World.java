package ca.carleton.pvz;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.PriorityQueue;
import ca.carleton.pvz.level.Level;

/**
 * A class to store the levels in the game.
 *
 */
public class World implements Serializable {

	private static final long serialVersionUID = -1136085713229155895L;

	private PriorityQueue<Level> levels; // low level number is prioritized

	/**
	 * Constructs a new game world.
	 */
	public World() {
		levels = new PriorityQueue<>(11,
				(Comparator<Level> & Serializable) (level1, level2) -> level1.getNum() - level2.getNum());
	}

	/**
	 * Adds the given level(s) to this world's priority queue of levels.
	 *
	 * @param levels The level(s) to be added to this world's priority queue of
	 *            levels.
	 */
	public void addLevels(Level... levels) {
		if (levels.length > 0) {
			for (Level level : levels) {
				this.levels.add(level);
			}
		}
	}

	/**
	 * Gets the current level.
	 *
	 * @return The current level.
	 */
	public Level getCurrentLevel() {
		if (levels.size() > 0) {
			return levels.peek();
		} else {
			return null;
		}
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
	 * Removes the current level and gets the next level.
	 *
	 * @return The next level.
	 */
	public Level nextLevel() {
		levels.poll();
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
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return copy;
	}

}
