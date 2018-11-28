package ca.carleton.pvz.actor;

import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * A subtype of Zombie that moves thrice as fast as a normal zombie and has high
 * health.
 *
 */
public class FootballZombie extends Zombie {

	private static final long serialVersionUID = 6214923633339415829L;
	public static final int FOOTBALL_ZOMBIE_HEALTH = 1000;
	public static final int FOOTBALL_ZOMBIE_SPEED = 3;

	/**
	 * Constructs a FootballZombie.
	 */
	public FootballZombie() {
		super(FOOTBALL_ZOMBIE_HEALTH, FOOTBALL_ZOMBIE_SPEED);
	}

	/**
	 * Gets the FootballZombie sprite.
	 *
	 * @return The FootballZombie sprite.
	 */
	public Image getSprite() {
		InputStream stream = getClass().getResourceAsStream("football_zombie.png");
		return new Image(stream);
	}

}
