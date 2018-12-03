package ca.carleton.pvz.level;

import java.util.Collection;


/**
 * Custom level object (for Level Builder)
 *
 */
public class CustomLevel extends Level {

	private static final long serialVersionUID = 6313811842425362371L;
	private static final int WIDTH = 8;
	private static final int HEIGHT = 5;
	private Wave[] wavesArray;
	/**
	 * Constructs level 1 with the specified dimensions (8 x 5).
	 */
	public CustomLevel(int levelNum, int sunpoints, Terrain terrain, Collection<Wave> waves) {
		super(levelNum, WIDTH, HEIGHT, sunpoints, terrain);
		wavesArray = new Wave[waves.size()];
		waves.toArray(wavesArray);
		initWaves();
	}

	/**
	 * Populates the waves priority queue with level-specific waves.
	 */
	@Override
	public void initWaves() {

		// ensure no residual waves
		clearWaves();

		// populate the queue
		addWaves(wavesArray);

	}

}
