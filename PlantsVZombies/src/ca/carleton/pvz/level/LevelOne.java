package ca.carleton.pvz.level;

import ca.carleton.pvz.level.Wave.Difficulty;

/**
 * The first level in the game.
 *
 */
public class LevelOne extends Level {

	private static final long serialVersionUID = 1662701129794850021L;
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
		Wave wave2 = new Wave(2, Difficulty.RAMPED, 2, 2, 0);
		Wave wave3 = new Wave(3, Difficulty.SUPER_RAMPED, 0, 3, 20);

		// populate the queue
		addWaves(wave1, wave2, wave3);

	}

}
