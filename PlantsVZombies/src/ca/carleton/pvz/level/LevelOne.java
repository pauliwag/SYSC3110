package ca.carleton.pvz.level;

/**
 * The first level in the game.
 *
 */
public class LevelOne extends Level {

	private static final int WIDTH = 7;
	private static final int HEIGHT = 5;
	private static final int STARTING_SUNPOINTS = 500;

	/**
	 * Constructs level 1 with the specified dimensions (5x5).
	 */
	public LevelOne() {
		super("Level 1", WIDTH, HEIGHT, STARTING_SUNPOINTS);
	}

	/**
	 * Returns this level's grid as a String.
	 *
	 * @return This level's grid as a String.
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * Populates the waves priority queue with level-specific waves.
	 */
	@Override
	public void initWaves() {

		// ensure no residual waves (non-issue for 1st level)
		clearWaves();

		// specify this level's waves
		Wave wave1 = new Wave(1, 2, 0, 0);
		Wave wave2 = new Wave(2, 3, 0, 0);
		Wave wave3 = new Wave(3, 4, 0, 0);

		// populate the queue
		addWaves(wave1, wave2, wave3);

	}

}
