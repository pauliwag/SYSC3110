package ca.carleton.pvz.level;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.actor.Sunflower;
import ca.carleton.pvz.actor.Zombie;

/**
 * The abstract class from which all game levels inherit.
 *
 */
public abstract class Level {

	/** The name of this level; e.g., "Level 1". */
	private String levelName;

	/**
	 * A Dimension object comprising the height and width, in cells, of this
	 * level's grid.
	 */
	private Dimension levelDimension;

	/**
	 * A 2-D array representing this level's grid. Each grid cell can hold a
	 * concrete extender of Actor.
	 */
	protected Actor[][] grid;

	/** The current turn. */
	private int turn;

	/**
	 * A queue that stores this level's waves in ascending order of wave number.
	 */
	private PriorityQueue<Wave> waves;

	/** The player's unspent sun points in this level. */
	private int sunPoints;

	/**
	 * Initializes the fields of a level object.
	 *
	 * @param levelName The name of the level.
	 * @param width The width (number of horizontal cells) of the level.
	 * @param height The height (number of vertical cells) of the level.
	 * @param startingSunPoints The sun points the player has at the start of
	 *            the level.
	 */
	public Level(String levelName, int width, int height, int startingSunPoints) {

		this.levelName = levelName;
		levelDimension = new Dimension(width, height);
		sunPoints = startingSunPoints;
		grid = new Actor[width][height];
		turn = 0;

		// initialize waves queue such that a lower wave number is prioritized
		waves = new PriorityQueue<>(11, (Comparator<Wave> & Serializable) (wave1, wave2) -> {
			return wave1.getNum() - wave2.getNum();
		});
		initWaves();

		// initialize grid (playable area)
		for (Actor[] row : grid) {
			Arrays.fill(row, null);
		}

	}

	/**
	 * Adds the given wave(s) to this level's queue of waves. This method is
	 * needed for level subclasses to be able to augment the waves queue.
	 *
	 * @param waves The wave(s) to be added to this level's queue of waves.
	 */
	public void addWaves(Wave... waves) {
		if (waves.length > 0) {
			for (Wave wave : waves) {
				this.waves.add(wave);
			}
		}
	}

	/**
	 * Initializes waves queue via subclass.
	 */
	public abstract void initWaves();

	/**
	 * Clears the waves queue.
	 */
	public void clearWaves() {
		waves.clear();
	}

	/**
	 * Gets the wave at the head of the queue.
	 *
	 * @return The wave at the head of the queue.
	 */
	public Wave getHeadWave() {
		return waves.peek();
	}

	/**
	 * Dequeues the head of the waves queue.
	 */
	public void dequeueHeadWave() {
		waves.poll();
	}

	/**
	 * Returns whether the head wave is defeated.
	 *
	 * @return true if the head wave is defeated, false otherwise.
	 */
	public boolean isHeadWaveEmpty() {
		return getHeadWave().isEmpty();
	}

	/**
	 * Returns whether this level is beat. This level is considered beat if all
	 * queued waves are void of zombies and there are no zombies on the map.
	 *
	 * @return true if this level is beat, false otherwise.
	 */
	public boolean isBeat() {

		for (Wave wave : waves) {
			if (wave.getTotalNumZombies() != 0) {
				return false;
			}
		}

		for (int x = 0; x < getNumCols(); ++x) {
			for (int y = 0; y < getNumRows(); ++y) {
				if (getCell(x, y) instanceof Zombie) {
					return false;
				}
			}
		}

		return true;

	}

	/**
	 * Gets the number of waves in queue.
	 *
	 * @return The number of waves in queue.
	 */
	public int getNumWaves() {
		return waves.size();
	}

	/**
	 * Gets the current turn.
	 *
	 * @return The current turn.
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * Increments turn by one.
	 */
	public void incTurn() {
		++turn;
	}

	/**
	 * Gets the number of sunflowers on the map.
	 *
	 * @return The number of sunflowers on the map.
	 */
	public int getNumSunflowers() {
		int numSunflowers = 0;
		for (int x = 0; x < getNumCols(); ++x) {
			for (int y = 0; y < getNumRows(); ++y) {
				if (getCell(x, y) instanceof Sunflower) {
					++numSunflowers;
				}
			}
		}
		return numSunflowers;
	}

	/**
	 * Gets the contents of the cell located at the given coordinates.
	 *
	 * @param x The x-coordinate (column number).
	 * @param y The y-coordinate (row number).
	 * @return The cell located at the given coordinates, or null if the
	 *         coordinates are invalid.
	 */
	public Actor getCell(int x, int y) {
		if (isPointValid(new Point(x, y))) {
			return grid[x][y];
		}
		return null;
	}

	/**
	 * Places a plant or zombie at the given point.
	 *
	 * @param a A plant or zombie object to be placed.
	 * @param p The point at which to place the given object.
	 */
	public void placeActor(Actor a, Point p) {
		if (isPointValid(p)) {
			grid[p.x][p.y] = a;
		}
	}

	/**
	 * Gets the number of columns in this level.
	 *
	 * @return The number of columns in this level.
	 */
	public int getNumCols() {
		return levelDimension.width;
	}

	/**
	 * Gets the number of rows in this level.
	 *
	 * @return The number of rows in this level.
	 */
	public int getNumRows() {
		return levelDimension.height;
	}

	/**
	 * Gets this level's name.
	 *
	 * @return This level's name.
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * Checks if the given point is a valid position on the grid.
	 *
	 * @param p The point whose validity will be evaluated.
	 * @return true if valid, false otherwise.
	 */
	public boolean isPointValid(Point p) {
		return p.x < levelDimension.width && p.x >= 0 && p.y < levelDimension.height && p.y >= 0;
	}

	/**
	 * Adds specified value from sunpoints field
	 *
	 * @param value The value to be added from sunpoints
	 */
	public void addToSunpoints(int value) {

		sunPoints += value;
	}

	/**
	 * Subtracts specified value from sunpoints field
	 *
	 * @param value The value to be subtracted from sunpoints
	 */
	public void subtractFromSunpoints(int value) {

		sunPoints -= value;
	}

	/**
	 * Returns sunPoints at the current state of the game
	 *
	 * @return sunPoints Users current sunpoints
	 */
	public int getSunpoints() {

		return sunPoints;
	}

	/**
	 * Returns this level's grid as a String.
	 *
	 * @return This level's grid as a String.
	 */
	@Override
	public String toString() {
		String s = "";
		for (int row = 0; row < levelDimension.height; ++row) {
			s += "| ";
			for (int col = 0; col < levelDimension.width; ++col) {
				if (grid[col][row] != null) {
					s += grid[col][row] + " | ";
				} else {
					s += "  | ";
				}
			}
			s += "\n";
		}
		return s;
	}

}
