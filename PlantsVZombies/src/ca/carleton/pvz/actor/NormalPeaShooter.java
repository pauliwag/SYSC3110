package ca.carleton.pvz.actor;

import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * A class for the normal pea-shooting plant.
 *
 */
public class NormalPeaShooter extends PeaShooter {

	private static final long serialVersionUID = -5722345411858347989L;
	public static final int NORMAL_PEA_COST = 200; // in sunpoints
	public static final int NORMAL_PEA_DAMAGE = 200;

	/**
	 * Creates a new normal pea-shooting plant. This type of plant can shoot and
	 * "kill" zombies.
	 */
	public NormalPeaShooter() {

		super(NORMAL_PEA_COST, NORMAL_PEA_DAMAGE);

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