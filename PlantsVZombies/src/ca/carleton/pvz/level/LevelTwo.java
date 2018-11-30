package ca.carleton.pvz.level;

import ca.carleton.pvz.level.Wave.Difficulty;

/**
 * The second level in the game.
 *
 */
public class LevelTwo extends Level {

	private static final long serialVersionUID = -4902710599915665805L;

	private static final int WIDTH = 8;
	private static final int HEIGHT = 5;
	private static final int STARTING_SUNPOINTS = 500;

	/**
	 * Constructs level 2 with the specified dimensions (8 x 5).
	 */
	public LevelTwo() {

		super(2, WIDTH, HEIGHT, STARTING_SUNPOINTS, Climate.DESERT);

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
				new Wave(1, Difficulty.NORMAL,       2,     2,     0,     0,     0,     0,     0),
				new Wave(2, Difficulty.NORMAL,       0,     0,     0,     1,     0,     0,     0),
				new Wave(3, Difficulty.NORMAL,       0,     1,     2,     0,     0,     0,     0),
				new Wave(4, Difficulty.RAMPED,       0,     0,     0,     1,     0,     0,     0),
				new Wave(5, Difficulty.RAMPED,       0,     2,     1,     0,     0,     0,     0),
				new Wave(6, Difficulty.RAMPED,       0,     0,     2,     1,     0,     0,     0),
				new Wave(7, Difficulty.RAMPED,       2,     1,     0,     0,     0,     0,     0),
				new Wave(8, Difficulty.SUPER_RAMPED, 0,     0,     0,     0,     3,     1,     0),
				new Wave(9, Difficulty.RAMPED,       0,     0,     0,     0,     0,     0,     1)

		};

		// populate the queue
		addWaves(waves);

	}

}
