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
import javafx.scene.image.Image;

/**
 * The abstract class from which all game levels inherit.
 *
 */
public abstract class Level implements Serializable {

	private static final long serialVersionUID = 8845013896074447924L;

	public static final int PASSIVE_SUNPOINT_BOOST = 25; // every two turns

	/** The number of this level. */
	private int levelNum;

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
	private int sunpoints;

	/**
	 * Indicates whether a zombie was spawned on this level during the current
	 * turn.
	 */
	private boolean zombieSpawned;

	/**
	 * Tileset options.
	 */
	public enum Terrain {

		GRASS, SAND, ICE

	};

	/** This level's terrain or tileset. */
	private Terrain terrain;

	/**
	 * Initializes the fields of a level object.
	 *
	 * @param levelNum This level's number.
	 * @param width The width (number of horizontal cells) of the level.
	 * @param height The height (number of vertical cells) of the level.
	 * @param startingSunPoints The sun points the player has at the start of
	 *            the level.
	 * @param terrain This level's terrain or tileset.
	 */
	public Level(int levelNum, int width, int height, int startingSunPoints, Terrain terrain) {

		this.levelNum = levelNum;
		levelDimension = new Dimension(width, height);
		sunpoints = startingSunPoints;
		grid = new Actor[width][height];
		turn = 0;
		zombieSpawned = false;
		this.terrain = terrain;

		// initialize waves queue such that a lower wave number is prioritized
		waves = new PriorityQueue<>(11,
				(Comparator<Wave> & Serializable) (wave1, wave2) -> wave1.getNum() - wave2.getNum());
		

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
	 * Gets this level's terrain.
	 *
	 * @return This level's terrain.
	 */
	public Terrain getTerrain() {
		return terrain;
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
	 * Gets whether a zombie was spawned in the previous turn.
	 *
	 * @return true if a zombie was spawned last turn, false otherwise.
	 */
	public boolean zombieSpawnedLastTurn() {
		return zombieSpawned;
	}

	/**
	 * Sets zombieSpawned to true.
	 */
	public void zombieSpawned() {
		zombieSpawned = true;
	}

	/**
	 * Sets zombieSpawned to false.
	 */
	public void zombieNotSpawned() {
		zombieSpawned = false;
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
	public int getNum() {
		return levelNum;
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
	 * Increases sunpoints by the given amount.
	 *
	 * @param amt The amount by which to increase sunpoints.
	 */
	public void addToSunpoints(int amt) {
		sunpoints += amt;
	}

	/**
	 * Decreases sunpoints by the given amount.
	 *
	 * @param amt The amount by which to decrease sunpoints.
	 */
	public void subtractFromSunpoints(int amt) {
		sunpoints -= amt;
	}

	/**
	 * Gets the current amount of sunpoints.
	 *
	 * @return The current amount of sunpoints.
	 */
	public int getSunpoints() {
		return sunpoints;
	}

	/**
	 * Gets a sprite of this level's terrain.
	 *
	 * @return A sprite of this level's terrain.
	 */
	public Image getSprite() {
		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/grass.png"));
		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/sand.png"));
		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/snow.png"));
		return null;
	}
}
