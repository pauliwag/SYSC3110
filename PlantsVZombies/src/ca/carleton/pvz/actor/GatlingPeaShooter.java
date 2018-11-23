package ca.carleton.pvz.actor;

import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * A class for the gatling pea shooter, which has a high damage output.
 *
 */
public class GatlingPeaShooter extends PeaShooter {

	public static final int GATLING_PEA_DAMAGE = 900;
	public static final int GATLING_PEA_COST = 500; // in sunpoints

	/**
	 * Constructs a new gatling pea-shooting plant, which deals more damage
	 * per pea than a normal pea shooter.
	 */
	public GatlingPeaShooter() {

		super(GATLING_PEA_DAMAGE);

	}

	/**
	 * Gets the gatling pea shooter sprite.
	 *
	 * @return The gatling pea shooter sprite.
	 */
	@Override
	public Image getSprite() {
		InputStream stream = getClass().getResourceAsStream("peashooter.png");
		return new Image(stream);
		// TODO : Get gatling pea shooter sprite ...
	}

}
