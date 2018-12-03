package ca.carleton.pvz.level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A class for user-created levels.
 *
 */
public class CustomLevel extends Level {

	private static final long serialVersionUID = 1813673426450151725L;
	private static final int WIDTH = 8;
	private static final int HEIGHT = 5;
	private Wave[] wavesArray;

	// for level reload
	private int startingSunpoints;
	private ArrayList<Wave> wavesClone;

	/**
	 * Constructs a custom level.
	 */
	public CustomLevel(int levelNum, int startingSunpoints, Terrain terrain, Collection<Wave> waves) {

		super(levelNum, WIDTH, HEIGHT, startingSunpoints, terrain);
		wavesArray = new Wave[waves.size()];
		waves.toArray(wavesArray);
		initWaves();

		// for level reload
		this.startingSunpoints = startingSunpoints;
		wavesClone = new ArrayList<>(waves.size());
		Iterator<Wave> it = waves.iterator();
		while (it.hasNext()) {
			wavesClone.add(it.next().clone());
		}

	}

	/**
	 * Populates the waves priority queue with user-specified waves.
	 */
	@Override
	public void initWaves() {
		clearWaves(); // ensure no residual waves
		addWaves(wavesArray); // populate the priority queue
	}

	/**
	 * Gets the starting number of sunpoints.
	 *
	 * @return The starting number of sunpoints.
	 */
	public int getStartingSunpoints() {
		return startingSunpoints;
	}

	/**
	 * Gets a clone of the original (unmutated) collection of waves.
	 *
	 * @return A clone of the original (unmutated) collection of waves.
	 */
	public ArrayList<Wave> getWavesClone() {
		return wavesClone;
	}

}
