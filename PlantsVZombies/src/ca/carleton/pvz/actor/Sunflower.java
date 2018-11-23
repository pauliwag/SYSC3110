package ca.carleton.pvz.actor;

import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * Creates a sunflower, which accumulates sun points each turn. Sun points are
 * the in-game currency which the user spends on more plants to defeat the
 * zombies.
 *
 */
public class Sunflower extends Plant {

	public static final int SUNFLOWER_COST = 100; // in sunpoints

	private int turnPlaced; // the turn this sunflower was planted

	/**
	 * Creates a new sunflower.
	 */
	public Sunflower() {

		super(SUNFLOWER_COST);

		turnPlaced = 0;

	}

	/**
	 * Sets the turn this sunflower was planted.
	 *
	 * @param turnNumber The turn this sunflower was planted.
	 */
	public void setTurnPlaced(int turnNumber) {
		turnPlaced = turnNumber;
	}

	/**
	 * Gets the turn this sunflower was planted.
	 *
	 * @return The turn this sunflower was planted.
	 */
	public int getTurnPlaced() {
		return turnPlaced;
	}

	/**
	 * Gets the sunflower sprite.
	 *
	 * @return The sunflower sprite.
	 */
	@Override
	public Image getSprite() {
		InputStream stream = getClass().getResourceAsStream("sunflower.png");
		return new Image(stream);
	}

}
