package ca.carleton.pvz.level;

import ca.carleton.pvz.level.Wave.Difficulty;

/**
 * The first level in the game.
 *
 */
public class LevelOne extends Level {

	private static final long serialVersionUID = 1662701129794850021L;

	private static final int WIDTH = 8;
	private static final int HEIGHT = 5;
	private static final int STARTING_SUNPOINTS = 500;

	/**
	 * Constructs level 1 with the specified dimensions (8 x 5).
	 */
	public LevelOne() {

		super(1, WIDTH, HEIGHT, STARTING_SUNPOINTS, Terrain.GRASS);

	}

	/**
	 * Populates the waves priority queue with level-specific waves.
	 */
	@Override
	public void initWaves() {

		// ensure no residual waves
		clearWaves();

		// specify this level's waves
		Wave[] waves = {
				//                               normal  heavy   fast    wiz  fball   giga   boss
				new Wave(1, Difficulty.NORMAL,       1,     0,     0,     0,     0,     0,     0),
				new Wave(2, Difficulty.NORMAL,       0,     0,     0,     1,     0,     0,     0),
				new Wave(3, Difficulty.NORMAL,       1,     1,     0,     0,     0,     0,     0),
				new Wave(4, Difficulty.NORMAL,       1,     1,     1,     0,     0,     0,     0),
				new Wave(5, Difficulty.NORMAL,       0,     1,     1,     0,     0,     0,     0),
				new Wave(6, Difficulty.RAMPED,       0,     0,     2,     1,     0,     0,     0),
				new Wave(7, Difficulty.SUPER_RAMPED, 0,     2,     2,     0,     0,     0,     0),
				new Wave(8, Difficulty.SUPER_RAMPED, 0,     0,     0,     0,     0,     3,     0)

		};

		// populate the queue
		addWaves(waves);

	}

}
