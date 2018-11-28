package ca.carleton.pvz.actor;

import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * A subtype of Zombie that moves twice as fast as a normal zombie but is
 * weaker.
 *
 */
public class FootballZombie extends Zombie {

	private static final long serialVersionUID = 6214923633339415829L;
	public static final int FOOTBALL_ZOMBIE_HEALTH = 1000;
	public static final int FOOTBALL_ZOMBIE_SPEED = 3;

	/**
	 * Constructs a fast zombie.
	 */
	public FootballZombie() {

		super(FOOTBALL_ZOMBIE_HEALTH, FOOTBALL_ZOMBIE_SPEED);

	}

	/**
	 * Gets the fast zombie sprite.
	 *
	 * @return The fast zombie sprite.
	 */
	public Image getSprite() {

		InputStream stream = getClass().getResourceAsStream("football_zombie.png");

		return new Image(stream);

	}

}
