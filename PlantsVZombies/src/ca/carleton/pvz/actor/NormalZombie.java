package ca.carleton.pvz.actor;

import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * A subtype of Zombie that has no special abilities.
 *
 */
public class NormalZombie extends Zombie {

	public static final int NORMAL_ZOMBIE_HEALTH = 600;
	public static final int NORMAL_ZOMBIE_SPEED = 1; // tiles moved to the left per turn

	/**
	 * Constructs a normal zombie.
	 */
	public NormalZombie() {

		super(NORMAL_ZOMBIE_HEALTH, NORMAL_ZOMBIE_SPEED);

	}

	/**
	 * Gets the normal zombie sprite.
	 *
	 * @return The normal zombie sprite.
	 */
	public Image getSprite() {

		InputStream stream = getClass().getResourceAsStream("zombie_tutorial.png");

		return new Image(stream);

	}

}
