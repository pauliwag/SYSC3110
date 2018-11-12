package ca.carleton.pvz.level;

/**
 * The first level in the game.
 *
 */
public class LevelOne extends Level {

	private static final int WIDTH = 5;
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

	@Override
	public void initWaves() {
		// TODO Auto-generated method stub
	}

}
