package ca.carleton.pvz.actor;

import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * A class for the normal pea-shooting plant.
 *
 */
public class NormalPeaShooter extends PeaShooter {

	public static final int NORMAL_PEA_COST = 300; // in sunpoints
	public static final int NORMAL_PEA_DAMAGE = 600;

	private int hits; // number of hits on zombies

	/**
	 * Creates a new normal pea-shooting plant. This type of plant can shoot and
	 * "kill" zombies.
	 */
	public NormalPeaShooter() {

		super(NORMAL_PEA_COST, NORMAL_PEA_DAMAGE);

		hits = 0; // TODO : Remove this field ...

	}

	/**
	 * Gets the number of times this pea shooter has hit a zombie with its
	 * projectile.
	 *
	 * @return The number of times this pea shooter has hit a zombie with its
	 *         projectile.
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * Resets this pea shooter's hits upon advancing to the next turn.
	 */
	public void newTurn() {
		hits = 0;
	}

	/**
	 * Increments this pea shooter's number of hits by one.
	 */
	public void addHit() {
		++hits;
	}

	/**
	 * Gets the peashooter sprite.
	 *
	 * @return The peashooter sprite.
	 */
	@Override
	public Image getSprite() {
		InputStream stream = getClass().getResourceAsStream("peashooter.png");
		return new Image(stream);
	}

}