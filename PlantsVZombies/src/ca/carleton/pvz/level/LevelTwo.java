package ca.carleton.pvz.level;

import ca.carleton.pvz.level.Wave.Difficulty;

/**
 * The first level in the game.
 *
 */
public class LevelTwo extends Level {

	private static final long serialVersionUID = -4902710599915665805L;

	private static final int WIDTH = 8;
	private static final int HEIGHT = 5;
	private static final int STARTING_SUNPOINTS = 500;

	/**
	 * Constructs level 1 with the specified dimensions (8 x 5).
	 */
	public LevelTwo() {
		super(2, WIDTH, HEIGHT, STARTING_SUNPOINTS);
	}

	/**
	 * Populates the waves priority queue with level-specific waves.
	 */
	@Override
	public void initWaves() {

		// ensure no residual waves
		clearWaves();

		// specify this level's waves
		Wave wave1 = new Wave(1, Difficulty.NORMAL, 3, 0, 0, 0);
		// Wave wave2 = new Wave(2, Difficulty.RAMPED, 0, 0, 0, 4);
		// Wave wave3 = new Wave(3, Difficulty.SUPER_RAMPED, 0, 2, 20, 0);

		// populate the queue
		addWaves(wave1);

	}

}
