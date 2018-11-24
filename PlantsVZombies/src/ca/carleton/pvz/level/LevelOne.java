package ca.carleton.pvz.level;

import ca.carleton.pvz.level.Wave.Difficulty;

/**
 * The first level in the game.
 *
 */
public class LevelOne extends Level {

	private static final int WIDTH = 7;
	private static final int HEIGHT = 5;
	private static final int STARTING_SUNPOINTS = 500;

	/**
	 * Constructs level 1 with the specified dimensions (7 x 5).
	 */
	public LevelOne() {
		super("Level 1", WIDTH, HEIGHT, STARTING_SUNPOINTS);
	}

	/**
	 * Populates the waves priority queue with level-specific waves.
	 */
	@Override
	public void initWaves() {

		// ensure no residual waves
		clearWaves();

		// specify this level's waves
		Wave wave1 = new Wave(1, Difficulty.NORMAL, 3, 1, 0);
		Wave wave2 = new Wave(2, Difficulty.NORMAL, 1, 2, 0);
		Wave wave3 = new Wave(3, Difficulty.SUPER_RAMPED, 0, 1, 20);
		Wave wave4 = new Wave(4, Difficulty.SUPER_RAMPED, 0, 10, 0);

		// populate the queue
		addWaves(wave1, wave2, wave3, wave4);

	}

}
