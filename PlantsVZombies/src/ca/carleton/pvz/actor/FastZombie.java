package ca.carleton.pvz.actor;

import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * A subtype of Zombie that moves twice as fast as a normal zombie but is
 * weaker.
 *
 */
public class FastZombie extends Zombie {

	public static final int FAST_ZOMBIE_HEALTH = 400;
	public static final int FAST_ZOMBIE_SPEED = 2; // tiles moved to the left per turn

	/**
	 * Constructs a fast zombie.
	 */
	public FastZombie() {

		super(FAST_ZOMBIE_HEALTH, FAST_ZOMBIE_SPEED);

	}

	/**
	 * Gets the fast zombie sprite.
	 *
	 * @return The fast zombie sprite.
	 */
	public Image getSprite() {

		InputStream stream = getClass().getResourceAsStream("fast_zombie.png");

		return new Image(stream);

	}

}
