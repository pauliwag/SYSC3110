package ca.carleton.pvz;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Supplier;
import java.util.stream.Collectors;
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
	private CooldownManager cooldownManager;

	/**
	 * Constructs a new game world.
	 */
	public World() {
		levels = new PriorityQueue<>(11,
				(Comparator<Level> & Serializable) (level1, level2) -> level1.getNum() - level2.getNum());
		cooldownManager = new CooldownManager();
	}

	/**
	 * Adds the given level(s) to this world's priority queue of levels. Each
	 * level in this world must have a unique level number; if an attempt is
	 * made to add a level that would violate this rule, then said level is not
	 * added (other, valid levels in the varargs still get added, though).
	 *
	 * @param levels The level(s) to be added to this world's priority queue of
	 *            levels.
	 */
	public void addLevels(Level... levels) {

		if (levels.length > 0) {

			// store the numbers of the given levels in an array
			int[] levelNums = new int[levels.length];
			IntStream.range(0, levels.length).forEach(i -> levelNums[i] = levels[i].getNum());

			// wrap a stream comprising invalid level numbers in a
			// Supplier, thereby rendering said stream as "reusable"
			Supplier<IntStream> invalidLevelNums = () -> Arrays.stream(levelNums).filter(num -> hasLevel(num)
					|| Collections.frequency(Arrays.stream(levelNums).boxed().collect(Collectors.toList()), num) > 1);

			// inform the user of the invalid levels
			if (invalidLevelNums.get().findAny().isPresent()) {
				GUIController.showAlert("Unaddable Levels",
						"Could not add levels " + Arrays.toString(invalidLevelNums.get().distinct().toArray()),
						"Some levels could not be added because they share level numbers or "
								+ "this world already contains levels having those level numbers.",
						AlertType.INFORMATION);
			}

			// add the valid levels to this world
			Arrays.stream(levels).filter(lvl -> !hasLevel(lvl.getNum()) && Collections
					.frequency(Arrays.stream(levelNums).boxed().collect(Collectors.toList()), lvl.getNum()) == 1)
					.forEach(lvl -> this.levels.add(lvl));

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

	/**
	 * Gets this world's CooldownManager.
	 *
	 * @return This world's CooldownManager.
	 */
	public CooldownManager getCooldownManager() {
		return cooldownManager;
	}

}
