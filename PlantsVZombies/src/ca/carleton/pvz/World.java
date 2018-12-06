package ca.carleton.pvz;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import ca.carleton.pvz.gui.GUIController;
import ca.carleton.pvz.level.Level;
import javafx.scene.control.Alert.AlertType;

/**
 * A means of storing, mutating and deploying a collection of levels.
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
			int[] levelNums = new int[levels.length];
			IntStream.range(0, levels.length).forEach(i -> levelNums[i] = levels[i].getNum());
			Supplier<IntStream> invalidLevelNums = () -> Arrays.stream(levelNums).filter(num -> hasLevel(num));
			if (invalidLevelNums.get().findAny().isPresent()) {
				GUIController.showAlert("Cannot Load Levels",
						"Cannot load levels having numbers " + Arrays.toString(invalidLevelNums.get().toArray()),
						"Some levels cannot be loaded because this world already contains levels having those level numbers.",
						AlertType.INFORMATION);
			}
			Arrays.stream(levels).filter(lvl -> !hasLevel(lvl.getNum())).forEach(lvl -> this.levels.add(lvl));
		}
	}

	/**
	 * Returns whether this world contains a level having the given level
	 * number.
	 *
	 * @param levelNum The level number to find.
	 * @return true if this world contains a level having the given level
	 *         number, false otherwise.
	 */
	public boolean hasLevel(int levelNum) {
		return levels.stream().filter(lvl -> lvl.getNum() == levelNum).findAny().isPresent();
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
