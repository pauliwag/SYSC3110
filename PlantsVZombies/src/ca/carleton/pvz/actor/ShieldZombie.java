package ca.carleton.pvz.actor;

import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * A subtype of Zombie that has a shield (more health than normal).
 *
 */
public class ShieldZombie extends Zombie {

	public static final int SHIELD_ZOMBIE_HEALTH = 1000;
	public static final int SHIELD_ZOMBIE_SPEED = 1; // tiles moved to the left per turn

	/**
	 * Constructs a shield zombie.
	 */
	public ShieldZombie() {

		super(SHIELD_ZOMBIE_HEALTH, SHIELD_ZOMBIE_SPEED);

	}

	/**
	 * Gets the shield zombie sprite.
	 *
	 * @return The shield zombie sprite.
	 */
	public Image getSprite() {
		InputStream stream = getClass().getResourceAsStream("zombie_tutorial.png");
		return new Image(stream);
		// TODO : Get proper sprite ...
	}

}
